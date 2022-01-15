package com.multimedialab.mfunzi

sealed class Screens(val route: String){

    object Home: Screens(route = "Home")
    object Login: Screens(route = "Login")
}
