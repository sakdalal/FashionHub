package com.example.basics.ui.activity

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import com.example.basics.R
import android.content.Context
import android.view.inputmethod.InputMethodManager
import com.example.basics.databinding.ActivityAddressBinding
import com.example.basics.ui.fragment.AddressFragment
import com.example.basics.ui.fragment.TagFragment
import com.example.basics.viewmodel.SharedAddressViewModel

class AddressActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddressBinding
    private val sharedViewModel: SharedAddressViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, true)
        binding=ActivityAddressBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, AddressFragment())
                .commit()
        }


        binding.commonTopBar.backArrow.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        binding.addressBottom.cancelBox.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        binding.addressBottom.saveBox.setOnClickListener {

//            if (!validateFields()) {
//                return@setOnClickListener
//            }

            sharedViewModel.onSaveClicked()

//            val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
//            imm.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
//
//            binding.saveAddressBox.visibility = View.VISIBLE
//            binding.saveAddressIcon.visibility = View.VISIBLE
//            binding.saveAddressText.visibility = View.VISIBLE
//
//            binding.root.postDelayed({
//                binding.saveAddressBox.visibility = View.GONE
//                binding.saveAddressIcon.visibility= View.GONE
//                binding.saveAddressText.visibility= View.GONE
//                onBackPressedDispatcher.onBackPressed()
//            }, 2000)


        }


        sharedViewModel.addressSaved.observe(this) {

            val imm = getSystemService(
                Context.INPUT_METHOD_SERVICE
            ) as InputMethodManager

            currentFocus?.let {
                imm.hideSoftInputFromWindow(it.windowToken, 0)
            }

            binding.saveAddressBox.visibility = View.VISIBLE
            binding.saveAddressIcon.visibility = View.VISIBLE
            binding.saveAddressText.visibility = View.VISIBLE

            binding.root.postDelayed({

                binding.saveAddressBox.visibility = View.GONE
                binding.saveAddressIcon.visibility = View.GONE
                binding.saveAddressText.visibility = View.GONE

                onBackPressedDispatcher.onBackPressed()

            }, 2000)
        }


    }
}