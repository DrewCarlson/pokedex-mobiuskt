import SwiftUI
import pokedex


@main
struct PokedexApp: App {
    
    @Environment(\.scenePhase) private var scenePhase
    
    var body: some Scene {
        WindowGroup {
            GeometryReader { geo in
                if ProcessInfo.processInfo.arguments.contains("-compose") {
                    ComposeView(
                        topSafeArea: Float(geo.safeAreaInsets.top),
                        bottomSafeArea: Float(geo.safeAreaInsets.bottom)
                    )
                    .ignoresSafeArea()
                    .onTapGesture {
                        UIApplication.shared.sendAction(#selector(UIResponder.resignFirstResponder), to: nil, from: nil, for: nil)
                    }
                } else {
                    PokdexAppView()
                        .safeAreaInset(edge: .top) {
                            Color.clear.frame(height: geo.safeAreaInsets.top)
                        }
                }
            }
        }
    }
}
