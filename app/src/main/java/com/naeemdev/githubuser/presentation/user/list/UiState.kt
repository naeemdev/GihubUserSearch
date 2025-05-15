package com.naeemdev.githubuser.presentation.user.list

import com.naeemdev.githubuser.domain.model.UserSearchModelD

data class UiState(
    val dataList: List<UserSearchModelD> = emptyList(),
    val isLoading: Boolean = false,
    val error: Int? = null
)