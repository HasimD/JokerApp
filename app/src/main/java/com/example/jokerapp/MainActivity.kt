package com.example.jokerapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jokerapp.ui.theme.JokerAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JokerAppTheme {
                Surface(color = MaterialTheme.colors.background) {
                    JokerScreen()
                }
            }
        }
    }
}

@Composable
fun JokerScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Setup", color = Color.Black, fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Spacer(Modifier.size(30.dp))
        Text(text = "Punchline", color = Color.Black, fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Spacer(Modifier.size(60.dp))
        Button(onClick = {}) {
            Text(text = "Tell me a joke.", fontSize = 20.sp)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JokerAppTheme {
        JokerScreen()
    }
}