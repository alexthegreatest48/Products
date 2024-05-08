package ru.myworld.products2.model

import androidx.paging.PagingSource
import androidx.paging.PagingState
import ru.myworld.products2.api.ApiService
import ru.myworld.products2.dto.Product
import ru.myworld.products2.dto.toProduct
import ru.myworld.products2.error.NetworkError
import ru.myworld.products2.error.UnknownError
import java.io.IOException

class ProductPagingSource(
    private val apiService: ApiService
): PagingSource<Int, Product>() {
    override fun getRefreshKey(state: PagingState<Int, Product>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Product> {
        try {
            val page = params.key ?: 1
            val limit = 20

            val response = apiService.getPagingAll(
                pager = page,
                limit = limit
            )

            val products = response.body()!!.products
            val nextKey = if (products.size < page) null else page+1
            val prevKey = if (page == 1) null else page - 1
            return LoadResult.Page(products, nextKey, prevKey)

        } catch (e: IOException) {
            return LoadResult.Error(NetworkError)
        } catch (e: Exception) {
            return LoadResult.Error(UnknownError)
        }
    }
}