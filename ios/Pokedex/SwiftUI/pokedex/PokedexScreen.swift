import SwiftUI
import pokedex


struct PokedexScreen: View {
    
    @EnvironmentObject var navigation: PokdexAppNavigation
    @State private var model: PokedexModel = PokedexModel.companion.create()
    @State private var eventConsumer: ((PokedexEvent) -> Void)? = nil
    
    var body: some View {
        ZStack {
            VStack {
                TextField("Search a pokemon",
                          text: Binding(
                            get: { model.searchValue },
                            set: {
                                eventConsumer?(PokedexEvent.SearchValueUpdated(searchValue: $0))
                            }))
                    .disableAutocorrection(true)
                    .padding(16)
                    .padding(.horizontal, 45)
                    .background(Color(.systemGray6))
                    .cornerRadius(40)
                    .overlay(
                        HStack {
                            Image(systemName: "magnifyingglass")
                                .foregroundColor(Color("DarkGreyColor"))
                                .frame(minWidth: 0, maxWidth: .infinity, alignment: .leading)
                                .padding(.horizontal, 20)
                            
                        }
                    )
                    .padding(.horizontal, 20)
                
                
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
            .toolbar {
                HStack {
                    Image(systemName: "slider.horizontal.3")
                }
                
            }
        }
        .bindLoop(
            loopFactory: {
                FlowMobius.shared
                    .loop(
                        update: PokedexUpdate(),
                        effectHandler: Dependencies.shared.getPokedexHandler(navigation:navigation)
                    )
                    .doInit(init: PokedexInit())
                    .logger(logger: SimpleLogger<AnyObject, AnyObject, AnyObject>(tag: "Pokedex Screen"))
            },
              modelBinding: $model,
              consumerBinding: $eventConsumer)
    }
}

class PokexdexNavigationImpl : PokedexNavigation {
    
    private let _navigateBack: () -> Void
    private let _navigateToDetails: (String) -> Void
    
    init(navigateBack: @escaping () -> Void, navigateToDetails: @escaping (String) -> Void) {
        self._navigateBack = navigateBack
        self._navigateToDetails = navigateToDetails
    }
    
    func navigateBack() {
        _navigateBack()
    }
    
    func navigateToDetails(name: String) {
        _navigateToDetails(name)
    }
    
}

//
//struct PokedexScreen_Previews: PreviewProvider {
//    static var previews: some View {
//        PokedexScreen()
//    }
//}
