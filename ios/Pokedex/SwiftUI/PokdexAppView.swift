import SwiftUI
import pokedex

struct PokdexAppView : View {
    
    @StateObject var navigation = PokdexAppNavigation()
    
    var body: some View {
        NavigationStack(path: $navigation.path) {
            VStack {
                HomeScreen()
                    .environmentObject(navigation)
            }.navigationDestination(for: String.self) { key in
                switch key {
                case "pokedex":
                    PokedexScreen()
                        .environmentObject(navigation)
                case "coming-soon":
                    Text("coming-soon")
                case "favorite":
                    Text("favorite")
                default:
                    if key.starts(with: "details") {
                        let name = key.trimmingPrefix("details/")
                        DetailsScreen(name: String(name))
                            .environmentObject(navigation)
                    } else {
                        Text("Error!")
                    }
                }
            }
        }
    }
}
