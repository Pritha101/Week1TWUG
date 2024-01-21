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





