package tn.esprit.gameradem_nsir

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class Forget_PasswordActivity: AppCompatActivity() {
    private lateinit var tiEmail: TextInputEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forget_password)

        // bouton Return
        val varbtnReturn: Button = findViewById(R.id.btnReturn)
        varbtnReturn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        // bouton SMS forgetpassword vers otpvalidation avec code 6789
        val varbtnSendSMS: Button = findViewById(R.id.btnSendSMS)
        val tiEmailLayout: TextInputLayout = findViewById(R.id.tiEmailLayout)
        varbtnSendSMS.setOnClickListener {
            val emailOrPhoneNumber = tiEmail.text.toString()

            var hasError = false // Variable pour suivre si une erreur s'est produite

            // Controle de saisie Email ou numéro de téléphone
            if (emailOrPhoneNumber.isEmpty()) {
                tiEmailLayout.error = "Email/Phone must not be empty!"
                tiEmailLayout.visibility = View.VISIBLE
                hasError = true
            }
            else if (!isValidEmail(emailOrPhoneNumber) && !isValidPhoneNumber(emailOrPhoneNumber)) {
                tiEmailLayout.error = "Invalid email/phone format!"
                tiEmailLayout.visibility = View.VISIBLE
                hasError = true
            }
            else {
                tiEmailLayout.error = null
                tiEmailLayout.isErrorEnabled = false
            }

            // Vérifier si l'entrée est un numéro de téléphone
            if (!hasError) {
                if (isValidPhoneNumber(emailOrPhoneNumber)) {
                    val intent = Intent(this, OtpValidationActivity::class.java)
                    intent.putExtra("otp_code", "6789") // Numéro de téléphone, utilisez le code par défaut
                    startActivity(intent)
                }
                else {
                    // Vérifiez si l'entrée contient uniquement des chiffres
                    if (emailOrPhoneNumber.all { it.isDigit() }) {

                        // Si l'entrée ne contient que des chiffres, redirigez vers l'activité OTP
                        val intent = Intent(this, OtpValidationActivity::class.java)
                        intent.putExtra("email_or_phone", emailOrPhoneNumber)
                        startActivity(intent)
                    }
                    else {
                        // Sinon, affichez une erreur
                        tiEmailLayout.error = "Phone number must contain only digits!"
                        tiEmailLayout.visibility = View.VISIBLE
                    }
                }
            }
        }




        // bouton submit forgetpassword vers otpvalidation avec code 1234
        val varbtnSubmit: Button = findViewById(R.id.btnSubmit)
        tiEmail = findViewById(R.id.tiEmail)

        varbtnSubmit.setOnClickListener {
            val email = tiEmail.text.toString()

            var hasError = false // Variable pour suivre si une erreur s'est produite

            // Controle de saisie Email
            if (email.isEmpty()) {
                tiEmailLayout.error = "Email must not be empty!"
                tiEmailLayout.visibility = View.VISIBLE
                hasError = true
            } else if (!isValidEmail(email)) {
                tiEmailLayout.error = "Invalid email format!"
                tiEmailLayout.visibility = View.VISIBLE
                hasError = true
            } else {
                tiEmailLayout.error = null
                tiEmailLayout.isErrorEnabled = false
            }

            if (!hasError) {
                val intent = Intent(this, OtpValidationActivity::class.java)
                intent.putExtra("otp_code", "1234")
                startActivity(intent)
            }
        }
    }

    private fun isValidEmail(email: String): Boolean {
        val regex = "^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}$"
        return regex.toRegex().matches(email)
    }

    private fun isValidPhoneNumber(phoneNumber: String): Boolean {
        // Vérifiez si le numéro de téléphone a exactement 8 chiffres
        return phoneNumber.length == 8 && phoneNumber.all { it.isDigit() }
    }
}
