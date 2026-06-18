package com.example.basics.ui.fragment

import android.os.Bundle
import android.text.Editable
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.basics.R
import com.example.basics.databinding.FragmentManageAccountBinding
import com.example.basics.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class ManageAccountFragment : Fragment() {

    private lateinit var binding: FragmentManageAccountBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding=FragmentManageAccountBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val uid = FirebaseAuth.getInstance().currentUser!!.uid

        FirebaseFirestore.getInstance()
            .collection("users")
            .document(uid)
            .get()
            .addOnSuccessListener {
                val user = it.toObject(User::class.java)
                Log.d("ACCOUNT", "User Name: ${user?.name}")
                binding.mobileText.text=user?.phone
                binding.emailText.text=user?.email
                binding.nameText.setText(user?.name ?: "")
            }
            .addOnFailureListener {
                Log.e("ACCOUNT", "Error", it)
            }

        binding.saveBox.setOnClickListener {

            val updatedName = binding.nameText.text.toString().trim()

            if (updatedName.isEmpty()) {
                binding.nameText.error = "Name cannot be empty"
                return@setOnClickListener
            }

            FirebaseFirestore.getInstance()
                .collection("users")
                .document(uid)
                .update("name", updatedName)
                .addOnSuccessListener {
                    Toast.makeText(
                        requireContext(),
                        "Details updated successfully",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                .addOnFailureListener { e ->
                    Toast.makeText(
                        requireContext(),
                        "Failed to update: ${e.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
        }

    }


}