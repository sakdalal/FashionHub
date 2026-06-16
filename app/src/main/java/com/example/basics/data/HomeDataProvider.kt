package com.example.basics.data

import com.example.basics.R
import com.example.basics.model.Banner
import com.example.basics.model.Card
import com.example.basics.model.FeatureBrand
import com.example.basics.model.HomeItem
import com.example.basics.model.OfferSmall
import com.example.basics.model.Product
import com.example.basics.model.RailItem
import com.example.basics.model.Seller

object HomeDataProvider {

//    fun getRail(): List<RailItem> = listOf(
//        RailItem("Fashion", R.drawable.human),
//        RailItem("Beauty", R.drawable.human),
//        RailItem("Fashion", R.drawable.human),
//        RailItem("Beauty", R.drawable.human),
//        RailItem("Fashion", R.drawable.human)
//    )

    fun getFeed(): List<HomeItem> {

//        val bannerList = listOf(
//            Banner(1,R.drawable.banner,"Western wear","UpTo 10% off"),
//            Banner(2,R.drawable.banner,"Perfume","UpTo 15% Off"),
//            Banner(3,R.drawable.banner,"Footwear season","UpTo 6% Off")
//        )


//        val cardList = listOf(
//            Card("Shirt", R.drawable.top),
//            Card("Jeans", R.drawable.top),
//            Card("Shirt", R.drawable.top),
//            Card("Jeans", R.drawable.top),
//            Card("Shirt", R.drawable.top),
//            Card("Jeans", R.drawable.top),
//            Card("Shirt", R.drawable.top),
//            Card("Jeans", R.drawable.top),
//            Card("Shirt", R.drawable.top),
//            Card("Jeans", R.drawable.top)
//        )

//        val brandList = listOf(
//            FeatureBrand(R.drawable.dress, "Up To 50%", "Western", R.drawable.brandname),
//            FeatureBrand(R.drawable.dress, "Up To 50%", "Western", R.drawable.brandname),
//            FeatureBrand(R.drawable.dress, "Up To 50%", "Western", R.drawable.brandname),
//            FeatureBrand(R.drawable.dress, "Up To 50%", "Western", R.drawable.brandname)
//        )

//        val offerList = listOf(
//            OfferSmall(R.drawable.perfume),
//            OfferSmall(R.drawable.perfume),
//            OfferSmall(R.drawable.perfume),
//            OfferSmall(R.drawable.perfume),
//            OfferSmall(R.drawable.perfume)
//        )

//        val sellerList = listOf(
//            Seller("Flat", R.drawable.box, "₹649", R.drawable.tinylogo, R.drawable.tinylogo),
//            Seller("Flat", R.drawable.box, "₹649", R.drawable.tinylogo, R.drawable.tinylogo),
//            Seller("Flat", R.drawable.box, "₹649", R.drawable.tinylogo, R.drawable.tinylogo),
//            Seller("Flat", R.drawable.box, "₹649", R.drawable.tinylogo, R.drawable.tinylogo)
//        )

        val tabs = listOf(
            R.drawable.summer,
            R.drawable.summer,
            R.drawable.summer
        )

//        val products = mapOf(
//            0 to listOf(
//                Product(
//                    R.drawable.product,
//                    "4.4",
//                    "314",
//                    "QUIER0",
//                    "Printed Tie-Up Shrug",
//                    "Best Price",
//                    "₹506 with coupon"
//                ),
//                Product(
//                    R.drawable.product,
//                    "4.4",
//                    "314",
//                    "QUIER0",
//                    "Printed Tie-Up Shrug",
//                    "Best Price",
//                    "₹506 with coupon"
//                ),
//                Product(
//                    R.drawable.product,
//                    "4.4",
//                    "314",
//                    "QUIER0",
//                    "Printed Tie-Up Shrug",
//                    "Best Price",
//                    "₹506 with coupon"
//                ),
//                Product(
//                    R.drawable.product,
//                    "4.4",
//                    "314",
//                    "QUIER0",
//                    "Printed Tie-Up Shrug",
//                    "Best Price",
//                    "₹506 with coupon"
//                )
//            ),
//            1 to listOf(
//                Product(
//                    R.drawable.jacket,
//                    "4.2",
//                    "4479",
//                    "HAP",
//                    "Women Ripped Jeans",
//                    "Best Price",
//                    "₹471 with coupon"
//                ),
//                Product(
//                    R.drawable.jacket,
//                    "4.2",
//                    "4479",
//                    "HAP",
//                    "Women Ripped Jeans",
//                    "Best Price",
//                    "₹471 with coupon"
//                ),
//                Product(
//                    R.drawable.jacket,
//                    "4.2",
//                    "4479",
//                    "HAP",
//                    "Women Ripped Jeans",
//                    "Best Price",
//                    "₹471 with coupon"
//                ),
//                Product(
//                    R.drawable.jacket,
//                    "4.2",
//                    "4479",
//                    "HAP",
//                    "Women Ripped Jeans",
//                    "Best Price",
//                    "₹471 with coupon"
//                )
//            ),
//            2 to listOf(
//                Product(
//                    R.drawable.boots,
//                    "4.1",
//                    "145313",
//                    "Roadster",
//                    "Relaxed-Regular Shoes",
//                    "Best Price",
//                    "₹779 with coupon"
//                ),
//                Product(
//                    R.drawable.boots,
//                    "4.1",
//                    "145313",
//                    "Roadster",
//                    "Relaxed-Regular Shoes",
//                    "Best Price",
//                    "₹779 with coupon"
//                ),
//                Product(
//                    R.drawable.boots,
//                    "4.1",
//                    "145313",
//                    "Roadster",
//                    "Relaxed-Regular Shoes",
//                    "Best Price",
//                    "₹779 with coupon"
//                ),
//            )
//        )



        // 🔥 UI ORDER CONTROLLED HERE
        return listOf(
//            HomeItem.BannerSection(bannerList),
//            HomeItem.CardSection(cardList),
//            HomeItem.FeatureBrandSection(brandList),
//            HomeItem.OfferSection(offerList),
//            HomeItem.SellerSection(sellerList),
//            HomeItem.ProductSection(tabs,products)
        )
    }
}