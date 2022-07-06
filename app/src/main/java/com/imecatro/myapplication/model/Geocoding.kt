package com.imecatro.myapplication.model


import com.google.gson.annotations.SerializedName

data class Geocoding(
    @SerializedName("results")
    val results: List<Result>,
    @SerializedName("status")
    val status: String
)