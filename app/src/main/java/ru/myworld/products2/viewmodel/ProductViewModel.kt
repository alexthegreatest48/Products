package ru.myworld.products2.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.flow.Flow
import ru.myworld.products2.api.ProductsApi
import ru.myworld.products2.dto.Product
import ru.myworld.products2.model.ProductPagingSource

class ProductViewModel(application: Application) : AndroidViewModel(application) {

    val data: Flow<PagingData<Product>> = Pager(
        config = PagingConfig(pageSize = 20),
        pagingSourceFactory =  { ProductPagingSource(apiService = ProductsApi.service) }
    ).flow.cachedIn(viewModelScope)


}