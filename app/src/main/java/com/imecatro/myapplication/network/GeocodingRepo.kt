package com.imecatro.myapplication.network

import com.imecatro.myapplication.model.Geocoding
import retrofit2.Response

interface GeocodingRepo {
    suspend fun getLocation(address: String?): Response<Geocoding>
}

class GeocodingRepoImp : GeocodingRepo {

    override suspend fun getLocation(address: String?): Response<Geocoding> =
        Service.geoService.getLocation(address)

}