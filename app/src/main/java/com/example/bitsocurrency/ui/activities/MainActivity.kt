package com.example.bitsocurrency.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.example.bitsocurrency.R
import com.example.bitsocurrency.ui.activities.viewmodel.BitsoViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: BitsoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel.availableBooks.observe(this) {
            it.forEach { bitso ->
                Log.d("AndroidStudio", bitso.book)
            }
        }
    }
}