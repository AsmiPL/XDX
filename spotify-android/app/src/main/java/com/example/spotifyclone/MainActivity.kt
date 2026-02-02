package com.example.spotifyclone

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.spotifyclone.databinding.ActivityMainBinding
import com.example.spotifyclone.ui.MainViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loadButton.setOnClickListener {
            viewModel.loadFeaturedPlaylists()
        }

        viewModel.uiState.observe(this) { state ->
            binding.progressIndicator.isIndeterminate = state.isLoading
            binding.progressIndicator.visibility = if (state.isLoading) {
                android.view.View.VISIBLE
            } else {
                android.view.View.GONE
            }
            binding.resultText.text = state.message
        }
    }
}
