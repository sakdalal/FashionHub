package com.example.basics.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.basics.MyApplication
import com.example.basics.R
import com.example.basics.adapter.AddressAdapter
import com.example.basics.databinding.DeleteAddressBinding
import com.example.basics.databinding.FragmentNewAddressBinding
import com.example.basics.db.AddressEntity
import com.example.basics.event.AddressEvent
import com.example.basics.viewmodel.AddressViewModel
import com.example.basics.viewmodel.AddressViewModelFactory
import com.example.basics.viewmodel.WishlistViewModel
import com.example.basics.viewmodel.WishlistViewModelFactory
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch

class NewAddressFragment : Fragment() {


    private lateinit var binding: FragmentNewAddressBinding
    private lateinit var adapter: AddressAdapter
    private lateinit var viewModel: AddressViewModel

    private var defaultAddress: AddressEntity? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewAddressBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = AddressAdapter { event ->

            when (event) {

                is AddressEvent.Edit -> {
                    val fragment = AddressFragment()
                    fragment.arguments = Bundle().apply {
                        putInt("address_id", event.address.id)
                    }
                    requireActivity().supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainer, fragment)
                        .addToBackStack(null)
                        .commit()

                }

                is AddressEvent.Delete -> {
                    showDeleteBottomSheet(address = event.address)
//                    viewModel.deleteAddressById(event.address.id)
                }

                is AddressEvent.SetDefault -> {
                    viewModel.setDefaultAddress(event.address)
                }

            }
        }


        binding.recyclerView.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        binding.recyclerView.adapter = adapter

        val repository = (requireActivity().application as MyApplication).addressRepository
        viewModel = ViewModelProvider(
            this,
            AddressViewModelFactory(repository)
        )[AddressViewModel::class.java]

        val userId = FirebaseAuth.getInstance().currentUser?.uid ?: ""



        viewLifecycleOwner.lifecycleScope.launch {

            viewModel.getAddressesByUserId(userId)
                .collect { addresses ->

                    defaultAddress = addresses.find { it.defaultAddress }

                    val otherAddresses = addresses.filter { !it.defaultAddress }

                    binding.defaultAddress.makeDefault.visibility = View.GONE

                    if (defaultAddress == null) {
                        binding.addressType.text = "NO DEFAULT ADDRESS PRESENT"
                        binding.defaultAddress.root.visibility = View.GONE
                    } else {

                        binding.defaultAddress.root.visibility = View.VISIBLE

                        defaultAddress?.let { address ->
                            binding.addressType.text = "DEFAULT ADDRESS"
                            binding.defaultAddress.name.text = address.name
                            binding.defaultAddress.house.text = address.house
                            binding.defaultAddress.address.text = address.address
                            binding.defaultAddress.city.text = address.city
                            binding.defaultAddress.pincode.text = address.pincode
                            binding.defaultAddress.state.text = address.state
                            binding.defaultAddress.mobile.text = "Mobile: ${address.mobile}"
                            binding.defaultAddress.homeOrOffice.text = address.addressType

                        }

                    }
                    adapter.submitList(otherAddresses)
                }

        }

        binding.defaultAddress.editDeleteAddress.removeBoxText.setOnClickListener {

            val dialog= BottomSheetDialog(requireContext())
            val sheetBinding= DeleteAddressBinding.inflate(layoutInflater)

            dialog.setContentView(sheetBinding.root)

            sheetBinding.deleteText.setOnClickListener {
                defaultAddress?.let { address ->
                    viewModel.deleteAddress(address)
                    binding.defaultAddress.root.visibility = View.GONE
                    binding.addressType.text = "NO DEFAULT ADDRESS PRESENT"
                }
                dialog.dismiss()
            }
            sheetBinding.cancelText.setOnClickListener {
                dialog.dismiss()
            }
            dialog.show()
        }






        binding.defaultAddress.editDeleteAddress.editBoxText.setOnClickListener {
            defaultAddress?.let { address ->

                val fragment = AddressFragment()

                fragment.arguments = Bundle().apply {
                    putInt("address_id", address.id)
                }

                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainer, fragment)
                    .addToBackStack(null)
                    .commit()
            }
        }


        binding.addOption.setOnClickListener {

            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, AddressFragment())
                .addToBackStack(null)
                .commit()

        }

        binding.defaultAddress.root.setOnClickListener {
            binding.defaultAddress.clickVisible.visibility = View.VISIBLE
            binding.defaultAddress.editDeleteAddress.root.visibility = View.VISIBLE
        }

        binding.recyclerView.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        binding.recyclerView.adapter = adapter


    }


    private fun showDeleteBottomSheet(address: AddressEntity){
        val dialog= BottomSheetDialog(requireContext())
        val sheetBinding= DeleteAddressBinding.inflate(layoutInflater)

        val view = layoutInflater.inflate(
            R.layout.delete_address,
            null
        )

        dialog.setContentView(sheetBinding.root)

        val btnDelete = view.findViewById<TextView>(R.id.deleteText)
        val btnCancel = view.findViewById<TextView>(R.id.cancelText)

        btnDelete.setOnClickListener {
            viewModel.deleteAddressById(address.id)
            dialog.dismiss()
        }

        btnCancel.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }


}