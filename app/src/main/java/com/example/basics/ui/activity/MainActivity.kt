package com.example.basics.ui.activity

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.MediaStore
import android.speech.RecognizerIntent
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import com.example.basics.R
import com.example.basics.databinding.ActivityMainBinding
import com.example.basics.listener.OnProductClickListener
import com.example.basics.model.Product
import com.example.basics.ui.fragment.TagFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
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

        binding.topBar.wishlistIcon.setOnClickListener {
            val intent = Intent(this, WishlistActivity::class.java)
            startActivity(intent)
        }

        binding.footer.bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.cart -> {
                    val intent = Intent(this, CartActivity::class.java)
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

}