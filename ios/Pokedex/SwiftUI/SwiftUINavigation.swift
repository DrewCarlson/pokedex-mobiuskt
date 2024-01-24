import SwiftUI
import pokedex


class SwiftUINavigation : AppNavigation, ObservableObject {
    
    @Published var path: NavigationPath = NavigationPath()
    
    init() {
        Dependencies.shared.initialize(navigation: self)
    }
    
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
    
    func navigateToFavorites() {
        DispatchQueue.main.async { [weak self] in
            self?.path.append("coming-soon")
        }
    }
}
