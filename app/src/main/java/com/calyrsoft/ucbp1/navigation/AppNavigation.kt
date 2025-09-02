package com.calyrsoft.ucbp1.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.calyrsoft.ucbp1.features.login.presentation.ForgotPasswordScreen
import com.calyrsoft.ucbp1.features.login.presentation.LoginScreen

@Composable
fun AppNavigation() {
    val navController: NavHostController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Login.route
    ) {
        composable(Screen.Login.route) {
            LoginScreen(
                modifier = Modifier,
                onLoginOk = {},
                onForgotPassword = {
                    navController.navigate(Screen.Forgot.route)
                },
            )
        }
        composable(Screen.Home.route) {

        }
        composable(Screen.Forgot.route) {
            ForgotPasswordScreen {  }
        }
    }
}