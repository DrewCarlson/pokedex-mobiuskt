import Foundation
import SwiftUI
import pokedex

struct PokedexItemView: View {
    
    let pokemon : Pokemon
    
    var body: some View {
        ZStack {
            VStack(alignment: .center) {
                AsyncImage(url: URL(string: pokemon.imageUrl)) { image in
                    image
                        .resizable()
                        .aspectRatio(contentMode: .fit)
                        .frame(width: 150, height: 150)
                } placeholder: { ProgressView() }
                    .frame(width: 150, height: 150)
                
                    Spacer()
                
                Text(self.pokemon.name.capitalized)
                    .font(.title2)
                    .foregroundColor(.white)
                    .padding(.top, 4)
                    .frame(alignment: .center)
                Spacer()

                Text(pokemon.numberString)
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
        //.shadow(color: Color(viewModel.backgroundColor), radius: 6, x: 0, y: 0)
    }
}

/*struct PokemonItemView_Previews: PreviewProvider {
    static var previews: some View {
        
        let bulbasaur = Pokemon(
            id: 1,
            name: "Bulbasaur",
            types: [.grass, .poison],
            description: "",
            height: 0,
            weight: 0,
            sprites: PokemonSprites(
                frontDefaultOfficialArtwork: "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png", frontShinyOfficialArtwork: ""
            ),
            stats: [PokemonStat](),
            abilities: [],
            growthRate: "medium",
            captureRate : 28,
            baseHappiness: 46,
            shape: "quadriped",
            evolutionChain: PokemonEvolutionChain(pokemonId: 1, level: nil, item: nil, evolutionChain: nil)
        )

        
        VStack {
            PokemonItemView(pokemon:bulbasaur).previewLayout(.sizeThatFits)
        }.padding(.all, 18)
    }
}*/
