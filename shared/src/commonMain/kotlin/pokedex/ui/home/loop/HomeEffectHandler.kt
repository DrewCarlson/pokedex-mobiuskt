package pokedex.ui.home.loop

import com.apollographql.apollo3.ApolloClient
import kt.mobius.flow.ExecutionPolicy
import kt.mobius.flow.FlowTransformer
import kt.mobius.flow.subtypeEffectHandler
//import org.koin.core.annotation.Factory
import pokedex.graphql.PokemonSearchQuery
import pokedex.model.toPokemon
import pokedex.ui.navigation.AppNavigation
import pokedex.ui.home.loop.HomeEffect as Effect
import pokedex.ui.home.loop.HomeEvent as Event

//@Factory
class HomeEffectHandler(
    private val apolloClient: ApolloClient,
    private val navigation: AppNavigation
) : FlowTransformer<Effect, Event> by subtypeEffectHandler({

    addConsumer<Effect.NavigateToDetails> { navigation.navigateToDetails(it.name) }

    addAction<Effect.NavigateToPokedex>(navigation::navigateToPokedex)

    addFunction<Effect.SearchPokedex>(ExecutionPolicy.Latest) { (query, offset) ->
        val searchQuery = PokemonSearchQuery(
            query = "$query%",
            limit = 20,
            offset = offset
        )

        val result = apolloClient.query(searchQuery).execute()
        if (result.hasErrors()) {
            val message = result.errors?.first()?.message.orEmpty()
            Event.OnSearchError(message)
        } else {
            val pokemon = result.dataOrThrow().pokemon
                .map { it.pokemonBasics.toPokemon() }
            Event.OnSearchComplete(
                query = query,
                results = pokemon
            )
        }
    }
})

