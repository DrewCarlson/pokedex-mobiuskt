package pokedex.data.repository

import pokedex.core.model.Pokemon
import pokedex.core.model.PokemonInfo
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {

    suspend fun getPokemonList(page: Long): Result<List<Pokemon>>

    suspend fun getPokemonByName(name: String): Result<PokemonInfo>
    suspend fun getFavoritePokemonListFlow(): Flow<List<Pokemon>>
    suspend fun updatePokemonFavoriteState(name: String, isFavorite: Boolean)

}