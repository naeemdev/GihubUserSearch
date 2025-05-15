package com.naeemdev.githubuser.di

import com.naeemdev.githubuser.data.remote.network.ApiServiceGithub
import com.naeemdev.githubuser.data.repositories.GithubRepositoryImpl
import com.naeemdev.githubuser.domain.repositories.GithubRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideGithubRepository(apiService: ApiServiceGithub): GithubRepository {
        return GithubRepositoryImpl(apiService)
    }
}