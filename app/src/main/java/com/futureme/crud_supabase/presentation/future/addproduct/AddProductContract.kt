package com.futureme.crud_supabase.presentation.future.addproduct

import com.futureme.crud_supabase.domain.usecase.CreateProductUseCase
import kotlinx.coroutines.flow.Flow

interface AddProductContract {

    val navigateAddProductSuccess: Flow<CreateProductUseCase.Output?>
    val isLoading: Flow<Boolean>
    val showSuccessMessage: Flow<Boolean>
    fun onCreateProduct(name: String, price: Double)
    fun onAddMoreProductSelected()
    fun onRetrySelected()
}