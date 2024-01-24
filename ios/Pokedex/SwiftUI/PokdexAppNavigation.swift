import SwiftUI
import pokedex


class PokdexAppNavigation : HomeNavigation,
                            PokedexNavigation,
                            DetailsNavigation,
                            ObservableObject {
    
    @Published var path: NavigationPath = NavigationPath()
    
    func navigateBack() {
        DispatchQueue.main.async { [weak self] in
            self?.path.removeLast()
        }
    }
    
    func navigateToPokedex() {
        DispatchQueue.main.async { [weak self] in
            self?.path.append("pokedex")
        }
    }
    
    func navigateToDetails(name: String) {
        DispatchQueue.main.async { [weak self] in
            self?.path.append("details/\(name)")
        }
    }
}
