package com.multimedialab.mfunzi

import androidx.compose.runtime.Composable
import androidx.compose.runtime.internal.composableLambda
import androidx.navigation.NavController

import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable


@Composable
fun SetupNavGraph(navController: NavHostController){
    NavHost(navController = navController,startDestination = Screens.Login.route){
        composable(route= Screens.Login.route){
            SplashScreen()
        }
    }

}

