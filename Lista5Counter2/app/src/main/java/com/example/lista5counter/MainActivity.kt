package com.example.lista5counter

import android.os.Bundle
import android.widget.Space
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity(){
    override fun onCreate(savedInstanceState: Bundle?)  {
        super.onCreate(savedInstanceState)

        setContent{
            Calculator()
        }
    }
}

@Composable
fun Counter(){
    var counter by rememberSaveable { mutableIntStateOf(0) }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Spacer(modifier = Modifier.weight(1f))

        Text(
            text = counter.toString(),
            fontSize = 250.sp,
            textAlign = TextAlign.Center,
        )

        Spacer(modifier = Modifier.weight(1f))

        Button(
            modifier = Modifier.fillMaxWidth(),
            shape = RectangleShape,
            onClick = { counter = 0 }
        ) {
            Text(text = "Reset")
        }

        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                modifier = Modifier.weight(1f),
                shape = RectangleShape,
                onClick = { counter++ }
            ) {
                Text(text = "Count UP")
            }

            Button(
                modifier = Modifier.weight(1f),
                shape = RectangleShape,
                onClick = { counter-- }
            ) {
                Text(text = "Count DOWN")
            }
        }
    }
}

@Composable
fun Calculator() {
    var firstNumber by rememberSaveable { mutableStateOf("") }
    var secondNumber by rememberSaveable { mutableStateOf("") }
    var result by rememberSaveable { mutableStateOf("N/A")}

    fun calculate(operation: String) {
        val a = firstNumber.toIntOrNull()
        val b = secondNumber.toIntOrNull()

        if (a == null || b == null) {
            result = "N/A"
            return
        }

        result = when (operation){
            "+" -> (a + b).toString()
            "-" -> (a - b).toString()
            "*" -> (a * b).toString()
            "/" -> {
                if(b == 0) {
                    "Nie dziel przez 0"
                } else {
                (a / b).toString()
                }
            } else -> "Bład"
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.weight(1f))

        TextField(
            value = firstNumber,
            onValueChange = { firstNumber = it},
            label = { Text("Enter first number")},
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            ),
            modifier = Modifier.fillMaxWidth()
        )

        TextField(
            value = secondNumber,
            onValueChange = { secondNumber = it},
            label = { Text("Enter second number")},
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            ),
            modifier = Modifier.fillMaxWidth()
        )

        Row {
            Button(
                onClick = { calculate("+") },
                shape = RectangleShape
            ) {
                Text("+")
            }

            Button(
                onClick = { calculate("-") },
                shape = RectangleShape
            ) {
                Text("-")
            }

            Button(
                onClick = { calculate("*") },
                shape = RectangleShape
            ) {
                Text("*")
            }

            Button(
                onClick = { calculate("/") },
                shape = RectangleShape
            ) {
                Text("/")
            }
        }
        Text(
            text = "Result: $result",
            fontSize = 20.sp
        )

        Spacer(modifier = Modifier.weight(1f))


    }

}