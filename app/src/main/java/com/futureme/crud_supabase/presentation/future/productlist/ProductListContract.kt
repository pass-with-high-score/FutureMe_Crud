package com.futureme.crud_supabase.presentation.future.productlist

import com.futureme.crud_supabase.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface ProductListContract {
    val productList: Flow<List<Product>?>
    fun removeItem(product: Product)
    fun getProducts()
}