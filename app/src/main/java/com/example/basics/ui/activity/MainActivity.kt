package com.example.basics.ui.activity

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.MediaStore
import android.speech.RecognizerIntent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.fragment.app.FragmentManager
import com.example.basics.R
import com.example.basics.adapter.ProductAdapter
import com.example.basics.databinding.ActivityMainBinding
import com.example.basics.listener.OnProductClickListener
import com.example.basics.model.Product
import com.example.basics.ui.fragment.SearchFragment
import com.example.basics.ui.fragment.TagFragment
import com.example.basics.viewmodel.HomeViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, true)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, TagFragment())
                .commit()
        }


        binding.footer.bottomNav.selectedItemId = R.id.home

        binding.footer.bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> true
                R.id.wishlist -> {
                    val intent = Intent(this, WishlistActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
                    startActivity(intent)
                    true
                }
                R.id.bag -> {
                    val intent = Intent(this, CartActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
                    startActivity(intent)
                    true
                }
                R.id.profile -> {
                    val intent = Intent(this, ProfileActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
                    startActivity(intent)
                    true
                }


                else -> {
                    false
                }
            }
        }


        binding.topBar.cameraIcon.setOnClickListener {
            if (
                ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.CAMERA
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                openCamera()
            } else {
                cameraPermissionLauncher.launch(
                    Manifest.permission.CAMERA
                )
            }
        }

        binding.topBar.mikeIcon.setOnClickListener {
            if (
                ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.RECORD_AUDIO
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                startVoiceRecognition()
            } else {

                microphonePermissionLauncher.launch(
                    Manifest.permission.RECORD_AUDIO
                )
            }
        }


        binding.topBar.searchArea.setOnEditorActionListener { v, actionId, _ ->

            if (
                actionId == EditorInfo.IME_ACTION_SEARCH ||
                actionId == EditorInfo.IME_ACTION_DONE
            ) {

                val query = v.text.toString()

                val results = homeViewModel.search(query)

                if (results.isNotEmpty()) {

                    supportFragmentManager.popBackStack(
                        "SEARCH",
                        FragmentManager.POP_BACK_STACK_INCLUSIVE
                    )

                    supportFragmentManager.beginTransaction()
                        .replace(
                            R.id.fragmentContainer,
                            SearchFragment()
                        )
                        .addToBackStack("SEARCH")
                        .commit()
                        binding.footer.root.visibility= View.GONE
                } else {

                    Toast.makeText(
                        this,
                        "No products found",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                true

            } else {
                false
            }
        }


    }

    private val cameraPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted ->

            if (isGranted) {
                Toast.makeText(
                    this,
                    "Camera Permission Granted",
                    Toast.LENGTH_SHORT
                ).show()
                openCamera()

            } else {

                Toast.makeText(
                    this,
                    "Camera Permission Denied",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

    private val microphonePermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted ->

            if (isGranted) {

                Toast.makeText(
                    this,
                    "Microphone Permission Granted",
                    Toast.LENGTH_SHORT
                ).show()
                startVoiceRecognition()

            } else {

                Toast.makeText(
                    this,
                    "Microphone Permission Denied",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

    private fun openCamera() {

        val intent = Intent(
            MediaStore.ACTION_IMAGE_CAPTURE
        )

        startActivity(intent)
    }

    private fun startVoiceRecognition() {

        try {

            Toast.makeText(
                this,
                "Opening Voice Recognition",
                Toast.LENGTH_SHORT
            ).show()

            val intent = Intent(
                RecognizerIntent.ACTION_RECOGNIZE_SPEECH
            )
            intent.putExtra(
                RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
            )
            intent.putExtra(
                RecognizerIntent.EXTRA_PROMPT,
                "Speak now..."
            )
            voiceRecognitionLauncher.launch(intent)

        }catch (e: Exception){
            Toast.makeText(
                this,
                "Speech recognizition not supported",
                Toast.LENGTH_SHORT
            ).show()
        }

    }

    private val voiceRecognitionLauncher =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->

            if (result.resultCode == RESULT_OK && result.data != null) {
                val matches = result.data?.getStringArrayListExtra(
                    RecognizerIntent.EXTRA_RESULTS
                )
                if (!matches.isNullOrEmpty()) {
                    val spokenText = matches[0]
                    binding.topBar.searchArea.setText(spokenText)
                    Toast.makeText(
                        this,
                        "You said: $spokenText",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

    override fun onResume() {
        super.onResume()
        binding.footer.bottomNav.selectedItemId = R.id.home
    }

}