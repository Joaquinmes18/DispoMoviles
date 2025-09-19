package com.calyrsoft.ucbp1.navigation

sealed class Screen(val route: String) {
    object Login : Screen("login")
    object Forgot : Screen("forgot")
    object Home : Screen("home")
    object Github : Screen("github")
    object Profile : Screen("profile")
    object Dollar: Screen("dollar")
}