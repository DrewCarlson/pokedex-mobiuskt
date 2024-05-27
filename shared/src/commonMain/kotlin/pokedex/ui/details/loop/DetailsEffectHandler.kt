package pokedex.ui.details.loop

import com.apollographql.apollo3.ApolloClient
import kt.mobius.flow.ExecutionPolicy
import kt.mobius.flow.FlowTransformer
import kt.mobius.flow.subtypeEffectHandler
//import org.koin.core.annotation.Factory
import pokedex.graphql.PokemonInfoByNameQuery
import pokedex.model.toPokemonInfo
import pokedex.ui.navigation.AppNavigation
import pokedex.ui.details.loop.DetailsEffect as Effect
import pokedex.ui.details.loop.DetailsEvent as Event

//@Factory
class DetailsEffectHandler(
    private val apolloClient: ApolloClient,
    private val navigation: AppNavigation
) : FlowTransformer<Effect, Event> by subtypeEffectHandler({

    addFunction<Effect.LoadPokemonInfo> { (name) ->
        val result = apolloClient.query(PokemonInfoByNameQuery(name)).execute()
        if (result.hasErrors()) {
            Event.PokemonInfoFailed(result.errors?.firstOrNull()?.message)
        } else {
            val pokemon = result.dataOrThrow().pokemon.first()
            Event.PokemonInfoLoaded(pokemon.toPokemonInfo())
        }
    }

    addConsumer<Effect.SetFavoriteState>(ExecutionPolicy.Latest) { (name, newState) ->

    }

    addAction<Effect.NavigateBack>(navigation::navigateBack)
})
