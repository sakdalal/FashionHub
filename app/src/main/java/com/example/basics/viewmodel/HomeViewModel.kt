package com.example.basics.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.basics.R
import com.example.basics.db.WishlistEntity
import com.example.basics.model.FeatureBrand
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

    private var originalFeed = emptyList<HomeItem>()

    private var allProducts = emptyList<FeatureBrand>()

    private val _searchResults =
        MutableLiveData<List<FeatureBrand>>()

    val searchResults: LiveData<List<FeatureBrand>>
        get() = _searchResults


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
                allProducts = repository.getSearchProducts()

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


                originalFeed = homeFeed
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

    fun search(query: String): List<FeatureBrand> {

        val filtered =
            allProducts.filter {

                it.title.contains(query, true) ||
                        (it.brand?.contains(query, true) == true) ||
                        it.category.contains(query, true)||
                        it.description.contains(query, true)
            }

        _searchResults.value = filtered

        return filtered
    }


}