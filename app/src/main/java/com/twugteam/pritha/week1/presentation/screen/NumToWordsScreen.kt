package com.twugteam.pritha.week1.presentation.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.twugteam.pritha.week1.ui.theme.Week1Theme

@Composable
fun NumToWordsScreen(navController: NavController){

    var numberText by remember { mutableStateOf(" ") }
    var words by remember { mutableStateOf(" ") }

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
                words = convertToWords(numberText.toIntOrNull() ?: 0)

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
                "Words: ${convertToWords(numberText.toInt())}",
                modifier = Modifier.padding(8.dp),
                style = MaterialTheme.typography.displayMedium
            )
        }
    }
}

fun convertToWords(number: Int): String {
    val units = arrayOf("", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine")
    val teens = arrayOf("", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen")
    val tens = arrayOf("", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety")

    // Base cases
    if (number == 0) {
        return "Zero"
    }

    if (number < 0) {
        return "Negative " + convertToWords(-number)
    }

    var result = ""

    // Process millions
    if (number / 1_000_000 > 0) {
        result += convertToWords(number / 1_000_000) + " Million "
        val remainingNumber = number % 1_000_000
        return result + convertToWords(remainingNumber)
    }

    // Process thousands
    if (number / 1_000 > 0) {
        result += convertToWords(number / 1_000) + " Thousand "
        val remainingNumber = number % 1_000
        return result + convertToWords(remainingNumber)
    }

    // Process hundreds
    if (number / 100 > 0) {
        result += convertToWords(number / 100) + " Hundred "
        val remainingNumber = number % 100
        return result + convertToWords(remainingNumber)
    }

    // Process tens and units

    result += if (number < 10) {
        units[number]
    } else if (number < 20) {
        teens[number - 10]
    } else {
        tens[number / 10] + " " + units[number % 10]
    }

    return result.trim()
}





