import SwiftUI
import pokedex

struct PokemonTypeItemView: View {
    
    let type: PokemonType
    
    var body: some View {
        Text(type.name)
            .fontWeight(.bold)
            .foregroundColor(.white)
            .padding(.horizontal, 20)
            .padding(.vertical, 5)
            .background(Color("TypeBackgroundColor"))
            .cornerRadius(12)
    }
}

struct PokemonTypeItemView_Previews: PreviewProvider {
    static var previews: some View {
        PokemonTypeItemView(type: PokemonType(name: "bug", slot: Int32(-1)))
    }
}
