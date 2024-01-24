import SwiftUI
import pokedex

struct DetailsScreen : View {
    
    @EnvironmentObject var navigation: PokdexAppNavigation
    @State private var model: DetailsModel
    @State private var eventConsumer: ((DetailsEvent) -> Void)? = nil
    @State private var selectedTab = 0
    
    init(name: String) {
        self.model = DetailsModel.companion.create(name: name)
    }

    var body: some View {
        ZStack(alignment: .top) {
            model.pokemon?.typeColor().ignoresSafeArea(.all)
            
            VStack {
                HStack {
                    Text(model.pokemon?.numberString ?? "")
                        .foregroundColor(.white)
                        .fontWeight(.bold)
                    Spacer()
                }
                
                
                HStack(spacing:15) {
                    Text(model.pokemon?.name ?? "")
                        .foregroundColor(.white)
                        .font(.title)
                        .fontWeight(.bold)
                    
                    
                    /*Image(systemName: "speaker.wave.3")
                        .foregroundColor(.white)
                        .font(.title3)
                        .onTapGesture {
                            pokemonViewModel.playPokemonSound(id: pokemon.id)
                        }*/
                    
                    
                    Spacer()
                    
                    /*Text(pokemon.shape)
                        .foregroundColor(.white)
                        .fontWeight(.bold)*/
                }
                
                HStack {
                    ForEach(model.pokemon?.types ?? [], id: \.self) { type in
                        PokemonTypeItemView(type: type)
                    }
                    
                    Spacer()
                }
            }.padding(20)
            
            ZStack {
                
                Constants.Images.pokeballBackground
                    .resizable()
                    .frame(width: 180, height: 180)
                    .offset(x: 0, y: 20)
                
                
                AsyncImage(url: URL(string: model.pokemon?.imageUrl ?? "")) { image in
                    image
                        .resizable()
                        .aspectRatio(contentMode: .fit)
                        .frame(width: 200, height: 200)
                } placeholder: { ProgressView() }
                    
            }
            .offset(x:0, y: 120)
            .zIndex(2)
            
            VStack {
                
                Picker("", selection: $selectedTab) {
                    Text("About").tag(0)
                    Text("Stats").tag(1)
                    Text("Evolution").tag(2)
                    // Add more tabs as you wish
                }
                .pickerStyle(SegmentedPickerStyle())
                
                
                /*ScrollView {
                    switch selectedTab {
                    case 0:
                        PokemonAboutView(
                            description: pokemon.description,
                            height: pokemon.height,
                            weight: pokemon.weight,
                            abilities: pokemon.abilities,
                            growthRate: pokemon.growthRate,
                            captureRate: pokemon.captureRate,
                            baseHappiness: pokemon.baseHappiness
                            
                        )
                    case 1:
                        PokemonStatView(stats: pokemon.stats)
                    default :
                        PokemonEvolutionView(evolutionChain: pokemon.evolutionChain)
                    }
                }*/
                
            }
            .padding(.top, 40)
            .padding(.horizontal)
            .background(.white)
            .cornerRadius(30)
            .offset(y:280)
            
        }.navigationBarTitleDisplayMode(.inline)
            .bindLoop(
                loopFactory: {
                    FlowMobius.shared.loop(
                        update: DetailsUpdate(),
                        effectHandler: Dependencies.shared.getDetailsHandler(navigation: navigation)
                    )
                    .doInit(init: DetailsInit())
                    .logger(logger: SimpleLogger<AnyObject, AnyObject, AnyObject>(tag: "Details Screen"))
                },
                modelBinding: $model,
                consumerBinding: $eventConsumer
            )
    }
}
