package com.example.basics.ui.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.basics.MyApplication
import com.example.basics.adapter.WishlistAdapter
import com.example.basics.databinding.ActivityWishlistBinding
import com.example.basics.databinding.CustomToastBinding
import com.example.basics.db.CartEntity
import com.example.basics.db.WishlistEntity
import com.example.basics.viewmodel.CartViewModel
import com.example.basics.viewmodel.CartViewModelFactory
import com.example.basics.viewmodel.OrderViewModel
import com.example.basics.viewmodel.OrderViewModelFactory
import com.example.basics.viewmodel.WishlistViewModel
import com.example.basics.viewmodel.WishlistViewModelFactory
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class WishlistActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWishlistBinding
    private lateinit var adapter: WishlistAdapter
    private lateinit var viewModel: WishlistViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, true)

        binding = ActivityWishlistBinding.inflate(layoutInflater)

        val repository =
            (application as MyApplication).wishlistRepository

        viewModel = ViewModelProvider(
            this,
            WishlistViewModelFactory(repository)
        )[WishlistViewModel::class.java]

        setContentView(binding.root)

        adapter = WishlistAdapter ({ item ->
            viewModel.removeFromWishlist(item.id)
        },{item->moveToBag(item)})
        binding.wishlistRecycler.layoutManager =
            GridLayoutManager(this, 2)
        binding.wishlistRecycler.adapter = adapter

        lifecycleScope.launch {
            viewModel.wishlistItems.collect { items ->
                Log.d("WISHLIST", "Items count = ${items.size}")
                adapter.submitList(items)

                if (items.isEmpty()) {
                    binding.wishlistRecycler.visibility = View.GONE
                    binding.emptyWishlistLayout.root.visibility = View.VISIBLE

                } else {
                    binding.wishlistRecycler.visibility = View.VISIBLE
                    binding.emptyWishlistLayout.root.visibility = View.GONE
                }
            }
        }

        binding.backArrow.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        binding.addToBagImg.setOnClickListener {
            val intent= Intent(this, CartActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
            startActivity(intent)
        }


    }


    private fun moveToBag(item: WishlistEntity){

        val repository = (application as MyApplication).cartRepository

        val orderRepository =
            (application as MyApplication).orderRepository
        val orderViewModel = ViewModelProvider(
            this,
            OrderViewModelFactory(orderRepository)
        )[OrderViewModel::class.java]

        val cartViewModel = ViewModelProvider(
            this,
            CartViewModelFactory(repository,orderRepository)
        )[CartViewModel::class.java]

        val cartItem = CartEntity(
            id = item.id,
            rating = item.rating,
            brand = item.brand,
            title = item.title,
            price = item.price,
            discountPercentage = item.discountPercentage,
            returnPolicy = item.returnPolicy,
            thumbnail = item.thumbnail
        )

        val dialog = BottomSheetDialog(this)
        val toastBinding= CustomToastBinding.inflate(layoutInflater)
        toastBinding.message.text="Moved to bag successfully!"
        dialog.setContentView(toastBinding.root)

        cartViewModel.addToCart(cartItem)
        dialog.show()
        viewModel.removeFromWishlist(item.id)
        lifecycleScope.launch {
            delay(2000)
            dialog.dismiss()
        }
    }



}