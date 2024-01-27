package com.twugteam.pritha.week1.presentation.screen

import android.text.Layout
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.twugteam.pritha.week1.presentation.route.NavRoute


@Composable
fun MainScreen(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "CalcMaster",
                style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,

                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 1.dp, top = 20.dp, end = 1.dp, bottom = 20.dp)
                    .size(50.dp)
                    .background(color = Color.Cyan)
                    .clickable { navController.navigate(NavRoute.MainScreen.route) }
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center),
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
            }, modifier = Modifier.fillMaxWidth()) {
                Text(text = "Tax Calculator")
            }
            Button(onClick = {
                navController.navigate(NavRoute.NumToWordsScreen.route)
            }, modifier = Modifier.fillMaxWidth()) {
                Text(text = "Number to Words")
            }
        }
    }
}
