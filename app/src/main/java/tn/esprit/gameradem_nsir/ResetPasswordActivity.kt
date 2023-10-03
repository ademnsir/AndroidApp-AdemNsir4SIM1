package tn.esprit.gameradem_nsir
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout


class ResetPasswordActivity : AppCompatActivity() {



    private lateinit var tiPassword: TextInputEditText
    private lateinit var tiConfirmPassword: TextInputEditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset_password)


//bouton Return
        val varbtnReturn: Button = findViewById(R.id.btnReturn)
        varbtnReturn.setOnClickListener {
            val intent = Intent(this, OtpValidationActivity::class.java)

            startActivity(intent)


        }

        tiPassword = findViewById(R.id.tiPassword)
        tiConfirmPassword = findViewById(R.id.tiConfirmPassword)


        // Bouton login
        val varbtnSubmit: Button = findViewById(R.id.btnSubmit)

        // Labels d'erreur

        val tiPasswordLayout: TextInputLayout = findViewById(R.id.tiPasswordLayout)
        val tiConfirmPasswordLayout: TextInputLayout = findViewById(R.id.tiConfirmPasswordLayout)


        varbtnSubmit.setOnClickListener {

            val password = tiPassword.text.toString()
            val confirmPassword = tiConfirmPassword.text.toString()

            var hasError = false


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

            else {
                // Afficher un Snackbar avec un message d'erreur
                showSnackbar("You have  some errors in your inputs!")
            }
        }
    }

    // Afficher SnackBar
    private fun showSnackbar(message: String) {
        val rootView: View = findViewById(android.R.id.content)
        Snackbar.make(rootView, message, Snackbar.LENGTH_SHORT).show()
    }


    // Validate password function
    private fun isValidPassword(password: String): Boolean {
        val passwordRegex = "^(?=.*[A-Z])(?=.*\\d).{8,}$"
        return passwordRegex.toRegex().matches(password)
    }

}