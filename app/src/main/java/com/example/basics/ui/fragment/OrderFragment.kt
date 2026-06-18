package com.example.basics.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.basics.MyApplication
import com.example.basics.R
import com.example.basics.adapter.OrderAdapter
import com.example.basics.databinding.ActivityCartBinding
import com.example.basics.databinding.FragmentOrderBinding
import com.example.basics.viewmodel.OrderViewModel
import com.example.basics.viewmodel.OrderViewModelFactory
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch

class OrderFragment : Fragment() {

    private lateinit var binding: FragmentOrderBinding
    private lateinit var adapter: OrderAdapter
    private lateinit var viewModel: OrderViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOrderBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val orderRepository =
            (requireActivity().application as MyApplication).orderRepository

        viewModel = ViewModelProvider(
            this,
            OrderViewModelFactory(orderRepository)
        )[OrderViewModel::class.java]

        adapter = OrderAdapter{ item ->

            val fragment = ItemHistoryFragment()

            fragment.arguments = Bundle().apply {
                putString("orderId", item.orderId)
                putInt("productId", item.id)
            }

            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, fragment)
                .addToBackStack(null)
                .commit()
        }

        val userId =
            FirebaseAuth.getInstance().currentUser?.uid ?: ""
        lifecycleScope.launch {
            viewModel.getOrdersByUserId(userId).collect { items ->

                items.forEach {
                    android.util.Log.d(
                        "ORDER_DEBUG",
                        "${it.title} -> ${it.orderId}"
                    )
                }

                adapter.submitList(items)
            }
        }

        binding.orderRecycler.layoutManager = LinearLayoutManager(requireContext())
        binding.orderRecycler.adapter = adapter


        lifecycleScope.launch {
            viewModel.getOrdersByUserId(userId).collect{ items ->
                adapter.submitList(items)

                Log.d("ORDER_SIZE", "Size = ${items.size}")

                if(items.isEmpty()){
                    binding.emptyOrders.root.visibility = View.VISIBLE
                    binding.orderRecycler.visibility= View.GONE
                    binding.lineBottom.visibility= View.GONE
                }else{
                    binding.emptyOrders.root.visibility = View.GONE
                    binding.orderRecycler.visibility= View.VISIBLE
                    binding.lineBottom.visibility = View.VISIBLE
                }
            }
        }


    }


}