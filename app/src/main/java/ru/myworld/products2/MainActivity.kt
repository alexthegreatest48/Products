package ru.myworld.products2

import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import ru.myworld.products2.databinding.AppActivityBinding
import ru.myworld.products2.adapter.ProductAdapter
import ru.myworld.products2.viewmodel.ProductViewModel
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

class MainActivity : AppCompatActivity(R.layout.app_activity) {


    override fun onCreate(savedInstanceState: Bundle?) {

        val viewModel: ProductViewModel by viewModels()
        val adapter = ProductAdapter()
        val binding = AppActivityBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)

        binding.list.adapter = adapter

        addRepeatingJob(Lifecycle.State.STARTED) {
            viewModel.data.collectLatest(adapter::submitData)
        }

    }

    private fun LifecycleOwner.addRepeatingJob(
        state: Lifecycle.State,
        coroutineContext: CoroutineContext = EmptyCoroutineContext,
        block: suspend CoroutineScope.() -> Unit
    ): Job = lifecycleScope.launch(coroutineContext) {
        repeatOnLifecycle(state, block)
    }
}