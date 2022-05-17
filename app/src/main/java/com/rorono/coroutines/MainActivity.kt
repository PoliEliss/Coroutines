package com.rorono.coroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.rorono.coroutines.databinding.ActivityMainBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.security.auth.callback.Callback
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.buttonDownload.setOnClickListener {
            lifecycleScope.launch {
                loadData()
            }

        }

    }

    private suspend fun loadData() {
        binding.progressBar.isVisible = true
        binding.buttonDownload.isEnabled = false
        val city = loadCity()
        binding.tvLocation.text = city
        val tempetature = loadTemperature(city)
        binding.tvTemperature.text = tempetature.toString()
        binding.progressBar.isVisible = false
        binding.buttonDownload.isEnabled = true

    }

    private suspend fun loadCity(): String {
        delay(5000)
        return "Moskov"
    }

    private suspend fun loadTemperature(city: String): Int {
        Toast.makeText(
            this,
            "Loading temperature for city: ${city}", Toast.LENGTH_LONG
        ).show()

        delay(5000)
        return 23
    }
}



