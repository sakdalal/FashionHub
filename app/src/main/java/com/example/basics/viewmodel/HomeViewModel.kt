package com.example.basics.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.basics.R
import com.example.basics.db.WishlistEntity
import com.example.basics.model.HomeItem
import com.example.basics.repository.HomeRepository
import com.example.basics.repository.WishlistRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay

import kotlinx.coroutines.launch
import kotlin.toString

class HomeViewModel : ViewModel() {
    private val repository = HomeRepository()
    private val _feed = MutableLiveData<List<HomeItem>>()
    val feed: LiveData<List<HomeItem>> = _feed
    init {
        loadFeed()
    }
    private val tabs = listOf(
        R.drawable.summer,
        R.drawable.summer,
        R.drawable.summer
    )



    private fun loadFeed() {
        Log.d("HOME_VM", "loadFeed called")

        viewModelScope.launch(Dispatchers.IO) {

            try {
                val data = repository.getHomeData()

                Log.d("HOME_VM", "banners ${data.banners.size}")
                Log.d("HOME_VM", "cards ${data.cards.size}")
                Log.d("HOME_VM", "offers ${data.offers.size}")
                Log.d("HOME_VM", "feature ${data.featureBrands.size}")
                Log.d("HOME_VM", "seller ${data.bestSellers.size}")
                Log.d("HOME_VM", "products ${data.products.size}")

                val mappedData = mapOf(
                    0 to data.products.take(6),
                    1 to data.products
                        .drop(6)
                        .take(6),
                    2 to data.products
                        .drop(12)
                        .take(6)
                )

                val homeFeed = listOf(

                    HomeItem.BannerSection(
                        banners = data.banners
                    ),
                    HomeItem.CardSection(
                        cards = data.cards
                    ),
                    HomeItem.OfferSection(
                        offers = data.offers
                    ),
                    HomeItem.FeatureBrandSection(
                        featureBrands = data.featureBrands
                    ),
                    HomeItem.SellerSection(
                        bestSellers = data.bestSellers
                    ),
                    HomeItem.ProductSection(
                        tabs = tabs,
                        data = mappedData
                    )
                )

                Log.d(
                    "HOME_VM",
                    "homeFeed size ${homeFeed.size}"
                )

                _feed.postValue(homeFeed)

                Log.d("HOME_VM", "feed assigned")
            } catch (e: Exception) {

                Log.d(
                    "HOME_VM_ERROR",
                    e.toString()
                )

                e.printStackTrace()
            }
        }
    }

//    private fun loadFeed(){
//        Log.d("HOME_VM", "loadFeed called")
//        viewModelScope.launch (Dispatchers.IO){
//            try {
//                Log.d("HOME_VM", "before banner")
//                val banners = repository.getBannerData()
//                Log.d("HOME_VM", "banner success ${banners.size}")
//                val cards = repository.getCardData()
//                Log.d("HOME_VM", "cards success ${cards.size}")
//                val offers = repository.getOfferData()
//                Log.d("HOME_VM", "offers success ${offers.size}")
//                val featureBrands= repository.getFeatureData()
//                Log.d("HOME_VM", "feature success ${featureBrands.size}")
//                val bestSellers= repository.getSellerData()
//                val products= repository.getProductData()
//                val mappedData=mapOf(
//                    0 to products.take(6),
//                    1 to products.drop(6).take(6),
//                    2 to products.drop(12).take(6)
//                )
//                val homeFeed = listOf(
//                    HomeItem.BannerSection(banners = banners),
//                    HomeItem.CardSection(cards= cards),
//                    HomeItem.OfferSection(offers = offers),
//                    HomeItem.FeatureBrandSection(featureBrands=featureBrands),
//                    HomeItem.SellerSection(bestSellers=bestSellers),
//                    HomeItem.ProductSection(tabs=tabs,data = mappedData)
//                )
//                Log.d("HOME_VM", "homeFeed size ${homeFeed.size}")
////                _feed.value=homeFeed
//                _feed.postValue(homeFeed)
//                Log.d("HOME_VM", "feed assigned")
//            }catch (e: Exception){
//
//                Log.d("HOME_VM_ERROR", e.toString())
//                e.printStackTrace()
//            }
//        }
//    }


}