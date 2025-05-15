package com.naeemdev.githubuser.presentation.user.detail


sealed class DetailUIEvent {
    data object DismissDialog : DetailUIEvent()
    data class UpdateSelectedTab(val tabIndex: Int) : DetailUIEvent()
}