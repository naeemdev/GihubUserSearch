package com.naeemdev.githubuser.presentation.user.detail

import com.naeemdev.githubuser.domain.model.UserDetailModelD
import com.naeemdev.githubuser.domain.model.UserReposD
import com.naeemdev.githubuser.domain.model.UserSearchModelD

data class DetailUiState(
    val userDetailModel: UserDetailModelD? = null,
    val repositories:List<UserReposD> = emptyList(),
    val userList: List<UserSearchModelD> = emptyList(),
    val isLoading: Boolean = false,
    val error: Int? = null,
    val selectedTab: Int = 0
)