package pokedex.di

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.cache.normalized.FetchPolicy
import com.apollographql.apollo3.cache.normalized.api.MemoryCacheFactory
import com.apollographql.apollo3.cache.normalized.api.NormalizedCacheFactory
import com.apollographql.apollo3.cache.normalized.fetchPolicy
import com.apollographql.apollo3.cache.normalized.normalizedCache
//import com.apollographql.apollo3.cache.normalized.sql.SqlNormalizedCacheFactory
//import com.apollographql.apollo3.network.ktorClient
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.module.Module
import org.koin.dsl.module

val networkModule: (dbUrl: String, enableLogging: Boolean) -> Module = { dbUrl, enableLogging ->
    module {
        single {
            HttpClient {
                install(ContentNegotiation) {
                    json(Json {
                        ignoreUnknownKeys = true
                    })
                }

                if (enableLogging) {
                    install(Logging) {
                        logger = Logger.SIMPLE
                        level = LogLevel.ALL
                    }
                }
            }
        }
        single<NormalizedCacheFactory> {
            MemoryCacheFactory(maxSizeBytes = 10 * 1024 * 1024)
                //.chain(SqlNormalizedCacheFactory(dbUrl))
        }
        single {
            ApolloClient.Builder()
                .serverUrl("https://beta.pokeapi.co/graphql/v1beta")
                .normalizedCache(get())
                //.ktorClient(get())
                .fetchPolicy(FetchPolicy.CacheFirst)
                .build()
        }
    }
}