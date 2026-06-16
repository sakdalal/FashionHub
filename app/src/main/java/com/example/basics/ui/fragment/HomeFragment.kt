package com.example.basics.ui.fragment

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.basics.databinding.FragmentHomeBinding

class HomeFragment : Fragment(){
private lateinit var binding: FragmentHomeBinding
    private val handler = Handler(Looper.getMainLooper())

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        setupRail()
        setupBanner()

        Handler(Looper.getMainLooper()).post {
            setupCard()
            setupFeatureBrand()
        }
        Handler(Looper.getMainLooper()).postDelayed({
            setupOffer()
            setupSeller()
//            setupTabs()
        }, 100)

    }

//    private fun setupRail(){
//        val railList=listOf(
//            RailItem("Fashion", R.drawable.human),
//            RailItem("Beauty", R.drawable.human),
//            RailItem("Accessory", R.drawable.human),
//            RailItem("Footwear", R.drawable.human),
//            RailItem("Homeliving", R.drawable.human)
//        )
//        binding.railRecyclerView.apply {
//            layoutManager= LinearLayoutManager(context, RecyclerView.HORIZONTAL,false)
//            adapter= RailAdapter(railList)
//        }
//    }

    private fun setupBanner(){
//        val bannerList = listOf(
//            Banner(R.drawable.banner),
//            Banner(R.drawable.banner),
//            Banner(R.drawable.banner)
//        )
//        val adapter= BannerAdapter(bannerList)
//        binding.bannerViewPager.adapter=adapter
//        binding.dotsIndicator.attachTo(binding.bannerViewPager)

        // ✅ CONNECT DOTS (THIS IS IMPORTANT)
//        val runnable = object : Runnable {
//            override fun run() {
//                binding.bannerViewPager.currentItem = (binding.bannerViewPager.currentItem + 1) % bannerList.size
//                handler.postDelayed(this, 5000)
//            }
//        }
//        handler.postDelayed(runnable, 5000)
//        // ✅ Reset timer when user swipes
//        binding.bannerViewPager.registerOnPageChangeCallback(
//            object : ViewPager2.OnPageChangeCallback() {
//                override fun onPageSelected(position: Int) {
//                    handler.removeCallbacksAndMessages(null)
//                    handler.postDelayed(runnable, 5000)
//                }
//            })

    }


    private fun setupCard(){
//        val cardList=listOf(
//            Card("Shirt", R.drawable.top),
//            Card("Jeans", R.drawable.top),
//            Card("T-Shirt", R.drawable.top),
//            Card("Trousers", R.drawable.top),
//            Card("Kurta Sets", R.drawable.top),
//            Card("Tops", R.drawable.top),
//            Card("Dresses", R.drawable.top),
//            Card("Shoes", R.drawable.top),
//            Card("Shirt", R.drawable.top),
//            Card("Jeans", R.drawable.top)
//        )
//        binding.cardRecycler.apply {
//            layoutManager= GridLayoutManager(context,2, RecyclerView.HORIZONTAL,false)
//            adapter= CardAdapter(cardList)
//        }
    }

    private fun setupFeatureBrand(){
//        val brandList=listOf(
//            FeatureBrand(R.drawable.dress,"Up To 50% Off","Western Wear",R.drawable.brandname),
//            FeatureBrand(R.drawable.dress,"Up To 50% Off","Western Wear",R.drawable.brandname),
//            FeatureBrand(R.drawable.dress,"Up To 50% Off","Western Wear",R.drawable.brandname),
//            FeatureBrand(R.drawable.dress,"Up To 50% Off","Western Wear",R.drawable.brandname)
//        )
//        binding.brandRecycler.apply {
//            layoutManager= LinearLayoutManager(context, RecyclerView.HORIZONTAL,false)
//            adapter= FeatureBrandAdapter(brandList)
//        }
    }

    private fun setupOffer(){
//        val offerList=listOf(
//            OfferSmall(R.drawable.perfume),
//            OfferSmall(R.drawable.perfume),
//            OfferSmall(R.drawable.perfume),
//            OfferSmall(R.drawable.perfume),
//            OfferSmall(R.drawable.perfume)
//        )
//        binding.offerRecycler.apply {
//            layoutManager= LinearLayoutManager(context, RecyclerView.HORIZONTAL,false)
//            adapter= OfferAdapter(offerList)
//        }
    }

    private fun setupSeller(){
//        val sellerList=listOf(
//            Seller("Flat",R.drawable.box,"Under ₹649",R.drawable.tinylogo,R.drawable.tinylogo),
//            Seller("Flat",R.drawable.box,"Under ₹649",R.drawable.tinylogo,R.drawable.tinylogo),
//            Seller("Flat",R.drawable.box,"Under ₹649",R.drawable.tinylogo,R.drawable.tinylogo),
//            Seller("Flat",R.drawable.box,"Under ₹649",R.drawable.tinylogo,R.drawable.tinylogo),
//            Seller("Flat",R.drawable.box,"Under ₹649",R.drawable.tinylogo,R.drawable.tinylogo)
//        )
//        binding.sellerRecycler.apply {
//            layoutManager= LinearLayoutManager(context, RecyclerView.HORIZONTAL,false)
//            adapter= SellerAdapter(sellerList)
//        }
    }

