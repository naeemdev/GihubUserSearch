package com.naeemdev.githubuser.domain.usecase

import com.naeemdev.githubuser.domain.Resource
import com.naeemdev.githubuser.domain.model.UserSearchModelD
import com.naeemdev.githubuser.domain.repositories.GithubRepository
import jakarta.inject.Inject

class SearchUserUseCase @Inject constructor(
    private val repository: GithubRepository
) {
    suspend operator fun invoke(query: String): Resource<List<UserSearchModelD>> {
        return repository.searchUser(query)
    }
}