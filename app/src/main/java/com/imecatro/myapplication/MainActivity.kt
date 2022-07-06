package com.imecatro.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.imecatro.myapplication.databinding.ActivityMainBinding
import com.imecatro.myapplication.network.GeocodingRepo
import com.imecatro.myapplication.network.GeocodingRepoImp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

const val EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE"

class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val geoRepository by lazy {
        GeocodingRepoImp()
    }

    private  var latitude: Double =0.0
    private  var longitude : Double =0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        CoroutineScope(Dispatchers.Main).launch {
            try {
                val response = geoRepository.getLocation("1785+The+Exchange+se,+Atlanta,+GA")
                val result = response.body()?.results
                val expected = result?.first()?.geometry?.location //component1()?.geometry?.location
                Log.d("CLASS::${javaClass.simpleName} MESSAGE ->", "LAT:${expected?.lat}")

                expected?.let {
                    latitude = it.lat
                    longitude =it.lng
                }

            } catch (e: Exception) {
                Log.d("CLASS::${javaClass.simpleName} MESSAGE ->", e.message.toString())
            }
        }

        binding.addBtn.setOnClickListener {
            // latitude = 33.909105
            // longitude = -84.4794287
            val location = "$latitude,$longitude"
            val intent = Intent(this, MapsActivity::class.java).apply {
                putExtra(EXTRA_MESSAGE, location)
            }
            startActivity(intent)
        }


        setContentView(binding.root)


    }
}