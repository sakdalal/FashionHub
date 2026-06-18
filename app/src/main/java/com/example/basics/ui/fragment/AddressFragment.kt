package com.example.basics.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.basics.R
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.example.basics.MyApplication
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.example.basics.databinding.FragmentAddressBinding
import com.example.basics.db.AddressEntity
import com.example.basics.viewmodel.AddressViewModel
import com.example.basics.viewmodel.AddressViewModelFactory
import com.example.basics.viewmodel.OrderViewModel
import com.example.basics.viewmodel.OrderViewModelFactory
import com.example.basics.viewmodel.SharedAddressViewModel
import com.google.firebase.auth.FirebaseAuth


class AddressFragment : Fragment() {


    private lateinit var binding: FragmentAddressBinding
    private val sharedViewModel: SharedAddressViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddressBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val addressRepository = (requireActivity().application as MyApplication).addressRepository
        val addressViewModel = ViewModelProvider(
            this,
            AddressViewModelFactory(addressRepository)
        )[AddressViewModel::class.java]


        setupValidation(binding.nameText, binding.nameLayout, binding.nameDivider, "Name")
        setupValidation(binding.mobileText, binding.mobileLayout, binding.mobileDivider, "Mobile")
        setupValidation(
            binding.pincodeText,
            binding.pincodeLayout,
            binding.pincodeDivider,
            "Pincode"
        )
        setupValidation(binding.stateText, binding.stateLayout, binding.stateDivider, "State")
        setupValidation(
            binding.addressText,
            binding.addressLayout,
            binding.addressDivider,
            "Address (Building, Street, Area)"
        )
        setupValidation(
            binding.cityText,
            binding.cityLayout,
            binding.cityDivider,
            "House Number/Tower/Block"
        )
        setupValidation(
            binding.houseText,
            binding.houseLayout,
            binding.houseDivider,
            "City/District"
        )


        val colorStateList =
            ContextCompat.getColorStateList(requireContext(), R.color.radio_button_color)

        binding.homeRadioBtn.buttonTintList = colorStateList
        binding.officeRadioBtn.buttonTintList = colorStateList



        sharedViewModel.saveClicked.observe(viewLifecycleOwner) {

            if (!validateFields()) {
                return@observe
            }

            val address = AddressEntity(
                id = 0,
                userId = FirebaseAuth.getInstance().currentUser?.uid ?: "",
                name = binding.nameText.text.toString(),
                mobile = binding.mobileText.text.toString(),
                pincode = binding.pincodeText.text.toString(),
                state = binding.stateText.text.toString(),
                address = binding.addressText.text.toString(),
                city = binding.cityText.text.toString(),
                house = binding.houseText.text.toString(),
                addressType = if (binding.homeRadioBtn.isChecked)
                    "Home"
                else
                    "Office",
                defaultAddress = binding.defaultAddressCheckBox.isChecked
            )

            addressViewModel.insertAddress(address)

            sharedViewModel.onAddressSaved()


        }

    }


    private fun setupValidation(
        editText: TextInputEditText,
        layout: TextInputLayout,
        divider: View,
        hint: String
    ) {

        editText.setOnFocusChangeListener { _, hasFocus ->

            if (hasFocus) {

                layout.isHintEnabled = true
                editText.hint = null

                divider.setBackgroundColor(
                    ContextCompat.getColor(requireContext(), R.color.focus_stroke)
                )

                layout.error = null

            } else {

                val text = editText.text.toString().trim()

                if (text.isEmpty()) {
                    divider.setBackgroundColor(
                        ContextCompat.getColor(requireContext(), R.color.error_stroke)
                    )
                    layout.error = "Required"
                    layout.isHintEnabled = false
                    editText.hint = hint
                } else {
                    divider.setBackgroundColor(
                        ContextCompat.getColor(requireContext(), R.color.default_stroke)
                    )
                    layout.error = null
                    layout.isHintEnabled = true
                }
            }
        }
    }

    private fun validateFields(): Boolean {

        val name = binding.nameText.text.toString().trim()
        val mobile = binding.mobileText.text.toString().trim()
        val address = binding.addressText.text.toString().trim()
        val city = binding.cityText.text.toString().trim()
        val state = binding.stateText.text.toString().trim()
        val pincode = binding.pincodeText.text.toString().trim()
        val house = binding.houseText.text.toString().trim()

        var isValid = true

        if (name.isEmpty()) {
            binding.nameLayout.error = "Name is required"
            isValid = false
        } else {
            binding.nameLayout.error = null
        }

        if (mobile.isEmpty()) {
            binding.mobileLayout.error = "Mobile number is required"
            isValid = false
        } else {
            binding.mobileLayout.error = null
        }

        if (address.isEmpty()) {
            binding.addressLayout.error = "Address is required"
            isValid = false
        } else {
            binding.addressLayout.error = null
        }

        if (city.isEmpty()) {
            binding.cityLayout.error = "City is required"
            isValid = false
        } else {
            binding.cityLayout.error = null
        }

        if (state.isEmpty()) {
            binding.stateLayout.error = "State is required"
            isValid = false
        } else {
            binding.stateLayout.error = null
        }

        if (pincode.isEmpty()) {
            binding.pincodeLayout.error = "Pincode is required"
            isValid = false
        } else {
            binding.pincodeLayout.error = null
        }

        if (house.isEmpty()) {
            binding.houseLayout.error = "House/Building is required"
            isValid = false
        } else {
            binding.houseLayout.error = null
        }

        return isValid
    }

}