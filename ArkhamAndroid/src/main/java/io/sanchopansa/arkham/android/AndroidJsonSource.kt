package io.sanchopansa.arkham.android

import android.content.Context
import android.util.Log
import io.sanchopansa.arkham.api.JsonSource
import java.io.InputStream

class AndroidJsonSource(private val context: Context) : JsonSource {
    override fun openGameData(filename: String): InputStream? {
        return context.assets.open(filename)
    }
}