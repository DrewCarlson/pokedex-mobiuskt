package pokedex.core.network.client

import pokedex.core.model.PokemonInfo
import pokedex.core.network.helper.handleErrors
import pokedex.core.network.model.PokemonResponse
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.http.contentType

class PokemonClient(
    private val httpClient: HttpClient
) {

    suspend fun getPokemonList(
        page: Long,
    ): PokemonResponse {
        return handleErrors {
            httpClient.get(Pokemon.ROUTE) {
                url {
                    parameters.append("limit", PAGE_SIZE.toString())
                    parameters.append("offset", (page * PAGE_SIZE).toString())
                }
                contentType(ContentType.Application.Json)
            }
        }
    }

    suspend fun getPokemonByName(
        name: String,
    ): PokemonInfo {
        return handleErrors {
            httpClient.get(Pokemon.byName(name)) {
                contentType(ContentType.Application.Json)
            }
        }
    }

    private object Pokemon {
        const val ROUTE = BASE_URL + "pokemon"
        val byName: (String) -> String = { name -> "$ROUTE/$name" }
    }

    companion object {
        private const val BASE_URL = "https://pokeapi.co/api/v2/"
        private const val PAGE_SIZE = 20
    }
}