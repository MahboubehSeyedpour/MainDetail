package com.example.omidPayTechTask.di

import com.example.omidPayTechTask.data.repositoryImpl.RepositoryImpl
import com.example.omidPayTechTask.domain.repository.Repository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindRepository(RepositoryImpl: RepositoryImpl): Repository
}