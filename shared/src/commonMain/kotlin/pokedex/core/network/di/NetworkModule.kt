package pokedex.core.network.di

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.cache.normalized.FetchPolicy
import com.apollographql.apollo3.cache.normalized.api.MemoryCacheFactory
import com.apollographql.apollo3.cache.normalized.api.NormalizedCacheFactory
import com.apollographql.apollo3.cache.normalized.fetchPolicy
import com.apollographql.apollo3.cache.normalized.normalizedCache
import com.apollographql.apollo3.cache.normalized.sql.SqlNormalizedCacheFactory
import com.apollographql.apollo3.network.http.LoggingInterceptor
import pokedex.core.network.createHttpClient
import org.koin.core.module.Module
import org.koin.dsl.module

val networkModule: (enableLogging: Boolean) -> Module
    get() = { enableLogging ->
        module {
            single { createHttpClient(enableLogging) }
            single<NormalizedCacheFactory> {
                MemoryCacheFactory(maxSizeBytes = 10 * 1024 * 1024)
                    .chain(SqlNormalizedCacheFactory("jdbc:sqlite:apollo.db"))
            }
            single {
                ApolloClient.Builder()
                    .serverUrl("https://beta.pokeapi.co/graphql/v1beta")
                    .normalizedCache(get())
                    .fetchPolicy(FetchPolicy.CacheFirst)
                    .apply {
                        if (enableLogging) {
                            addHttpInterceptor(LoggingInterceptor())
                        }
                    }
                    .build()
            }
        }
    }