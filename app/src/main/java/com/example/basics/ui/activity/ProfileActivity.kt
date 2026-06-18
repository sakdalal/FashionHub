package com.example.basics.ui.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.basics.R
import com.example.basics.databinding.ActivityCartBinding
import com.example.basics.databinding.ActivityProfileBinding
import com.example.basics.model.User
import com.example.basics.ui.fragment.ItemHistoryFragment
import com.example.basics.ui.fragment.ManageAccountFragment
import com.example.basics.ui.fragment.OrderFragment
import com.example.basics.ui.fragment.ProfileFragment
import com.example.basics.ui.fragment.TagFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding=ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, ProfileFragment())
                .commit()
        }


        binding.commonTopBar.backArrow.setOnClickListener {
            if (supportFragmentManager.backStackEntryCount > 0) {
                supportFragmentManager.popBackStack()
            } else {
                finish()
            }
        }

        supportFragmentManager.registerFragmentLifecycleCallbacks(
            object : androidx.fragment.app.FragmentManager.FragmentLifecycleCallbacks() {

                override fun onFragmentResumed(
                    fm: androidx.fragment.app.FragmentManager,
                    f: androidx.fragment.app.Fragment
                ) {

                    binding.commonTopBar.headText.text =
                        when (f) {
                            is ProfileFragment -> "Profile"
                            is OrderFragment -> "My Orders"
                            is ItemHistoryFragment -> "Order Details"
                            is ManageAccountFragment -> "Manage Your Account"
                            else -> "Profile"
                        }
                }
            },
            true
        )

    }
}