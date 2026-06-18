package com.example.basics.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.basics.R
import com.example.basics.databinding.FragmentProfileBinding
import com.example.basics.model.User
import com.example.basics.ui.activity.AddressActivity
import com.example.basics.ui.activity.LoginActivity
import com.example.basics.ui.activity.WishlistActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore


class ProfileFragment : Fragment() {


    private lateinit var binding: FragmentProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding= FragmentProfileBinding.inflate(inflater,container,false)
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
                Log.d("PROFILE", "User Name: ${user?.name}")
                binding.profileName.text = "Hey ${user?.name}!"
            }
            .addOnFailureListener {
                Log.e("PROFILE", "Error", it)
            }

        binding.wishlistArrow.setOnClickListener {
            startActivity(Intent(requireContext(), WishlistActivity::class.java))
        }

        binding.logoutBtn.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(requireContext(), LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            requireActivity().finish()
        }

        binding.orderArrow.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, OrderFragment())
                .addToBackStack(null)
                .commit()
        }

        binding.manageArrow.setOnClickListener {
            binding.manageArrowTwo.visibility= View.VISIBLE
            binding.manageArrow.visibility= View.GONE
            binding.manageExtra.root.visibility= View.VISIBLE
        }

        binding.manageArrowTwo.setOnClickListener {
            binding.manageArrowTwo.visibility= View.GONE
            binding.manageArrow.visibility= View.VISIBLE
            binding.manageExtra.root.visibility= View.GONE
        }

        binding.manageExtra.accountDetailsBox.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, ManageAccountFragment())
                .addToBackStack(null)
                .commit()
        }

        binding.manageExtra.addressBox.setOnClickListener {
            startActivity(Intent(requireContext(), AddressActivity::class.java))
        }


    }

}