package com.example.basics.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.basics.R
import com.google.firebase.auth.FirebaseAuth

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val user = FirebaseAuth.getInstance().currentUser

        if (user != null) {

            startActivity(
                Intent(
                    this,
                    MainActivity::class.java
                )
            )

        } else {

            startActivity(
                Intent(
                    this,
                    LoginActivity::class.java
                )
            )
        }

        finish()
    }


}