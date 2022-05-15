package com.rorono.coroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.core.view.isVisible
import com.rorono.coroutines.databinding.ActivityMainBinding
import javax.security.auth.callback.Callback
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        loadData()
    }

    private fun loadData() {
        binding.progressBar.isVisible = true
        binding.buttonDownload.isEnabled = false
        loadCity {
            binding.tvLocation.text = it
            loadTemperature(it) {
                binding.tvTemperature.text = it.toString()
                binding.progressBar.isVisible = false
                binding.buttonDownload.isEnabled = true
            }
        }
    }

    private fun loadCity(callback: (String) -> Unit) {
        thread {
            Thread.sleep(5000)
            handler.post {
                callback.invoke("Moskov4")
            }

        }
    }

    private fun loadTemperature(city: String, callback: (Int) -> Unit) {
        thread {
            handler.post {
                Toast.makeText(
                    this,
                    "Loading temperature for city: ${city}", Toast.LENGTH_LONG
                ).show()
            }
            Thread.sleep(5000)
            handler.post {
                callback.invoke(17)
            }
        }
    }
}


