package com.naeemdev.githubuser.domain.usecase

import com.naeemdev.githubuser.domain.Resource
import com.naeemdev.githubuser.domain.model.UserSearchModelD
import com.naeemdev.githubuser.domain.repositories.GithubRepository
import jakarta.inject.Inject

class GetUserListUseCase @Inject constructor(
    private val repository: GithubRepository
) {
    suspend operator fun invoke(): Resource<List<UserSearchModelD>> {
        return repository.getUserList()
    }
}