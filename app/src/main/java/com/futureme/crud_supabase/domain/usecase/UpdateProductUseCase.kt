package com.futureme.crud_supabase.domain.usecase

interface UpdateProductUseCase : UseCase<UpdateProductUseCase.Input, UpdateProductUseCase.Output> {
    class Input(
        val id: String,
        val name: String,
        val price: Double,
        val imageName: String,
        val imageFile: ByteArray,
    )

    sealed class Output() {
        object Success : Output()
        object Failure : Output()
    }
}