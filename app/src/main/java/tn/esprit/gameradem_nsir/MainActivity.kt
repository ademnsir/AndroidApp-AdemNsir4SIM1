package tn.esprit.gameradem_nsir


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

import android.widget.TextView
import android.view.View
import android.widget.ImageView
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout


class MainActivity : AppCompatActivity() {

    private lateinit var tiEmail: TextInputEditText
    private lateinit var tiPassword: TextInputEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        tiEmail = findViewById(R.id.tiEmail)
        tiPassword = findViewById(R.id.tiPassword)

        //navigationForgetpassword
        val varbtnForgetPassword: Button = findViewById(R.id.btnForgotPassword)
        varbtnForgetPassword.setOnClickListener {
            val intent = Intent(this, Forget_PasswordActivity::class.java)
            startActivity(intent)
        }

        //navigationRegisterNow
        val varbtnSignup: Button = findViewById(R.id.btnCreateAccount)
        varbtnSignup.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

        // Snackbar message for Facebook and Google buttons
        val btnFacebookLogin: ImageView = findViewById(R.id.btnFacebookLogin)
        val btnGoogleLogin: ImageView = findViewById(R.id.btnGoogleLogin)
        btnFacebookLogin.setOnClickListener {
            showSnackbar("Coming soon :)")
        }

        btnGoogleLogin.setOnClickListener {
            showSnackbar("Coming soon :)")
        }

        //bouton login
        val btnValidateEmail: Button = findViewById(R.id.btnLogin)
        //label derreur
        val tiEmailLayout: TextInputLayout = findViewById(R.id.tiEmailLayout)
        var tiPasswordLayout : TextInputLayout= findViewById(R.id.tiPasswordLayout)


        btnValidateEmail.setOnClickListener {
            val email = tiEmail.text.toString()
            val password = tiPassword.text.toString()
            var hasError = false // Variable pour suivre si une erreur s'est produite

            // Controle de saisie Email
            if (email.isEmpty()) {
                tiEmailLayout.error = "Email must not be empty!"
                tiEmailLayout.visibility = View.VISIBLE
                hasError = true
            } else if (!isValidEmail(email)) {
                tiEmailLayout.error = "Invalid email format!"
                tiEmailLayout.visibility = View.VISIBLE
                hasError = true // Une erreur s'est produite
            } else {
                tiEmailLayout.error = null
                tiEmailLayout.isErrorEnabled = false
            }

            // Controle de saisie  motdepasse
            if (password.isEmpty()) {
                tiPasswordLayout.error = "Password must not be empty!"
                tiPasswordLayout.visibility = View.VISIBLE
                hasError = true // Une erreur s'est produite

            } else if (!isValidPassword(password)) {
                tiPasswordLayout.error = "Password must contain at least one uppercase letter, one digit, and be at least 8 characters long."
                tiPasswordLayout.visibility = View.VISIBLE
                hasError = true // Une erreur s'est produite

            } else {
                tiPasswordLayout.error = null
                tiPasswordLayout.isErrorEnabled = false
            }

            // Si aucune erreur action je veux  limplementer  ici (homeActivity)
            if (!hasError) {

            }
        }


    }//endOncreate

    //afficher  snack bar
    private fun showSnackbar(message: String) {
        val rootView: View = findViewById(android.R.id.content)
        Snackbar.make(rootView, message, Snackbar.LENGTH_SHORT).show()
    }

    // Validate email function
    private fun isValidEmail(email: String): Boolean {
        val regex = "^[^.]+\\.[^.]+$"
        return regex.toRegex().matches(email)
    }
    //validate password fucntion
    private fun isValidPassword(password: String): Boolean {
        val passwordRegex = "^(?=.*[A-Z])(?=.*\\d).{8,}$"
        return passwordRegex.toRegex().matches(password)
    }

}