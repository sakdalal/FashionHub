package com.example.basics.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.basics.MyApplication
import com.example.basics.R
import com.example.basics.databinding.FragmentItemHistoryBinding
import kotlinx.coroutines.launch
import kotlin.text.replace


class ItemHistoryFragment : Fragment() {


    private lateinit var binding: FragmentItemHistoryBinding
    private var orderId: String = ""
    private var productId: Int = -1



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        orderId = arguments?.getString("orderId") ?: ""
        productId = arguments?.getInt("productId") ?: -1

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentItemHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val repository = (requireActivity().application as MyApplication).orderRepository


        lifecycleScope.launch {
            val item = repository.getOrderedItem(orderId,productId)

            item?.let {

                binding.brandText.text = it.brand
                binding.titleText.text = it.title
                binding.priceText.text = it.price
                binding.orderIdText.text=it.orderId
                binding.orderIdValue.text=it.orderId

                Glide.with(requireContext())
                    .load(it.thumbnail)
                    .into(binding.productImage)
            }

            val orderItems = repository.getItemsByOrderId(orderId)

            val totalPrice = orderItems.sumOf {
                it.price.replace("$", "").toDoubleOrNull() ?: 0.0
            }

            binding.totalPriceValue.text="$${totalPrice}"
        }

    }


}