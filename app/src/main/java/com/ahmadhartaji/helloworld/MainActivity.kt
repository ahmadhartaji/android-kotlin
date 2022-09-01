package com.ahmadhartaji.helloworld

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.ahmadhartaji.helloworld.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = MainAdapter()
        binding.recyclerView.adapter = adapter
        binding.recyclerView.setHasFixedSize(true)

        viewModel.getData().observe(this) {
            adapter.updateData(it)
        }
        viewModel.getStatus().observe(this) {
            updateUI(it)
        }
    }
    private fun updateUI(status: ApiStatus) {
        when (status) {
            ApiStatus.LOADING -> {
                binding.recyclerView.visibility = View.GONE
                binding.textViewError.visibility = View.GONE
                binding.progressBar.visibility = View.VISIBLE
            }
            ApiStatus.SUCCESS -> {
                binding.recyclerView.visibility = View.VISIBLE
                binding.textViewError.visibility = View.GONE
                binding.progressBar.visibility = View.GONE
            }
            ApiStatus.FAILED -> {
                binding.recyclerView.visibility = View.GONE
                binding.textViewError.visibility = View.VISIBLE
                binding.progressBar.visibility = View.GONE
            }
        }
    }
}