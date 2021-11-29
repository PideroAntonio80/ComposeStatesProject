package com.example.composestatesproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composestatesproject.ui.theme.ComposeStatesProjectTheme
import org.w3c.dom.Text

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeStatesProjectTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    StateSample()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun StateSample() {
    /* Si en vez de "by" pongo "=" tendre que añadir ".value" después de cada text
     Con el "by" asegurarse de tener importado "import androidx.compose.runtime.getValue"
     y "import androidx.compose.runtime.setValue" o simplemente "import androidx.compose.runtime.*"
     El "remember" sería solo para la pantalla en vertical, pero si queremos que conserve el estado
     al rotarla, ponemos "rememberSaveable"*/
    var text by rememberSaveable { mutableStateOf("") }
    var change by rememberSaveable { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(64.dp),
        verticalArrangement = Arrangement.Center
    ) {
        TextField(
            value = text,
            onValueChange = { text = it},
            modifier = Modifier
                .fillMaxWidth()
        )
        Text(
            text = text,
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    if (change) {
                        Color.Blue
                    } else Color.Yellow
                )
                .padding(8.dp)
        )
        Button(
            onClick = { text = ""
                        change = !change
                      },
            modifier = Modifier.fillMaxWidth(),
            enabled = text.isNotEmpty()
        ) {
            Text(text = stringResource(R.string.button_clear_text))
        }
    }
}




