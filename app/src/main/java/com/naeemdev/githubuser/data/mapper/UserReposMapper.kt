package com.naeemdev.githubuser.data.mapper

import com.naeemdev.githubuser.data.remote.response.UserReposResponse
import com.naeemdev.githubuser.domain.model.UserReposD

object UserReposMapper {

    // Convert a UserReposResponse (DTO) to a UserReposD (Domain Model)
    fun UserReposResponse.mapToDomain(): UserReposD {
        return UserReposD(
            name = name.orEmpty(),
            id = id ?: 0,
            htmlUrl = htmlUrl ?: "",
            language = language.orEmpty(),
            description = description.orEmpty(),
            stargazersCount = if (stargazersCount != null) stargazersCount else 0,
        )
    }

    // Convert a list of UserReposResponse (DTO) to a list of UserReposD (Domain Model)
    fun mapToDomainList(userModels: List<UserReposResponse>): List<UserReposD> {
        return userModels.map { it.mapToDomain() }
    }
}