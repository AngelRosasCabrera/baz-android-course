package com.example.bitsocurrency.ui.activities

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.example.bitsocurrency.R
import com.example.bitsocurrency.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_BitsoCurrency)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
    }

    fun hideLoading() {
        binding.lottieLoading.visibility = View.GONE
    }

    fun showLoading() {
        binding.lottieLoading.visibility = View.VISIBLE
        binding.lottieLoading.playAnimation()
        binding.lottieLoading.loop(true)
    }

}