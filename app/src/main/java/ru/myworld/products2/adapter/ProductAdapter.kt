package ru.myworld.products2.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ru.myworld.products2.databinding.CardProductBinding
import ru.myworld.products2.dto.Product
import ru.myworld.products2.view.load

class ProductAdapter() : PagingDataAdapter<Product, ProductViewHolder>(ProductDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = CardProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }
}

class ProductViewHolder(
    private val binding: CardProductBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(product: Product) {
        binding.apply {
            photo.load(product.thumbnail)
            brand.text = product.brand
            title.text = product.title
            price.text = product.price.toString()
            description.text = product.description
        }
    }
}



private object ProductDiffCallback : DiffUtil.ItemCallback<Product>() {
    override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem == newItem
    }
}