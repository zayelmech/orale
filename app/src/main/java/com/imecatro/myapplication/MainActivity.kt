package com.imecatro.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.imecatro.myapplication.databinding.ActivityMainBinding
import com.imecatro.myapplication.model.Geocoding
import com.imecatro.myapplication.network.GeocodingRepoImp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

const val EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE"

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val geoRepository by lazy {
        GeocodingRepoImp()
    }

    private var latitude: Double = 0.0
    private var longitude: Double = 0.0
    private var _randomJoke: MutableLiveData<String> = MutableLiveData()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        fun getCoordinatesFrom(addressText: String) {

            CoroutineScope(Dispatchers.Main).launch {
                try {
                    val response = geoRepository.getLocation(addressText)
                    val result = response.body()?.results
                    val expected =
                        result?.first()?.geometry?.location //component1()?.geometry?.location
                    Log.d("CLASS::${javaClass.simpleName} MESSAGE ->", "LAT:${expected?.lat}")

                    expected?.let {
                        latitude = it.lat
                        longitude = it.lng
                        _randomJoke.postValue("$latitude,$longitude")
                    }

                } catch (e: Exception) {
                    Log.d("CLASS::${javaClass.simpleName} MESSAGE ->", e.message.toString())
                }
            }
        }

        _randomJoke.observe(this){
            val location = it
            val intent = Intent(this, MapsActivity::class.java).apply {
                putExtra(EXTRA_MESSAGE, location)
            }
            startActivity(intent)
        }

        binding.addBtn.setOnClickListener {
            val addressText = binding.editTextTextPostalAddress.text.toString()

            getCoordinatesFrom(addressText)
            /**
            val location = "$latitude,$longitude"
            val intent = Intent(this, MapsActivity::class.java).apply {
            putExtra(EXTRA_MESSAGE, location)
            }
            startActivity(intent)
             */
        }


        setContentView(binding.root)


    }
}