//    private fun setupTabs(){
//        val productAdapter= ProductAdapter(mutableListOf())
//        binding.productRecycler.apply{
//            layoutManager= GridLayoutManager(context,2)
//            adapter=productAdapter
//        }
//        val tabImages = listOf(
//            R.drawable.summer,
//            R.drawable.summer,
//            R.drawable.summer
//        )
//        tabImages.forEach {
//            val tab = binding.tabsLayout.newTab()
//            val tabBinding = TabItemBinding.inflate(layoutInflater)
//            tabBinding.tabItem.setImageResource(it)
//            tab.customView = tabBinding.root
//            binding.tabsLayout.addTab(tab)
//        }
//
//        val trending = listOf(
//            Product(R.drawable.product, "4.4","314","QUIER0","Printed Tie-Up Shrug","Best Price","₹506 with coupon"),
//            Product(R.drawable.product, "4.4", "314", "QUIER0", "Printed Tie-Up Shrug", "Best Price", "₹506 with coupon"),
//            Product(R.drawable.product, "4.4", "314", "QUIER0", "Printed Tie-Up Shrug", "Best Price", "₹506 with coupon"),
//            Product(R.drawable.product, "4.4", "314", "QUIER0", "Printed Tie-Up Shrug", "Best Price", "₹506 with coupon")
//        )
//
//        val new = listOf(
//            Product(R.drawable.jacket, "4.2", "4479", "HAP", "Women Ripped Jeans", "Best Price", "₹471 with coupon"),
//            Product(R.drawable.jacket, "4.2", "4479", "HAP", "Women Ripped Jeans", "Best Price", "₹471 with coupon"),
//            Product(R.drawable.jacket, "4.2", "4479", "HAP", "Women Ripped Jeans", "Best Price", "₹471 with coupon"),
//            Product(R.drawable.jacket, "4.2", "4479", "HAP", "Women Ripped Jeans", "Best Price", "₹471 with coupon")
//        )
//
//        val best = listOf(
//            Product(R.drawable.boots, "4.1", "145313", "Roadster", "Relaxed-Regular Shoes", "Best Price", "₹779 with coupon"),
//            Product(R.drawable.boots, "4.1", "145313", "Roadster", "Relaxed-Regular Shoes", "Best Price", "₹779 with coupon"),
//            Product(R.drawable.boots, "4.1", "145313", "Roadster", "Relaxed-Regular Shoes", "Best Price", "₹779 with coupon"),
//            Product(R.drawable.boots, "4.1", "145313", "Roadster", "Relaxed-Regular Shoes", "Best Price", "₹779 with coupon")
//        )
//
//        productAdapter.updateData(trending)
//        binding.tabsLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
//            override fun onTabSelected(tab: TabLayout.Tab) {
//                tab.customView?.alpha = 1f
//                when (tab.position) {
//                    0 -> productAdapter.updateData(trending)
//                    1 -> productAdapter.updateData(new)
//                    2 -> productAdapter.updateData(best)
//                }
//            }
//            override fun onTabUnselected(tab: TabLayout.Tab) {
//                tab.customView?.alpha = 0.75f
//            }
//            override fun onTabReselected(tab: TabLayout.Tab) {}
//        })
//    }

    override fun onDestroyView() {
        super.onDestroyView()
        handler.removeCallbacksAndMessages(null)
    }
}