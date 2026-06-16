package com.example.basics.network

import com.example.basics.model.FeatureBrand
import com.google.gson.Gson
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {


    private const val DUMMY_URL="https://dummyjson.com/"
    private const val FAKERY_URL="https://jsonfakery.com/"

    private fun createApi(baseUrl:String):ApiService{
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    val dummyApi: ApiService by lazy {
        createApi(DUMMY_URL)
    }

    val fakeryApi: ApiService by lazy {
        createApi(FAKERY_URL)
    }

    suspend fun getProductById(
        productId: Int
    ): FeatureBrand {
        return RetrofitInstance
            .dummyApi
            .getProductById(productId)
    }

//    private const val FAKESTORE_URL="https://fakestoreapiserver.reactbd.org/"
//    val railApi: ApiService by lazy {
//        Retrofit.Builder()
//            .baseUrl(FAKESTORE_URL)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//            .create(ApiService::class.java)
//    }
//    private const val BANNER_URL="https://dummyjson.com/"
//    val bannerApi: ApiService by lazy {
//        Retrofit.Builder()
//            .baseUrl(BANNER_URL)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//            .create(ApiService::class.java)
//    }
//    val cardApi: ApiService by lazy {
//        Retrofit.Builder()
//            .baseUrl(FAKESTORE_URL)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//            .create(ApiService::class.java)
//    }
//    val offerApi: ApiService by lazy {
//        Retrofit.Builder()
//            .baseUrl(FAKESTORE_URL)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//            .create(ApiService::class.java)
//    }
//    val featureApi: ApiService by lazy {
//        Retrofit.Builder()
//            .baseUrl(BANNER_URL)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//            .create(ApiService::class.java)
//    }
//    val sellerApi: ApiService by lazy {
//        Retrofit.Builder()
//            .baseUrl("https://jsonfakery.com/")
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//            .create(ApiService::class.java)
//    }
//    val productApi: ApiService by lazy {
//        Retrofit.Builder()
//            .baseUrl("https://jsonfakery.com/")
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//            .create(ApiService::class.java)
//    }


}