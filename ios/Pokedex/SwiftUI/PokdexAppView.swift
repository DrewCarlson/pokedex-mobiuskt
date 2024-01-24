import SwiftUI
import pokedex

struct PokdexAppView : View {
    
    @StateObject var navigation = SwiftUINavigation()
    
    var body: some View {
        NavigationStack(path: $navigation.path) {
            HomeScreen()
                .environmentObject(navigation)
                .background { Color.background.ignoresSafeArea(.all) }
                .navigationDestination(for: String.self) { key in
                    switch key {
                    case "pokedex":
                        PokedexScreen()
                            .environmentObject(navigation)
                            .background { Color.background.ignoresSafeArea(.all) }
                    case "coming-soon":
                        Text("coming-soon")
                    case "favorite":
                        Text("favorite")
                    default:
                        if key.starts(with: "details") {
                            let name = key.trimmingPrefix("details/")
                            DetailsScreen(name: String(name))
                                .environmentObject(navigation)
                                .background { Color.background.ignoresSafeArea(.all) }
                        } else {
                            Text("Error!")
                        }
                }
            }
        }
    }
}
