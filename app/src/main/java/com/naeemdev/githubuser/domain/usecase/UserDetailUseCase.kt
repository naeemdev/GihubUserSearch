package com.naeemdev.githubuser.domain.usecase

import com.naeemdev.githubuser.domain.Resource
import com.naeemdev.githubuser.domain.model.UserDetailModelD
import com.naeemdev.githubuser.domain.repositories.GithubRepository
import jakarta.inject.Inject

class UserDetailUseCase @Inject constructor(
    private val repository: GithubRepository
) {
    suspend operator fun invoke(userName: String): Resource<UserDetailModelD?> {
        return repository.userDetail(userName)
    }
}