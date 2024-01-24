package pokedex.ui.pokedex.loop

import com.apollographql.apollo3.ApolloClient
import kt.mobius.flow.ExecutionPolicy
import kt.mobius.flow.FlowTransformer
import kt.mobius.flow.subtypeEffectHandler
import org.koin.core.annotation.Factory
import pokedex.graphql.PokemonListQuery
import pokedex.model.toPokemon
import pokedex.ui.navigation.AppNavigation
import pokedex.ui.pokedex.loop.PokedexEffect as Effect
import pokedex.ui.pokedex.loop.PokedexEvent as Event

// The amount of items to request for each page
private const val PAGE_SIZE = 30

@Factory
class PokedexEffectHandler(
    private val apolloClient: ApolloClient,
    private val navigation: AppNavigation,
) : FlowTransformer<Effect, Event> by subtypeEffectHandler({

    addAction<Effect.NavigateBack>(navigation::navigateBack)

    addConsumer<Effect.NavigateToDetails> { (name) ->
        navigation.navigateToDetails(name)
    }

    addFunction<Effect.LoadPokemonList>(ExecutionPolicy.Sequential) { (offset) ->
        val query = PokemonListQuery(offset, PAGE_SIZE)
        val response = apolloClient.query(query).execute()

        if (response.hasErrors()) {
            Event.PokemonListFailed(response.errors?.firstOrNull()?.message)
        } else {
            val pokemonList = response.dataOrThrow().pokemon
                .map { it.pokemonBasics.toPokemon() }

            if (pokemonList.isEmpty()) {
                Event.LastPageLoaded
            } else {
                Event.PokemonListLoaded(pokemonList)
            }
        }
    }
})
