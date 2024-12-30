package com.sbjr.navigationdrawer

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface VenueApiService {
    @GET("v2/venues/search?ll=40.7484,-73.9857&oauth_token=NPKYZ3WZ1VYMNAZ2FLX1WLECAWSMUVOQZOIDBN53F3LVZBPQ&v=20180616")
    suspend fun getVenues(): ApiResponse
}


     object getIn{
        private val url = "https://api.foursquare.com/"

         val api: VenueApiService by lazy {
             Retrofit.Builder()
                 .baseUrl(url)
                 .addConverterFactory(GsonConverterFactory.create())
                 .build()
                 .create(VenueApiService::class.java)
         }


        }



