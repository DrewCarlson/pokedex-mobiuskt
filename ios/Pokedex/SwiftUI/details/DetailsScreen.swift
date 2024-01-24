import SwiftUI
import pokedex

struct DetailsScreen : View {
    
    @EnvironmentObject var navigation: SwiftUINavigation
    @State private var model: DetailsModel
    @State private var eventConsumer: ((DetailsEvent) -> Void)? = nil
    
    init(name: String) {
        self.model = DetailsModel.companion.create(name: name)
    }

    var body: some View {
        ZStack(alignment: .top) {
            model.pokemon?.typeColor()
                .frame(height: 400)
                .ignoresSafeArea(.all)
            
            ScrollView {
                VStack {
                    ZStack {
                        Constants.Images.pokeballBackground
                            .resizable()
                            .padding(10)
                            .aspectRatio(contentMode: .fit)
                        
                        AsyncImage(url: URL(string: model.pokemon?.imageUrl ?? "")) { image in
                            image
                                .resizable()
                                .aspectRatio(contentMode: .fit)
                        } placeholder: { ProgressView() }
                    }
                    .padding(.horizontal, 40)
                    .padding(.bottom, 40)
                    .background(model.pokemon?.typeColor())
                    
                    VStack {
                        HStack {
                            Spacer()
                            Text(model.pokemon?.name.capitalized ?? "")
                                .font(.title)
                                .fontWeight(.bold)
                            Spacer()
                        }
                        HStack {
                            ForEach(model.pokemon?.types ?? [], id: \.name) { type in
                                PokemonTypeItemView(type: type)
                            }
                            
                            Spacer()
                            
                            VStack(alignment: .trailing) {
                                Text(model.pokemon?.idString ?? "")
                                    .fontWeight(.bold)
                                Text(model.pokemon?.genus ?? "")
                            }
                        }
                        .padding(.horizontal, 12)
                        
                        let pokemon = model.pokemon
                        if pokemon != nil {
                            PokemonPhysicalView(
                                description: pokemon!.description_,
                                height: Int(pokemon!.height),
                                weight: Float(pokemon!.weight)
                            )
                            
                            PokemonStatView(
                                typeColor: pokemon!.typeColor(),
                                stats: pokemon!.stats
                            )
                        }
                    }
                    .padding(.top, 16)
                    .padding(.horizontal)
                    .background(Color.background)
                    .cornerRadius(30)
                    .offset(y:-40)
                }
            }
            
        }.navigationBarTitleDisplayMode(.inline)
            .bindLoop(
                initFunc: DetailsInit(),
                modelBinding: $model,
                consumerBinding: $eventConsumer,
                loopFactory: FlowMobius.shared.loop(
                    update: DetailsUpdate(),
                    effectHandler: Dependencies.shared.getDetailsHandler()
                )
                .logger(logger: mobiusLogger(tag: "Details Screen"))
            )
    }
}
