package com.naeemdev.githubuser.presentation.user.list


sealed class UIEvent {
    data class SearchUser(val query: String) : UIEvent()
    data object DismissDialog : UIEvent()
}