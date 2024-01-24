package com.mocoding.pokedex.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.mocoding.pokedex.ui.ContentView
import com.mocoding.pokedex.core.di.initKoin
import moe.tlaster.precompose.PreComposeApp
import moe.tlaster.precompose.navigation.rememberNavigator
import org.koin.android.ext.koin.androidContext
import org.koin.compose.KoinApplication
import org.koin.compose.KoinContext
import org.koin.core.context.stopKoin

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val koinApp = initKoin(
            enableNetworkLogs = false//TODO: BuildConfig.DEBUG
        ) {
            androidContext(applicationContext)
        }

        setContent {
            KoinContext(koinApp.koin) {
                PreComposeApp {
                    val navigator = rememberNavigator()
                    ContentView(navigator)
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        stopKoin()
    }
}