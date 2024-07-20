package com.example.manageproducts.domain.usecase.impl

import com.futureme.crud_supabase.data.respository.ProductRepository
import com.futureme.crud_supabase.domain.usecase.DeleteProductUseCase
import javax.inject.Inject

class DeleteProductUseCaseImpl @Inject constructor(
    private val productRepository: ProductRepository
) : DeleteProductUseCase {
    override suspend fun execute(input: DeleteProductUseCase.Input): DeleteProductUseCase.Output {
        productRepository.deleteProduct(input.productId)
        return DeleteProductUseCase.Output.Success
    }
}