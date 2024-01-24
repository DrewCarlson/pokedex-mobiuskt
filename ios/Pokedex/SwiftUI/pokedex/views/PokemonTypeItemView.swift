import SwiftUI
import pokedex

struct PokemonTypeItemView: View {
    
    let type: PokemonType
    
    var body: some View {
        HStack(alignment: .center) {
            type.typeIcon()
                .resizable()
                .aspectRatio(contentMode: .fit)
                .frame(width: 12, height: 16)

            Text(type.name.capitalized)
                .foregroundColor(.white)
        }
        .padding(.horizontal, 12)
        .padding(.vertical, 5)
        .background(type.typeColor())
        .cornerRadius(12)
    }
}
