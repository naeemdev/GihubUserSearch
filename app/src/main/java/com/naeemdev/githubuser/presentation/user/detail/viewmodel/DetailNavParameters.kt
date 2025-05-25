package com.naeemdev.githubuser.presentation.user.detail.viewmodel
import androidx.lifecycle.SavedStateHandle
data class DetailNavUi (
    val userName: String,
)
object DetailNavParameters {
    fun from(savedStateHandle: SavedStateHandle) = DetailNavUi(
        userName = savedStateHandle.get<String>("userName").orEmpty(),
    )
}
