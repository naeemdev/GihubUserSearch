package com.naeemdev.githubuser.domain.usecase

import com.naeemdev.githubuser.domain.Resource
import com.naeemdev.githubuser.domain.model.UserReposD
import com.naeemdev.githubuser.domain.repositories.GithubRepository
import jakarta.inject.Inject

class UserReposUseCase @Inject constructor(
    private val repository: GithubRepository
) {
    suspend operator fun invoke(userName: String): Resource<List<UserReposD>> {
        return repository.userRepos(userName)
    }
}