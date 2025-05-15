package com.naeemdev.githubuser.presentation.user.detail.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.naeemdev.githubuser.R
import com.naeemdev.githubuser.domain.ErrorType
import com.naeemdev.githubuser.domain.Resource
import com.naeemdev.githubuser.domain.usecase.UserDetailUseCase
import com.naeemdev.githubuser.domain.usecase.UserFollowersUseCase
import com.naeemdev.githubuser.domain.usecase.UserFollowingUseCase
import com.naeemdev.githubuser.domain.usecase.UserReposUseCase
import com.naeemdev.githubuser.presentation.user.detail.DetailUIEvent
import com.naeemdev.githubuser.presentation.user.detail.DetailUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val detailUseCase: UserDetailUseCase,
    private val reposUseCase: UserReposUseCase,
    private val followingUseCase: UserFollowingUseCase,
    private val followersUseCase: UserFollowersUseCase,
) : ViewModel() {
    private val _uiState = MutableStateFlow<DetailUiState>(DetailUiState())
    val uiState: StateFlow<DetailUiState> = _uiState
    val userNameFromNav: String? = savedStateHandle["userName"]

    init {
        userNameFromNav?.let {
            userDetail(userNameFromNav)
        }
    }

    fun onEvent(event: DetailUIEvent) {
        when (event) {
            DetailUIEvent.DismissDialog -> {
                _uiState.update { it.copy(error = null) }
            }

            is DetailUIEvent.UpdateSelectedTab -> {
                _uiState.update { it.copy(selectedTab = event.tabIndex) }
                when (event.tabIndex) {
                    0 -> {
                        _uiState.update { it.copy(isLoading = true) }
                        userRepos(userNameFromNav.orEmpty())
                    }

                    1 -> {
                        getFollowers(userNameFromNav.orEmpty())
                    }
                    2 -> {
                        getFollowing(userNameFromNav.orEmpty())
                    }
                }

            }
        }
    }

    private fun userDetail(userName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.update { it.copy(isLoading = true) }
            when (val response = detailUseCase(userName)) {
                is Resource.Success -> {
                    _uiState.update {
                        it.copy(
                            userDetailModel = response.data
                        )
                    }
                    userRepos(userName)
                }

                is Resource.Error -> {
                    handleError(response.errorType)
                }
            }
        }
    }

    private fun userRepos(userName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            when (val response = reposUseCase(userName)) {
                is Resource.Success -> {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            repositories = response.data
                        )
                    }
                }

                is Resource.Error -> {
                    handleError(response.errorType)
                }
            }
        }
    }
    private fun getFollowers(userName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.update { it.copy(isLoading = true) }
            when (val response = followersUseCase(userName)) {
                is Resource.Success -> {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            userList = response.data
                        )
                    }
                }

                is Resource.Error -> {
                    handleError(response.errorType)
                }
            }
        }
    }
    private fun getFollowing(userName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.update { it.copy(isLoading = true) }
            when (val response = followingUseCase(userName)) {
                is Resource.Success -> {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            userList = response.data
                        )
                    }
                }

                is Resource.Error -> {
                    handleError(response.errorType)
                }
            }
        }
    }

    private fun handleError(errorType: ErrorType) {
        _uiState.update {
            it.copy(
                isLoading = false,
                error =errorType.errorMessageRes
            )
        }
    }
}