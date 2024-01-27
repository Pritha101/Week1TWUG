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
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.twugteam.pritha.week1.presentation.route.NavRoute
import okhttp3.internal.format

@Composable
fun NumToWordsScreen(navController: NavController){

    var numberText by remember {
        mutableStateOf("")
    }
    var words by remember {
        mutableStateOf("")
    }

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
        // Input field for entering a number
        OutlinedTextField(
            value = numberText,
            onValueChange = {
                            numberText = it
            },
            label = { Text("Enter a Number") },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

        // Convert to Words button
        Button(
            onClick = {
                if (numberText.isNotEmpty()) {
                    // Convert the number text to words
                    words = convertToWords(numberText)
                }else {
                    words = "No number input"
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Icon(imageVector = Icons.Default.DateRange, contentDescription = null)
            Spacer(modifier = Modifier.width(8.dp))
            Text("Convert to Words")
        }

        // Display words
        if (words.isNotEmpty()) {
            Text(
                "Words: ${convertToWords(numberText)}",
                modifier = Modifier.padding(8.dp),
                style = MaterialTheme.typography.displaySmall
            )
        }
    }
}

fun convertToWords(number: String): String {
    val units = arrayOf("", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine")
    val teens = arrayOf("", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen")
    val tens = arrayOf("", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety")

    if (number.isEmpty()) {
        return "No number input"
    }

    val number = number.toInt()


    // Base cases
    if (number == 0) {
        return "Zero"
    }

    if (number < 0) {
        return "Negative " + convertToWords((-number).toString())
    }

    var result = ""

    // Process millions
    if (number / 1_000_000 > 0) {
        result += convertToWords((number / 1_000_000).toString()) + " Million "
        val remainingNumber = number % 1_000_000
        return result + convertToWords(remainingNumber.toString())
    }

    // Process thousands
    if (number / 1_000 > 0) {
        result += convertToWords((number / 1_000).toString()) + " Thousand "
        val remainingNumber = number % 1_000
        return result + convertToWords(remainingNumber.toString())
    }

    // Process hundreds
    if (number / 100 > 0) {
        result += convertToWords((number / 100).toString()) + " Hundred "
        val remainingNumber = number % 100
        return result + convertToWords(remainingNumber.toString())
    }

    // Process tens and units

    result += if (number < 10) {
        units[number]
    } else if (number < 20) {
        teens[number - 10]
    } else {
        tens[number / 10] + " " + units[number % 10]
    }

    return format(result)
}





