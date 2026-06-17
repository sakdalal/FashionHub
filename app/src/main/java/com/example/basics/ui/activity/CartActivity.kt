package com.example.basics.ui.activity

import android.R
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.basics.MyApplication
import com.example.basics.adapter.CartAdapter
import com.example.basics.databinding.ActivityCartBinding
import com.example.basics.databinding.RemoveFromBagBinding
import com.example.basics.db.CartEntity
import com.example.basics.db.WishlistEntity
import com.example.basics.viewmodel.CartViewModel
import com.example.basics.viewmodel.CartViewModelFactory
import com.example.basics.viewmodel.OrderViewModel
import com.example.basics.viewmodel.OrderViewModelFactory
import com.example.basics.viewmodel.WishlistViewModel
import com.example.basics.viewmodel.WishlistViewModelFactory
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.coroutines.launch


class CartActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCartBinding
    private lateinit var adapter: CartAdapter
    private lateinit var viewModel: CartViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)
        WindowCompat.setDecorFitsSystemWindows(window, true)

        val repository =
            (application as MyApplication).cartRepository

        val orderRepository =
            (application as MyApplication).orderRepository

        val orderViewModel = ViewModelProvider(
            this,
            OrderViewModelFactory(orderRepository)
        )[OrderViewModel::class.java]

        viewModel = ViewModelProvider(
            this,
            CartViewModelFactory(repository,orderRepository)
        )[CartViewModel::class.java]

        adapter = CartAdapter { item ->
            showBottomSheet(item)
        }
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.cartItems.collect { items ->
                    Log.d("CART", "Items count = ${items.size}")
                    adapter.submitList(items)

                    if (items.isEmpty()) {
                        binding.emptyCartLayout.root.visibility = View.VISIBLE
                        binding.recyclerView.visibility = View.GONE
                        binding.priceDetails.visibility = View.GONE
                        binding.priceDetailView.visibility = View.GONE
                        binding.mrp.visibility= View.GONE
                        binding.mrpValue.visibility= View.GONE
                        binding.platformFee.visibility= View.GONE
                        binding.platformFeeValue.visibility= View.GONE
                        binding.totalAmount.visibility= View.GONE
                        binding.totalAmountValue.visibility= View.GONE
                        binding.placeOrder.visibility=View.GONE
                    } else {
                        binding.emptyCartLayout.root.visibility = View.GONE
                        binding.recyclerView.visibility = View.VISIBLE
                        val totalPrice =
                            items.sumOf { it.price.replace("$", "").toDoubleOrNull() ?: 0.0 }
                        binding.mrpValue.text = "₹%.2f".format(totalPrice)
                        binding.totalAmountValue.text = "₹%.2f".format(totalPrice)
                    }

                }
            }
        }
        binding.backArrow.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        binding.wishlistIcon.setOnClickListener {
            val intent = Intent(this, WishlistActivity::class.java)
            startActivity(intent)
        }

        binding.placeOrder.setOnClickListener {
            viewModel.placeOrder()
        }

    }


    private fun showBottomSheet(item: CartEntity) {

        val repository = (application as MyApplication).wishlistRepository

        val wishlistViewModel = ViewModelProvider(
            this,
            WishlistViewModelFactory(repository)
        )[WishlistViewModel::class.java]

        val wishlistItem= WishlistEntity(
            id=item.id,
            brand = item.brand,
            rating = item.rating,
            price = item.price,
            discountPercentage = item.discountPercentage,
            thumbnail = item.thumbnail,
            title = item.title,
            returnPolicy = item.returnPolicy
        )

        val dialog = BottomSheetDialog(this)
        val sheetBinding = RemoveFromBagBinding.inflate(layoutInflater)
        Glide.with(sheetBinding.root)
            .load(wishlistItem.thumbnail)
            .into(sheetBinding.productImg)

        dialog.setContentView(sheetBinding.root)

        sheetBinding.removeText.setOnClickListener {
            viewModel.removeFromCart(item.id)
            dialog.dismiss()
        }

        sheetBinding.wishlistText.setOnClickListener {
            wishlistViewModel.addToWishlist(wishlistItem)
            viewModel.removeFromCart(item.id)
            dialog.dismiss()
        }

        sheetBinding.crossIcon.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }

}

