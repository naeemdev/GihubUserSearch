package com.naeemdev.githubuser.presentation.user.list.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.naeemdev.githubuser.R
import com.naeemdev.githubuser.domain.ErrorType
import com.naeemdev.githubuser.domain.Resource
import com.naeemdev.githubuser.domain.usecase.GetUserListUseCase
import com.naeemdev.githubuser.domain.usecase.SearchUserUseCase
import com.naeemdev.githubuser.presentation.user.list.UIEvent
import com.naeemdev.githubuser.presentation.user.list.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val getUserListUseCase: GetUserListUseCase,
    private val searchGithubUserUseCase: SearchUserUseCase,
) : ViewModel() {
    private val _uiState = MutableStateFlow<UiState>(UiState())
    val uiState: StateFlow<UiState> = _uiState

    init {
        fetchUserList()
    }

    fun onEvent(event: UIEvent) {
        when (event) {
            UIEvent.DismissDialog -> {
                _uiState.update { it.copy(error = null) }
            }

            is UIEvent.SearchUser -> {
                searchUser(event.query)
            }
        }
    }

    private fun searchUser(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            if (query.isBlank()) {
                fetchUserList()
                return@launch
            }
            _uiState.update { it.copy(isLoading = true) }
            when (val response = searchGithubUserUseCase(query)) {
                is Resource.Success -> {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            dataList = response.data
                        )
                    }
                }

                is Resource.Error -> {
                    handleError(response.errorType)
                }
            }
        }
    }

    private fun fetchUserList() {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.update { it.copy(isLoading = true) }
            when (val response = getUserListUseCase()) {
                is Resource.Success -> {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            dataList = response.data
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
                error = errorType.errorMessageRes
            )
        }
    }
}