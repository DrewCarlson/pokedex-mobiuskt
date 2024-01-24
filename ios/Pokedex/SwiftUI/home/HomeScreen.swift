import SwiftUI
import pokedex

struct HomeScreen: View {
    
    @EnvironmentObject var navigation: SwiftUINavigation
    @State private var model: HomeModel = HomeModel.companion.create()
    @State private var eventConsumer: ((HomeEvent) -> Void)? = nil
    @State private var searchText = ""
    
    var body: some View {
        VStack {
            TextField("Search a pokemon", text: $searchText)
                .onSubmit { eventConsumer?(HomeEvent.SearchPokedex(query: searchText)) }
                .disableAutocorrection(true)
                .padding(16)
                .padding(.horizontal, 45)
                .background(Color(.systemGray6))
                .cornerRadius(40)
                .overlay(
                    HStack(alignment: .center) {
                        Image(systemName: "magnifyingglass")
                            .foregroundColor(Color("DarkGreyColor"))
                            .frame(minWidth: 0, maxWidth: .infinity, alignment: .leading)
                            .padding(.horizontal, 20)
                        Spacer()
                        if !searchText.isEmpty {
                            Image(systemName: "xmark.circle.fill")
                                .foregroundColor(Color("DarkGreyColor"))
                                .onTapGesture {
                                    searchText = ""
                                    eventConsumer?(HomeEvent.ClearSearch())
                                }
                                .padding(.vertical, 10)
                                .padding(.horizontal, 20)
                        }
                    }
                )
                .padding(.bottom, 12)
            
            if model.searchQuery.isEmpty {
                HomeItemView(title: "Pokédex", color: Color.fireColorLight)
                    .onTapGesture {
                        eventConsumer?(HomeEvent.OpenPokedex())
                    }
                
                HomeItemView(title: "Moves", color: Color.electricColorLight)
                    .onTapGesture {
                        eventConsumer?(HomeEvent.OpenComingSoon())
                    }
                
                HomeItemView(title: "Evolutions", color: Color.grassColorLight)
                    .onTapGesture {
                        eventConsumer?(HomeEvent.OpenComingSoon())
                    }
                
                HomeItemView(title: "Locations", color: Color.waterColorLight)
                    .onTapGesture {
                        eventConsumer?(HomeEvent.OpenComingSoon())
                    }
            } else {
                ScrollView {
                    LazyVGrid(columns: [GridItem(.adaptive(minimum: 150, maximum: 250))]) {
                        ForEach(model.results, id: \.self) { pokemon in
                            PokedexItemView(pokemon: pokemon)
                                .onAppear {
                                    if model.results.firstIndex(of: pokemon) == model.results.count - 1 {
                                        eventConsumer?(HomeEvent.LoadMoreResults())
                                    }
                                }
                                .onTapGesture {
                                    eventConsumer?(HomeEvent.NavigateToDetails(name: pokemon.name))
                                }
                        }
                    }
                }
            }
            
            
            Spacer()
        }
        .frame(maxWidth: .infinity, alignment: .top)
        .padding(20)
        .navigationTitle("Home")
        .navigationBarTitleDisplayMode(.large)
        .bindLoop(
            initFunc: HomeInit(),
            modelBinding: $model,
            consumerBinding: $eventConsumer,
            loopFactory: FlowMobius.shared.loop(
                update: HomeUpdate(),
                effectHandler: Dependencies.shared.getHomeHandler()
            )
            .logger(logger: mobiusLogger(tag: "Home Screen"))
        )
    }
}
