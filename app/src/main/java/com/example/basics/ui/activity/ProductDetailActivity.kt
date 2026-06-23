package com.example.basics.ui.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.bumptech.glide.Glide
import com.example.basics.MyApplication
import com.example.basics.databinding.ActivityProductDetailBinding
import com.example.basics.databinding.CustomToastBinding
import com.example.basics.db.CartEntity
import com.example.basics.db.WishlistEntity
import com.example.basics.model.FeatureBrand
import com.example.basics.model.User
import com.example.basics.viewmodel.AddressViewModel
import com.example.basics.viewmodel.AddressViewModelFactory
import com.example.basics.viewmodel.CartViewModel
import com.example.basics.viewmodel.CartViewModelFactory
import com.example.basics.viewmodel.OrderViewModel
import com.example.basics.viewmodel.OrderViewModelFactory
import com.example.basics.viewmodel.WishlistViewModel
import com.example.basics.viewmodel.WishlistViewModelFactory
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ProductDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProductDetailBinding
    private lateinit var cartViewModel: CartViewModel

    private lateinit var addressViewModel: AddressViewModel

    private var isInCart = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, true)

        binding = ActivityProductDetailBinding.inflate(layoutInflater)

        setContentView(binding.root)

        @Suppress("DEPRECATION")
        val product = intent.getSerializableExtra("PRODUCT") as FeatureBrand

        val addressRepository =
            (application as MyApplication).addressRepository

        addressViewModel = ViewModelProvider(
            this,
            AddressViewModelFactory(addressRepository)
        )[AddressViewModel::class.java]

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

        val uid = FirebaseAuth.getInstance().currentUser!!.uid
        FirebaseFirestore.getInstance()
            .collection("users")
            .document(uid)
            .get()
            .addOnSuccessListener {
                val user = it.toObject(User::class.java)
                Log.d("PROFILE", "User Name: ${user?.name}")
                binding.profileName.text = user?.name
            }
            .addOnFailureListener {
                Log.e("PROFILE", "Error", it)
            }
        val userId =
            FirebaseAuth.getInstance().currentUser?.uid ?: return
        lifecycleScope.launch {
            val defaultAddress =
                addressViewModel.getDefaultAddress(userId)

            defaultAddress?.let {
                binding.profileAddress.text =
                    "${it.house}, ${it.address}"

            } ?: run {
                binding.profileAddress.text =
                    "No default address selected"

            }
        }


        val orderRepository =
            (application as MyApplication).orderRepository
        val orderViewModel = ViewModelProvider(
            this,
            OrderViewModelFactory(orderRepository)
        )[OrderViewModel::class.java]


        val repository =
            (application as MyApplication).cartRepository
        cartViewModel = ViewModelProvider(
            this,
            CartViewModelFactory(repository,orderRepository)
        )[CartViewModel::class.java]

        val wishlistRepository =
            (application as MyApplication).wishlistRepository
        val wishlistViewModel = ViewModelProvider(
            this,
            WishlistViewModelFactory(wishlistRepository)
        )[WishlistViewModel::class.java]

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {

                cartViewModel.cartItems.collect { items ->

                    if (items.isEmpty()) {
                        binding.productTopBar.cartBadge.visibility = View.GONE
                    } else {
                        binding.productTopBar.cartBadge.visibility = View.VISIBLE
                        binding.productTopBar.cartBadge.text = items.size.toString()
                    }
                }
            }
        }


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
                        "Go to Bag"
                    } else {
                        "Add to Cart"
                    }
            }
        }


        binding.addToBag.setOnClickListener {
            if (isInCart) {
                val intent=Intent(this, CartActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
                startActivity(intent)
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

        binding.change.setOnClickListener {
            val intent= Intent(this,AddressActivity::class.java)
            startActivity(intent)
        }

    }
}