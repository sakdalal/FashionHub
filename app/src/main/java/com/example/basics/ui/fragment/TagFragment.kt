package com.example.basics.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.basics.MyApplication
import com.example.basics.ui.activity.ProductDetailActivity
import com.example.basics.adapter.HomeAdapter
import com.example.basics.adapter.RailAdapter
import com.example.basics.databinding.FragmentTagBinding
import com.example.basics.db.WishlistEntity
import com.example.basics.listener.OnProductClickListener
import com.example.basics.model.FeatureBrand
import com.example.basics.model.Product
import com.example.basics.repository.WishlistRepository
import com.example.basics.viewmodel.HomeViewModel
import com.example.basics.viewmodel.RailViewModel
import com.example.basics.viewmodel.WishlistViewModel
import com.example.basics.viewmodel.WishlistViewModelFactory
import kotlinx.coroutines.launch

class TagFragment : Fragment(), OnProductClickListener {

    private lateinit var binding: FragmentTagBinding
    private lateinit var radapter: RailAdapter
    private lateinit var homeAdapter: HomeAdapter
    private val viewModel: RailViewModel by viewModels()
    private val homeViewModel: HomeViewModel by activityViewModels()

    private lateinit var wishlistViewModel: WishlistViewModel
    private val handler = Handler(Looper.getMainLooper())

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTagBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d("HOME_FRAGMENT", "on view created")
        homeAdapter = HomeAdapter(emptyList(), this)

        binding.recyclerView.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        binding.recyclerView.adapter = homeAdapter

        setupRail()
        observeRail()

        homeViewModel.feed.observe(viewLifecycleOwner) {
            Log.d("HOME_FRAGMENT", "received ${it.size}")
            homeAdapter.updateList(it)
            Log.d("HOME_FRAGMENT", "submitList called")
        }

        val dao =
            (requireActivity()
                .application as MyApplication)
                .database
                .wishlistDao()

        val repository =
            WishlistRepository(dao)

        val factory =
            WishlistViewModelFactory(repository)

        wishlistViewModel =
            ViewModelProvider(
                this,
                factory
            )[WishlistViewModel::class.java]

        lifecycleScope.launch {
            wishlistViewModel.wishlistItems.collect { items ->
                val ids = items.map {
                    it.id
                }.toSet()
                homeAdapter.updateWishlist(ids)
            }
        }

    }

    private fun observeRail() {
        viewModel.rail.observe(viewLifecycleOwner) {
            radapter.submitList(it)
        }
    }

    private fun setupRail() {
        radapter = RailAdapter()
        binding.railRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        binding.railRecyclerView.adapter = radapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        handler.removeCallbacksAndMessages(null)
    }


    override fun onProductClick(
        product: FeatureBrand
    ) {
        val intent = Intent(
            requireContext(),
            ProductDetailActivity::class.java
        )
        intent.putExtra(
            "PRODUCT",
            product
        )
        startActivity(intent)
    }

    override fun onWishlistClick(product: FeatureBrand) {
            val item = WishlistEntity(
                id = product.id,
                brand=product.brand,
                rating = product.rating,
                price = product.price,
                discountPercentage = product.discountPercentage,
                thumbnail = product.thumbnail,
                title = product.title,
                returnPolicy = product.returnPolicy
            )
        wishlistViewModel.toggleWishlist(item)
    }


}