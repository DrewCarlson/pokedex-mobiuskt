package pokedex.core.database

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import org.koin.core.scope.Scope
import java.io.File


actual fun Scope.sqlDriverFactory(): SqlDriver {
    val databasePath = File(System.getProperty("java.io.tmpdir"), POKEMON_DB)
    val driver = JdbcSqliteDriver(url = "jdbc:sqlite:${databasePath.path}")
    PokemonDatabase.Schema.create(driver)
    return driver
}