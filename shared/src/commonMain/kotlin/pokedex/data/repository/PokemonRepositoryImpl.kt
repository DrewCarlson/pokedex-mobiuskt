package pokedex.data.repository

import pokedex.core.database.dao.PokemonDao
import pokedex.core.database.dao.PokemonInfoDao
import pokedex.core.model.Pokemon
import pokedex.core.model.PokemonInfo
import pokedex.core.network.client.PokemonClient
import pokedex.data.toPokemon
import pokedex.data.toPokemonEntity
import pokedex.data.toPokemonInfo
import pokedex.data.toPokemonInfoEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PokemonRepositoryImpl(
    private val pokemonClient: PokemonClient,
    private val pokemonDao: PokemonDao,
    private val pokemonInfoDao: PokemonInfoDao
) : PokemonRepository {

    override suspend fun getPokemonList(page: Long): Result<List<Pokemon>> {
        return try {
            val cachedPokemonList = pokemonDao.selectAllByPage(page)

            if (cachedPokemonList.isEmpty()) {
                val response = pokemonClient.getPokemonList(page = page)
                response.results.forEach { pokemon ->
                    pokemonDao.insert(pokemon.toPokemonEntity(page))
                }

                Result.success(pokemonDao.selectAllByPage(page).map { it.toPokemon() })
            } else {
                Result.success(cachedPokemonList.map { it.toPokemon() })
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(e)
        }
    }

    override suspend fun getPokemonByName(name: String): Result<PokemonInfo> {
        return try {
            val cachedPokemon = pokemonInfoDao.selectOneByName(name = name)

            if (cachedPokemon == null) {
                val response = pokemonClient.getPokemonByName(name = name)
                pokemonInfoDao.insert(response.toPokemonInfoEntity())

                Result.success(response)
            } else {
                Result.success(cachedPokemon.toPokemonInfo())
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getFavoritePokemonListFlow(): Flow<List<Pokemon>> {
        return pokemonInfoDao.selectAllFavorite().map { list ->
            list.map { it.toPokemon() }
        }
    }

    override suspend fun updatePokemonFavoriteState(name: String, isFavorite: Boolean) {
        pokemonInfoDao.updateIsFavorite(
            name = name,
            isFavorite = isFavorite,
        )
    }

}