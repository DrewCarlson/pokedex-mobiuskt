import SwiftUI
import pokedex


@main
struct PokedexApp: App {
    @Environment(\.scenePhase) private var scenePhase
    var body: some Scene {
        WindowGroup {
            GeometryReader { geo in
                /*ComposeViewControllerToSwiftUI(
                    topSafeArea: Float(geo.safeAreaInsets.top),
                    bottomSafeArea: Float(geo.safeAreaInsets.bottom)
                )
                .ignoresSafeArea()
                .onTapGesture {
                    // Hide keyboard on tap outside of TextField
                    UIApplication.shared.sendAction(#selector(UIResponder.resignFirstResponder), to: nil, from: nil, for: nil)
                }*/
                PokdexAppView()
                    .safeAreaInset(edge: .top) {
                        Color.clear.frame(height: geo.safeAreaInsets.top)
                    }
            }
        }
    }
}
