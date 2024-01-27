package com.twugteam.pritha.week1.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.twugteam.pritha.week1.presentation.route.NavRoute
import com.twugteam.pritha.week1.ui.theme.Week1Theme
import java.text.DecimalFormat
import kotlin.math.roundToLong

@Composable
fun BMIScreen(navController: NavController) {
    var showDialog by remember { mutableStateOf(false) }
    var height by remember { mutableStateOf(0f) }
    var weight by remember { mutableStateOf(0f) }

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
        // Input fields for height and weight
        OutlinedTextField(
            value = height.toString(),
            onValueChange = {
                height = it.toFloatOrNull() ?: 0f
            },
            label = { Text("Height (ft)") },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Decimal
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

        OutlinedTextField(
            value = weight.toString(),
            onValueChange = {
                weight = it.toFloatOrNull() ?: 0f
            },
            label = { Text("Weight (kg)") },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Decimal
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

        // Calculate BMI button
        Button(
            onClick = { showDialog = true },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Icon(imageVector = Icons.Default.Add, contentDescription = null)
            Spacer(modifier = Modifier.width(8.dp))
            Text("Calculate BMI")
        }

        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = {
                    Text("Information")
                },
                text = {
                    Text("The BMI is: ${calculateBMI(weight, height)}")
                },
                confirmButton = {
                    Button(onClick = { showDialog = false }) {
                        Text("OK")
                    }
                }
            )
        }
    }
}

fun calculateBMI(weight: Float, height_ft: Float): String {
    val height_m = height_ft * 0.3048
    val bmi = weight / (height_m * height_m)
    return DecimalFormat("#.##").format(bmi)
}
