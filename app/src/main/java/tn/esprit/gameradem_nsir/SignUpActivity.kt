package tn.esprit.gameradem_nsir

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class SignUpActivity : AppCompatActivity() {
    private lateinit var tiEmail: TextInputEditText
    private lateinit var tiPassword: TextInputEditText
    private lateinit var tiConfirmPassword: TextInputEditText
    private lateinit var tiFullName: TextInputEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)


        //bouton Return
        val varbtnReturn: Button = findViewById(R.id.btnReturn)
        varbtnReturn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)


        }
        tiEmail = findViewById(R.id.tiEmail)
        tiPassword = findViewById(R.id.tiPassword)
        tiConfirmPassword = findViewById(R.id.tiConfirmPassword)
        tiFullName = findViewById(R.id.tiFullName)

        // Privacy and Policy snackbar
        val VarbtnTermsAndPolicy: Button = findViewById(R.id.btnTermsAndPolicy)

        VarbtnTermsAndPolicy.setOnClickListener {
            showSnackbar("Coming soon :)")
        }

        // Bouton login
        val varbtnSignUp: Button = findViewById(R.id.btnSignUp)

        // Labels d'erreur
        val tiEmailLayout: TextInputLayout = findViewById(R.id.tiEmailLayout)
        val tiPasswordLayout: TextInputLayout = findViewById(R.id.tiPasswordLayout)
        val tiConfirmPasswordLayout: TextInputLayout = findViewById(R.id.tiConfirmPasswordLayout)
        val tiFullnameLayout: TextInputLayout = findViewById(R.id.tiFullNameLayout)


        varbtnSignUp.setOnClickListener {
            val email = tiEmail.text.toString()
            val password = tiPassword.text.toString()
            val confirmPassword = tiConfirmPassword.text.toString()
            val fullname = tiFullName.text.toString()
            var hasError = false // Variable pour suivre si une erreur s'est produite

            // Controle de saisie FullName
            if (fullname.isEmpty()) {
                tiFullnameLayout.error = "Fullname must not be empty!"
                tiFullnameLayout.visibility = View.VISIBLE
                hasError = true
            } else if (fullname.length < 4) {
                tiFullnameLayout.error = "Fullname must be at least 4 characters long!"
                tiFullnameLayout.visibility = View.VISIBLE
                hasError = true
            } else if (!isAlpha(fullname)) {
                tiFullnameLayout.error = "Fullname must contain only letters!"
                tiFullnameLayout.visibility = View.VISIBLE
                hasError = true
            } else {
                tiFullnameLayout.error = null
                tiFullnameLayout.isErrorEnabled = false
            }



            // Controle de saisie Email
            if (email.isEmpty()) {
                tiEmailLayout.error = "Email must not be empty!"
                tiEmailLayout.visibility = View.VISIBLE
                hasError = true // Une erreur s'est produite
            }
            else if (!isValidEmail(email)) {
                tiEmailLayout.error = "Invalid email format!"
                tiEmailLayout.visibility = View.VISIBLE
                hasError = true // Une erreur s'est produite
            }
            else {
                tiEmailLayout.error = null
                tiEmailLayout.isErrorEnabled = false
            }

            // Controle de saisie  mot de passe
            if (password.isEmpty()) {
                tiPasswordLayout.error = "Password must not be empty!"
                tiPasswordLayout.visibility = View.VISIBLE
                hasError = true // Une erreur s'est produite
            }
            else if (!isValidPassword(password)) {
                tiPasswordLayout.error = "Password must contain at least one uppercase letter, one digit, and be at least 8 characters long."
                tiPasswordLayout.visibility = View.VISIBLE
                hasError = true
            }
            else {
                tiPasswordLayout.error = null
                tiPasswordLayout.isErrorEnabled = false
            }

            // Vérification de la confirmation du mot de passe
            if (confirmPassword.isEmpty()) {
                tiConfirmPasswordLayout.error = "Confirm Password must not be empty!"
                tiConfirmPasswordLayout.visibility = View.VISIBLE
                hasError = true
            }
            else if (password != confirmPassword) {
                tiConfirmPasswordLayout.error = "Passwords do not match!"
                tiConfirmPasswordLayout.visibility = View.VISIBLE
                hasError = true
            }
            else {
                tiConfirmPasswordLayout.error = null
                tiConfirmPasswordLayout.isErrorEnabled = false
            }

            // si il n'ya pas derreur passer a loginActivity
            if (!hasError) {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }

    // Afficher SnackBar
    private fun showSnackbar(message: String) {
        val rootView: View = findViewById(android.R.id.content)
        Snackbar.make(rootView, message, Snackbar.LENGTH_SHORT).show()
    }



    //validate FullName fucntion
    private fun isAlpha(text: String): Boolean {
        return text.matches("[a-zA-Z]+".toRegex())
    }

    // Validate email function
    private fun isValidEmail(email: String): Boolean {
        val regex = "^[^.]+\\.[^.]+$"
        return regex.toRegex().matches(email)
    }

    // Validate password function
    private fun isValidPassword(password: String): Boolean {
        val passwordRegex = "^(?=.*[A-Z])(?=.*\\d).{8,}$"
        return passwordRegex.toRegex().matches(password)
    }

}
