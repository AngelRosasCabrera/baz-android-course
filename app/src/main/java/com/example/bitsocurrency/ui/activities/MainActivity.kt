package com.example.bitsocurrency.ui.activities

import android.opengl.Visibility
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.example.bitsocurrency.R
import com.example.bitsocurrency.databinding.ActivityMainBinding
import com.example.bitsocurrency.utils.constants.Constants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var backPressedTime: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
    }

    fun showLoading() {
        binding.lottieLoading.visibility = View.VISIBLE
    }

    fun hideLoading() {
        binding.lottieLoading.visibility = View.GONE
    }

    override fun onBackPressed() {
        if (backPressedTime + Constants.TIME_TO_EXIT > System.currentTimeMillis()) {
            super.onBackPressed()
            finish()
            return
        } else {
            Toast.makeText(this, getString(R.string.press_back_again_to_exit), Toast.LENGTH_SHORT).show()
        }
        backPressedTime = System.currentTimeMillis()
    }
}