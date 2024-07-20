package com.futureme.crud_supabase.domain.usecase

interface UseCase<InputT, OutputT> {
    suspend fun execute(input: InputT): OutputT
}