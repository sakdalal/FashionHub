package com.example.basics.ui.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.basics.MyApplication
import com.example.basics.R
import com.example.basics.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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
            val intent=Intent(this, SignupActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
            startActivity(intent)
        }

    }

    private fun loginUser() {

        val email = binding.emailText.text.toString().trim()

        val password = binding.passwordText.text.toString().trim()

        auth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {

                lifecycleScope.launch {

                    withContext(Dispatchers.IO) {
                        (application as MyApplication)
                            .database
                            .clearAllTables()
                    }

                    Toast.makeText(
                        this@LoginActivity,
                        "Login Successful",
                        Toast.LENGTH_SHORT
                    ).show()

                    val intent = Intent(
                        this@LoginActivity,
                        MainActivity::class.java
                    )

                    intent.flags =
                        Intent.FLAG_ACTIVITY_NEW_TASK or
                                Intent.FLAG_ACTIVITY_CLEAR_TASK

                    startActivity(intent)
                }
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