package com.imecatro.myapplication.network

import com.imecatro.myapplication.BuildConfig.MAPS_API_KEY
import com.imecatro.myapplication.model.Geocoding
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GeocodingService {

    @GET(URL_GEOCODE)
    suspend fun getLocation(
        @Query("address") address: String?,
        @Query("key") key: String = MAPS_API_KEY
    ): Response<Geocoding>

    companion object {
        const val BASE_URL = "https://maps.googleapis.com/"
        const val URL_GEOCODE ="maps/api/geocode/json"
    }
}