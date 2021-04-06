package com.example.jokerapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jokerapp.Constants.EMPTY
import com.example.jokerapp.Constants.STATE_PUNCHLINE
import com.example.jokerapp.Constants.STATE_SETUP
import com.example.jokerapp.Constants.STATE_START
import com.example.jokerapp.Constants.TELL_ME_A_JOKE
import com.example.jokerapp.Constants.TEN_SECONDS
import com.example.jokerapp.Constants.WHAT_IS_THE_ANSWER
import com.example.jokerapp.ui.theme.JokerAppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JokerAppTheme {
                Surface(color = MaterialTheme.colors.background) {
                    JokerScreen(viewModel)
                }
            }
        }
    }
}

@Composable
fun JokerScreen(viewModel: MainViewModel) {
    val joke = viewModel.joke.observeAsState()
    val state = remember { mutableStateOf(STATE_START) }

    val setup = if (state.value == STATE_START) EMPTY else joke.value!!.setup
    val punchline = if (state.value != STATE_PUNCHLINE) EMPTY else joke.value!!.punchline
    val buttonText = if (state.value != STATE_SETUP) TELL_ME_A_JOKE else WHAT_IS_THE_ANSWER

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = setup,
            color = Color.Black,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        Spacer(Modifier.size(30.dp))
        Text(
            text = punchline,
            color = Color.Black,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        Spacer(Modifier.size(60.dp))
        Button(onClick = {
            when (state.value) {
                STATE_START -> {
                    viewModel.getRandomJoke().invokeOnCompletion {
                        state.value = STATE_SETUP
                    }
                }
                STATE_SETUP -> {
                    state.value = STATE_PUNCHLINE
                    GlobalScope.launch {
                        delay(TEN_SECONDS)
                        if (state.value != STATE_SETUP) {
                            state.value = STATE_START
                        }
                    }
                }
                STATE_PUNCHLINE -> {
                    viewModel.getRandomJoke().invokeOnCompletion {
                        state.value = STATE_SETUP
                    }
                }
            }
        }
        ) {
            Text(
                text = buttonText,
                fontSize = 20.sp
            )
        }
    }
}

object Constants {
    const val STATE_START = "stateStart"
    const val STATE_SETUP = "stateSetup"
    const val STATE_PUNCHLINE = "statePunchline"
    const val WHAT_IS_THE_ANSWER = "What's the answer?"
    const val TELL_ME_A_JOKE = "Tell me a joke."
    const val EMPTY = "-"
    const val TEN_SECONDS = 10000L
}