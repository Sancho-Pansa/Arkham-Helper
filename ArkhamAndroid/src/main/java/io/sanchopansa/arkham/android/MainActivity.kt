package io.sanchopansa.arkham.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import io.sanchopansa.arkham.android.ui.theme.AndroidTheme
import io.sanchopansa.arkham.core.investigators.Investigator
import io.sanchopansa.arkham.factories.JsonGameFactory

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        val jsonSource = AndroidJsonSource(applicationContext)
        val gameVault = JsonGameFactory(jsonSource).createVault()
        val player = gameVault.investigators.toList()[0]

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding),
                        player
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier, player: Investigator) {
    Column {
        Text(
            text = "Hello, ${player.name}"
        )
    }
}

@Composable
fun Health(name: String, modifier: Modifier) {

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    val context = LocalContext.current
    val jsonSource = AndroidJsonSource(context)
    val gameVault = JsonGameFactory(jsonSource).createVault()
    val player = gameVault.investigators.toList()[0]

    AndroidTheme {
        Greeting("Android", player =  player)
    }
}