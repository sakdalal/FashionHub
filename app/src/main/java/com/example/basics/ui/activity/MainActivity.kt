package com.example.basics.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import com.example.basics.R
import com.example.basics.databinding.ActivityMainBinding
import com.example.basics.listener.OnProductClickListener
import com.example.basics.model.Product
import com.example.basics.ui.fragment.TagFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, true)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, TagFragment())
                .commit()
        }

        binding.topBar.wishlistIcon.setOnClickListener {
            val intent = Intent(this, WishlistActivity::class.java)
            startActivity(intent)
        }

        binding.footer.bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.cart->{
                    val intent = Intent(this, CartActivity::class.java)
                    startActivity(intent)
                    true
                }
                else -> {
                    false
                }
            }
        }


    }
}