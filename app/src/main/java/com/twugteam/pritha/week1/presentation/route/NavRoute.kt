package com.twugteam.pritha.week1.presentation.route

sealed class NavRoute(val route: String) {
    data object MainScreen: NavRoute("Main")
    data object BMIScreen: NavRoute("BMI")
    data object NumToWordsScreen: NavRoute("NumToWords")
    data object TaxCalculation: NavRoute("Tax")
}