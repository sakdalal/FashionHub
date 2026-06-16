package com.example.basics.ui.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.basics.R
import com.example.basics.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)

        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        binding.loginBtn.setOnClickListener {

            loginUser()
        }

        binding.signupClick.setOnClickListener {
            startActivity(Intent(this, SignupActivity::class.java))
        }

    }

    private fun loginUser() {

        val email = binding.emailText.text.toString().trim()

        val password = binding.passwordText.text.toString().trim()

        auth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {

                Toast.makeText(
                    this,
                    "Login Successful",
                    Toast.LENGTH_SHORT
                ).show()

                startActivity(
                    Intent(
                        this,
                        MainActivity::class.java
                    )
                )

                finish()
            }
            .addOnFailureListener {

                Toast.makeText(
                    this,
                    it.message,
                    Toast.LENGTH_SHORT
                ).show()
            }
    }
}