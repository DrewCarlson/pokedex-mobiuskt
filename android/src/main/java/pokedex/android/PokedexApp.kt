package pokedex.android

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.ksp.generated.module
import pokedex.core.di.initKoin
import pokedex.ui.root.UiModule

class PokedexApp : Application() {

    override fun onCreate() {
        super.onCreate()

        initKoin(
            enableNetworkLogs = false//TODO: BuildConfig.DEBUG
        ) {
            androidContext(applicationContext)
            modules(UiModule().module)
        }
    }
}
