package com.naeemdev.githubuser

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.naeemdev.githubuser.navigation.Navigation
import com.naeemdev.githubuser.ui.theme.GihubUserSearchTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GihubUserSearchTheme {
                val navController = rememberNavController()
                Navigation(navController = navController)
            }
        }
    }
}
