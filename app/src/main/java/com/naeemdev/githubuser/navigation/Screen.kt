package com.naeemdev.githubuser.navigation

import kotlinx.serialization.Serializable

sealed class Screen {
    @Serializable
    data object UserListScreenRoute : Screen()

    @Serializable
    data class UserDetailsScreenRoute(
        val userName: String
    ) : Screen()


}