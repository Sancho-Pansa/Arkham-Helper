package io.sanchopansa.arkham.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import io.sanchopansa.arkham.android.ui.PlayerSheetVm
import io.sanchopansa.arkham.android.ui.theme.AndroidTheme
import io.sanchopansa.arkham.factories.JsonGameFactory

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val jsonSource = AndroidJsonSource(applicationContext)
        val gameVault = JsonGameFactory(jsonSource).createVault()
        val player = gameVault.investigators.toList().singleOrNull { investigator -> investigator.name == "Аманда Шарп" }
        val playerVm: PlayerSheetVm by viewModels()
        playerVm.loadInvestigator(player!!)
        enableEdgeToEdge()
        setContent {
            AndroidTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    PlayerSheet(
                        modifier = Modifier.padding(innerPadding),
                        playerVm
                    )
                }
            }
        }
    }
}