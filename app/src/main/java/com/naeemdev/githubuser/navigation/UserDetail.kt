package com.naeemdev.githubuser.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.naeemdev.githubuser.navigation.Screen.UserDetailsScreenRoute
import com.naeemdev.githubuser.presentation.user.detail.DetailScreen


fun NavController.navigateToUserDetail(
    userName: String,
) = navigateSingleTop(
    UserDetailsScreenRoute(
        userName = userName,
    )
)

fun NavGraphBuilder.navigateToUserDetail(navController: NavController) =
    composable<UserDetailsScreenRoute> { backStackEntry ->
        DetailScreen(
            gotoUserDetailScreen = {  userName ->
                navController.navigateUp()
                navController.navigateSingleTop(
                    UserDetailsScreenRoute(
                        userName = userName,
                    )
                )
            },
            onBackClicked = {
                navController.navigateUp()
            },
        )
    }
