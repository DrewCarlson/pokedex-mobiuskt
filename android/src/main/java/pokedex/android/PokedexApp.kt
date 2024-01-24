package pokedex.android

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.ksp.generated.module
import pokedex.di.initKoin
import pokedex.ui.root.UiModule

class PokedexApp : Application() {

    override fun onCreate() {
        super.onCreate()

        initKoin(
            "pokedex.db",
            enableNetworkLogs = false
        ) {
            androidContext(applicationContext)
            modules(UiModule().module)
        }
    }
}
