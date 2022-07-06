package com.imecatro.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.imecatro.myapplication.databinding.ActivityMainBinding

const val EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE"

class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.addBtn.setOnClickListener {
            val latitude = 33.909105
            val longitude = -84.4794287
            val location = "$latitude,$longitude"
            val intent = Intent(this, MapsActivity::class.java).apply {
                putExtra(EXTRA_MESSAGE,location)
            }
            startActivity(intent)
        }


        setContentView(binding.root)


    }
}