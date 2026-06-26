package com.example.basics.ui.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.basics.R
import com.example.basics.databinding.ActivitySignupBinding
import com.example.basics.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class SignupActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignupBinding

    private lateinit var auth: FirebaseAuth

    private lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding= ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        firestore = FirebaseFirestore.getInstance()

        binding.signupBtn.setOnClickListener {
            signupUser()
        }

    }

    private fun signupUser() {

        val name = binding.nameText.text.toString().trim()

        val phone = binding.phoneText.text.toString().trim()

        val email = binding.emailText.text.toString().trim()

        val password = binding.passwordText.text.toString().trim()

        if (name.isEmpty() ||
            phone.isEmpty() ||
            email.isEmpty() ||
            password.isEmpty()
        ) {

            Toast.makeText(this,
                "Fill all fields",
                Toast.LENGTH_SHORT
            ).show()

            return
        }

        auth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener {

                val uid = auth.currentUser!!.uid

                val user = User(
                    uid,
                    name,
                    phone,
                    email
                )

                firestore.collection("users")
                    .document(uid)
                    .set(user)
                    .addOnSuccessListener {
                        Toast.makeText(
                            this,
                            "Signup Successful",
                            Toast.LENGTH_SHORT
                        ).show()
                        startActivity(
                            Intent(
                                this,
                                LoginActivity::class.java
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
            .addOnFailureListener {

                Toast.makeText(
                    this,
                    it.message,
                    Toast.LENGTH_SHORT
                ).show()
            }
    }
}