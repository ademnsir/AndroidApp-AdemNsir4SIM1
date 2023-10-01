package tn.esprit.gameradem_nsir

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText


class OtpValidationActivity : AppCompatActivity() {
    private lateinit var tiCode1: TextInputEditText
    private lateinit var tiCode2: TextInputEditText
    private lateinit var tiCode3: TextInputEditText
    private lateinit var tiCode4: TextInputEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_otp_validation)
        tiCode1 = findViewById(R.id.tiCode1)
        tiCode2 = findViewById(R.id.tiCode2)
        tiCode3 = findViewById(R.id.tiCode3)
        tiCode4 = findViewById(R.id.tiCode4)

        // Récupérer l'extra contenant le code OTP
        val otpCode = intent.getStringExtra("otp_code")

        // Préremplir les champs de texte avec le code OTP par défaut
        tiCode1.setText(otpCode?.get(0).toString())
        tiCode2.setText(otpCode?.get(1).toString())
        tiCode3.setText(otpCode?.get(2).toString())
        tiCode4.setText(otpCode?.get(3).toString())

        //bouton Return
        val varbtnReturn: Button = findViewById(R.id.btnReturn)
        varbtnReturn.setOnClickListener {
            val intent = Intent(this, Forget_PasswordActivity::class.java)

            startActivity(intent)
        }

        tiCode1 = findViewById(R.id.tiCode1)
        tiCode2 = findViewById(R.id.tiCode2)
        tiCode3 = findViewById(R.id.tiCode3)
        tiCode4 = findViewById(R.id.tiCode4)

        val btnVerify: Button = findViewById(R.id.btnVerify)

        //snacbar ResendCodeButton
        val varbtnResendCode: Button = findViewById(R.id.btnResendCode)
        varbtnResendCode.setOnClickListener {
            showSnackbar("Coming soon :)")
        }


        //controle de  saisie sur les champs SMS
        btnVerify.setOnClickListener {
            val code1 = tiCode1.text.toString()
            val code2 = tiCode2.text.toString()
            val code3 = tiCode3.text.toString()
            val code4 = tiCode4.text.toString()

            if (code1.isEmpty() || code2.isEmpty() || code3.isEmpty() || code4.isEmpty()) {

                showSnackbar("Veuillez remplir tous les champs!")
            }

            else {
                val intent = Intent(this, ResetPasswordActivity::class.java)
                startActivity(intent)

            }
        }
    }

    private fun showSnackbar(message: String) {
        val rootView: View = findViewById(android.R.id.content)
        Snackbar.make(rootView, message, Snackbar.LENGTH_SHORT).show()
    }
}
