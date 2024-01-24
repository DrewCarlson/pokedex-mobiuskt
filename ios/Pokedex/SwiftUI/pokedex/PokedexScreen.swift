import SwiftUI
import pokedex


struct PokedexScreen: View {
    
    @EnvironmentObject var navigation: SwiftUINavigation
    @State private var model: PokedexModel = PokedexModel.companion.create()
    @State private var eventConsumer: ((PokedexEvent) -> Void)? = nil
    
    var body: some View {
        VStack {
            ScrollView {
                LazyVGrid(columns: [GridItem(.adaptive(minimum: 150, maximum: 250))]) {
                    ForEach(model.pokemonList, id: \.self) { pokemon in
                        PokedexItemView(pokemon: pokemon)
                            .onAppear {
                                if model.pokemonList.firstIndex(of: pokemon) == model.pokemonList.count - 1 {
                                    eventConsumer?(PokedexEvent.LoadPokemonList())
                                }
                            }
                            .onTapGesture {
                                eventConsumer?(PokedexEvent.NavigateToDetails(name: pokemon.name))
                            }
                    }
                }
                .padding(.all, 20)
            }
        }
        .navigationTitle("Pokédex")
        .navigationBarTitleDisplayMode(.automatic)
        .bindLoop(
            initFunc: PokedexInit(),
            modelBinding: $model,
            consumerBinding: $eventConsumer,
            loopFactory: FlowMobius.shared.loop(
                update: PokedexUpdate(),
                effectHandler: Dependencies.shared.getPokedexHandler()
            )
            .logger(logger: mobiusLogger(tag: "Pokedex Screen"))
        )
    }
}
