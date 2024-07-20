package com.futureme.crud_supabase.presentation.future.productdetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.futureme.crud_supabase.domain.model.Product
import com.futureme.crud_supabase.domain.usecase.GetProductDetailsUseCase
import com.futureme.crud_supabase.domain.usecase.UpdateProductUseCase
import com.futureme.crud_supabase.presentation.navigation.ProductDetailsDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailsViewModel @Inject constructor(
    private val getProductDetailsUseCase: GetProductDetailsUseCase,
    private val updateProductUseCase: UpdateProductUseCase,
    savedStateHandle: SavedStateHandle,

    ) : ViewModel(), ProductDetailsContract {
    private val _product = MutableStateFlow<Product?>(null)
    override val product: Flow<Product?> = _product

    private val _name = MutableStateFlow("")
    override val name: Flow<String> = _name

    private val _price = MutableStateFlow(0.0)
    override val price: Flow<Double> = _price

    private val _imageUrl = MutableStateFlow("")
    override val imageUrl: Flow<String> = _imageUrl

    init {
        val productId = savedStateHandle.get<String>(ProductDetailsDestination.productId)
        productId?.let {
            getProduct(productId = it)
        }
    }

    private fun getProduct(productId: String) {
        viewModelScope.launch {
            val result = getProductDetailsUseCase.execute(
                GetProductDetailsUseCase.Input(
                    id = productId
                )
            )
            when (result) {
                is GetProductDetailsUseCase.Output.Success -> {
                    _product.emit(result.data)
                    _name.emit(result.data.name)
                    _price.emit(result.data.price)
                    _imageUrl.emit(result.data.image)
                }
                is GetProductDetailsUseCase.Output.Failure -> {

                }
            }
        }
    }

    fun onNameChange(name: String) {
        _name.value = name
    }

    fun onPriceChange(price: Double) {
        _price.value = price
    }

    override fun onSaveProduct(image: ByteArray) {
        viewModelScope.launch {
            updateProductUseCase.execute(
                UpdateProductUseCase.Input(
                    id = _product.value?.id ?: "",
                    price = _price.value,
                    name = _name.value,
                    imageFile = image,
                    imageName = "image_${_product.value?.id}",
                )
            )
        }
    }

    override fun onImageChange(url: String) {
        _imageUrl.value = url
    }
}