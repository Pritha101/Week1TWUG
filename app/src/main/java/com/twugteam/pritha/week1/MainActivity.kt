package com.twugteam.pritha.week1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.twugteam.pritha.week1.presentation.route.NavRoute
import com.twugteam.pritha.week1.presentation.screen.BMIScreen
import com.twugteam.pritha.week1.presentation.screen.MainScreen
import com.twugteam.pritha.week1.presentation.screen.NumToWordsScreen
import com.twugteam.pritha.week1.presentation.screen.TaxCalculationScreen
import com.twugteam.pritha.week1.ui.theme.Week1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Week1Theme {
                App()
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun Preview() {
    Week1Theme {
        App()
    }
}

@Composable
fun App(){
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ){
        Navigation()
    }
}

@Composable
fun Navigation(){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = NavRoute.MainScreen.route) {
        composable(NavRoute.BMIScreen.route) { BMIScreen(navController) }
        composable(NavRoute.NumToWordsScreen.route) { NumToWordsScreen(navController) }
        composable(NavRoute.TaxCalculation.route) { TaxCalculationScreen(navController) }
        composable(NavRoute.MainScreen.route) { MainScreen(navController) }
        // Add more composables for additional screens
    }
}