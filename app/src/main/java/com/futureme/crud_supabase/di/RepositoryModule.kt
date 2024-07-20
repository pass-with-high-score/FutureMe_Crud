package com.futureme.crud_supabase.di

import com.futureme.crud_supabase.data.respository.AuthenticationRepository
import com.futureme.crud_supabase.data.respository.AuthenticationRepositoryImpl
import com.futureme.crud_supabase.data.respository.ProductRepository
import com.futureme.crud_supabase.data.respository.ProductRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {

    @Binds
    abstract fun bindProductRepository(impl: ProductRepositoryImpl): ProductRepository

    @Binds
    abstract fun bindAuthenticateRepository(impl: AuthenticationRepositoryImpl): AuthenticationRepository

}