package com.twugteam.pritha.week1.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.twugteam.pritha.week1.presentation.route.NavRoute

@Composable
fun TaxCalculationScreen(navController: NavController){
    var incomeText by remember { mutableStateOf("0") }
    var taxResult by remember { mutableStateOf("0") }


    Column(
        modifier = Modifier
            .fillMaxWidth(),
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
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Input field for entering income
        OutlinedTextField(
            value = incomeText,
            onValueChange = { newIncomeText ->
                incomeText = newIncomeText
            },
            label = { Text("Enter Income") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                capitalization = KeyboardCapitalization.None,
                imeAction = ImeAction.Done
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

        // Calculate Tax button
        Button(
            onClick = {
                val income = incomeText
                taxResult = calculateTax(income)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text("Calculate Tax")
        }

        // Display tax result
        if (taxResult.isNotEmpty()) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                "Tax Calculation Result:",
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                taxResult,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

fun calculateTax(taxableIncome: String): String{
    //Tax calculation formula taxableIncome*0.19
    val tax = taxableIncome.toDouble() * 0.19
    return tax.toString()
}