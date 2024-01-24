import Foundation
import SwiftUI
import pokedex

struct PokedexItemView: View {
    
    let pokemon : Pokemon
    
    var body: some View {
        ZStack {
            VStack(alignment: .center) {
                ZStack(alignment:.center) {
                    Constants.Images.pokeballBackground
                        .resizable()
                    AsyncImage(url: URL(string: pokemon.imageUrl)) { image in
                        image
                            .resizable()
                            .aspectRatio(contentMode: .fit)
                    } placeholder: { ProgressView() }
                }
                .frame(width: 150, height: 150)
                
                Spacer()
                
                Text(self.pokemon.name.capitalized)
                    .font(.title2)
                    .foregroundColor(.white)
                    .padding(.top, 4)
                    .frame(alignment: .center)
                
                Spacer()

                Text(pokemon.idString)
                    .font(.subheadline)
                    .bold()
                    .foregroundColor(.white)
                    .padding(.init(top: 6, leading: 12, bottom: 6, trailing: 12))
                    .overlay(
                        RoundedRectangle(cornerRadius: 20)
                            .fill(Color.white.opacity(0.25))
                    )
                    .frame(width: 100, height: 24)
            }
            .frame(maxWidth: .infinity)
        }
        .padding(.vertical, 8)
        .background(pokemon.typeColor())
        .cornerRadius(12)
        .frame(maxWidth: .infinity)
    }
}
