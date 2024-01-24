package pokedex.core.network.model

import pokedex.core.model.Pokemon
import kotlinx.serialization.Serializable

@Serializable
data class PokemonResponse(
  val count: Int,
  val next: String?,
  val previous: String?,
  val results: List<Pokemon>
)
