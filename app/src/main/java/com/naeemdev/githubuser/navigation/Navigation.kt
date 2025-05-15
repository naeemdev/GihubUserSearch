package com.naeemdev.githubuser.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.naeemdev.githubuser.navigation.Screen.UserDetailsScreenRoute
import com.naeemdev.githubuser.navigation.Screen.UserListScreenRoute
import com.naeemdev.githubuser.presentation.user.detail.DetailScreen
import com.naeemdev.githubuser.presentation.user.list.UserListScreen

fun <T : Screen> NavController.navigateSingleTop(screen: T) {
    this.navigate(screen) {
        launchSingleTop = true
    }
}

@Composable
fun Navigation(
    navController: NavHostController = rememberNavController(),
) {

    NavHost(
        navController = navController,
        startDestination = UserListScreenRoute,
    ) {
        composable<UserListScreenRoute> {
            UserListScreen(
                gotoUserDetailScreen = {  userName ->
                    navController.navigateSingleTop(
                        UserDetailsScreenRoute(
                            userName = userName,
                        )
                    )
                },
            )
        }


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
    }
}

