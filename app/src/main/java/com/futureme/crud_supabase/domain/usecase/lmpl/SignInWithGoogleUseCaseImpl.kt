package com.example.manageproducts.domain.usecase.impl

import com.futureme.crud_supabase.data.respository.AuthenticationRepository
import com.futureme.crud_supabase.domain.usecase.SignInWithGoogleUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SignInWithGoogleUseCaseImpl @Inject constructor(
    private val authenticationRepository: AuthenticationRepository,
): SignInWithGoogleUseCase {
    override suspend fun execute(input: SignInWithGoogleUseCase.Input): SignInWithGoogleUseCase.Output {
        return withContext(Dispatchers.IO) {
            authenticationRepository.signInWithGoogle()
            SignInWithGoogleUseCase.Output()
        }
    }
}