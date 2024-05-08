package ru.myworld.products2

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import ru.myworld.products2.adapter.ProductAdapter
import ru.myworld.products2.databinding.AppActivityBinding
import ru.myworld.products2.viewmodel.ProductViewModel

class MainActivity : AppCompatActivity(R.layout.app_activity) {


    override fun onCreate(savedInstanceState: Bundle?) {

        val viewModel: ProductViewModel by viewModels()
        val adapter = ProductAdapter()
        val binding = AppActivityBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)

        binding.list.adapter = adapter

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.data.collectLatest {
                    adapter.submitData(it)
                }
            }
        }

    }
}