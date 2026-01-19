package io.sanchopansa.arkham.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
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
        val player = gameVault.investigators.toList().singleOrNull { investigator -> investigator.name == "Аманда Шарп" }

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CharList(
                        modifier = Modifier.padding(innerPadding),
                        player!!
                    )
                }
            }
        }
    }
}