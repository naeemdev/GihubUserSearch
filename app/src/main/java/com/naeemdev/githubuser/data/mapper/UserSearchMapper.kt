package com.naeemdev.githubuser.data.mapper

import com.naeemdev.githubuser.data.remote.response.UserSearchModel
import com.naeemdev.githubuser.domain.model.UserSearchModelD

object UserSearchMapper {

    // Convert a UserSearchModel (DTO) to a UserSearchModelD (Domain Model)
    fun UserSearchModel.mapToDomain(): UserSearchModelD {
        return UserSearchModelD(
            login = login ?: "",
            id = id ?: 0,
            avatarUrl = avatarUrl ?: ""
        )
    }

    // Convert a list of UserSearchModel (DTO) to a list of UserSearchModelD (Domain Model)
    fun mapToDomainList(userModels: List<UserSearchModel>): List<UserSearchModelD> {
        return userModels.map { it.mapToDomain() }
    }
}