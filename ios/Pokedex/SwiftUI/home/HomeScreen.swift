import SwiftUI
import pokedex

struct HomeScreen: View {
    
    @EnvironmentObject var navigation: PokdexAppNavigation
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
                    HStack {
                        Image(systemName: "magnifyingglass")
                            .foregroundColor(Color("DarkGreyColor"))
                            .frame(minWidth: 0, maxWidth: .infinity, alignment: .leading)
                            .padding(.horizontal, 20)
                        
                    }
                )
                .padding(.bottom, 12)
            
            HomeItemView(title: "Pokédex", color: Constants.Colors.fireColor)
                .onTapGesture {
                    eventConsumer?(HomeEvent.OpenPokedex())
                }
            
            HomeItemView(title: "Moves", color: Constants.Colors.electricColor)
                .onTapGesture {
                    eventConsumer?(HomeEvent.OpenComingSoon())
                }
            
            HomeItemView(title: "Evolutions", color: Constants.Colors.grassColor)
                .onTapGesture {
                    eventConsumer?(HomeEvent.OpenComingSoon())
                }
            
            HomeItemView(title: "Locations", color: Constants.Colors.waterColor)
                .onTapGesture {
                    eventConsumer?(HomeEvent.OpenComingSoon())
                }
            
            Spacer()
        }
        .frame(maxWidth: .infinity, alignment: .top)
        .padding(20)
        .navigationTitle("Home")
        .navigationBarTitleDisplayMode(.large)
        .bindLoop(
            loopFactory: {
                FlowMobius.shared.loop(
                    update: HomeUpdate(),
                    effectHandler: Dependencies.shared.getHomeHandler(navigation: navigation)
                )
                .logger(logger: SimpleLogger<AnyObject, AnyObject, AnyObject>(tag: "Home Screen"))
            },
            modelBinding: $model,
            consumerBinding: $eventConsumer
        )
    }
}

//
//struct HomeView_Previews: PreviewProvider {
//    static var previews: some View {
//        HomeView()
//    }
//}
