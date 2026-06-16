package com.example.basics.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.basics.MyApplication
import com.example.basics.databinding.ActivityProductDetailBinding
import com.example.basics.databinding.CustomToastBinding
import com.example.basics.db.CartEntity
import com.example.basics.db.WishlistEntity
import com.example.basics.model.FeatureBrand
import com.example.basics.viewmodel.CartViewModel
import com.example.basics.viewmodel.CartViewModelFactory
import com.example.basics.viewmodel.WishlistViewModel
import com.example.basics.viewmodel.WishlistViewModelFactory
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ProductDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProductDetailBinding
    private lateinit var cartViewModel: CartViewModel

    private var isInCart = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, true)

        binding =
            ActivityProductDetailBinding.inflate(
                layoutInflater
            )

        setContentView(binding.root)

        @Suppress("DEPRECATION")
        val product =
            intent.getSerializableExtra(
                "PRODUCT"
            ) as FeatureBrand

        binding.brandName.text = product.brand
        binding.brandTitle.text = product.title
        binding.rating.text = product.rating
        binding.price.text = product.price
        binding.discount.text = product.discountPercentage
        binding.shippingBy.text = product.shippingInformation
        binding.descriptionText.text = product.description
        binding.stockText.text = product.availabilityStatus
        binding.warrantyText.text = product.warrantyInformation
        binding.returnText.text = product.returnPolicy
        Glide.with(this)
            .load(product.thumbnail)
            .into(binding.productImage)


        val repository =
            (application as MyApplication).cartRepository
        cartViewModel = ViewModelProvider(
            this,
            CartViewModelFactory(repository)
        )[CartViewModel::class.java]

        val wishlistRepository =
            (application as MyApplication).wishlistRepository
        val wishlistViewModel = ViewModelProvider(
            this,
            WishlistViewModelFactory(wishlistRepository)
        )[WishlistViewModel::class.java]


        lifecycleScope.launch {
            wishlistViewModel.wishlistItems.collect { items ->

                val exists = items.any { it.id == product.id }

                binding.wishlistText.text =
                    if (exists) {
                        "Wishlisted"
                    } else {
                        "Add to Wishlist"
                    }
            }
        }

        lifecycleScope.launch {
            cartViewModel.cartItems.collect { items ->

                val inCart = items.any { it.id == product.id }
                isInCart = items.any { it.id == product.id }
                binding.bagText.text =
                    if (inCart) {
                        "Go to Cart"
                    } else {
                        "Add to Cart"
                    }
            }
        }


        binding.addToBag.setOnClickListener {
            if (isInCart) {
                startActivity(Intent(this, CartActivity::class.java))
            } else {
                val cartItem = CartEntity(
                    id = product.id,
                    rating = product.rating,
                    brand = product.brand,
                    title = product.title,
                    price = product.price,
                    discountPercentage = product.discountPercentage,
                    returnPolicy = product.returnPolicy,
                    thumbnail = product.thumbnail
                )
                val dialog = BottomSheetDialog(this@ProductDetailActivity)
                val toastBinding = CustomToastBinding.inflate(layoutInflater)
                lifecycleScope.launch {

                    if (cartViewModel.isAddedToCart(product.id)) {
                        toastBinding.message.text = "Item already in bag"
                        dialog.setContentView(toastBinding.root)
                        dialog.show()
                        delay(2000)
                        dialog.dismiss()
                    } else {
                        cartViewModel.addToCart(cartItem)
                        toastBinding.message.text = "Moved to bag successfully!"
                        dialog.setContentView(toastBinding.root)
                        dialog.show()
                        delay(2000)
                        dialog.dismiss()
                        binding.bagText.text = "Go to Bag"
                    }
                }
            }
        }


        //Wishlist
        binding.buyNow.setOnClickListener {
            val wishlistItem = WishlistEntity(
                id = product.id,
                rating = product.rating,
                brand = product.brand,
                title = product.title,
                price = product.price,
                discountPercentage = product.discountPercentage,
                returnPolicy = product.returnPolicy,
                thumbnail = product.thumbnail
            )
            val dialog = BottomSheetDialog(this@ProductDetailActivity)
            val toastBinding = CustomToastBinding.inflate(layoutInflater)
            lifecycleScope.launch {
                if (wishlistViewModel.isWishlisted(product.id)) {
                    toastBinding.message.text = "Item already in wishlist"
                    dialog.setContentView(toastBinding.root)
                    dialog.show()
                    delay(2000)
                    dialog.dismiss()
                } else {
                    wishlistViewModel.addToWishlist(wishlistItem)
                    toastBinding.message.text = "Added to wishlist successfully!"
                    dialog.setContentView(toastBinding.root)
                    dialog.show()
                    delay(2000)
                    dialog.dismiss()
                    binding.wishlistText.text = "Wishlisted"
                }
            }

        }


        binding.productTopBar.backArrow.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        binding.productTopBar.wishlistIcon.setOnClickListener {
            val intent = Intent(this, WishlistActivity::class.java)
            startActivity(intent)
        }


        binding.productTopBar.accountIcon.setOnClickListener {
            val intent = Intent(this, CartActivity::class.java)
            startActivity(intent)
        }

    }
}