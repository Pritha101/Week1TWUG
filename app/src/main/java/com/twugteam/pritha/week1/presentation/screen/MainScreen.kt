package com.twugteam.pritha.week1.presentation.screen

import android.text.Layout
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.twugteam.pritha.week1.presentation.route.NavRoute


@Composable
fun MainScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = {
            navController.navigate(NavRoute.BMIScreen.route)
        }, modifier = Modifier.fillMaxWidth()) {
            Text(text = "BMI Calculator")
        }
        Button(onClick = {
            navController.navigate(NavRoute.TaxCalculation.route)

        }, modifier = Modifier.padding(8.dp)) {
            Text(text = "Tax Calculator")
        }
        Button(onClick = {
            navController.navigate(NavRoute.NumToWordsScreen.route)

        }) {
            Text(text = "Number to Words")
        }
    }

}