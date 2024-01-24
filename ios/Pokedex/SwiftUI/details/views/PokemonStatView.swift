import SwiftUI
import pokedex

struct PokemonStatView: View {
    
    let typeColor: Color
    let stats : [PokemonStat]
    @State private var progress = 0.0
    
    var body: some View {
        
        VStack {
            ForEach(stats, id: \.self) { stat in
                HStack {
                    HStack {
                        Text(stat.name)
                            .foregroundColor(.gray)
                        Spacer()
                        Text("\(stat.value)")
                            .fontWeight(.bold)
                            .padding(.horizontal, 4)
                    }
                    
                    HStack {
                        ProgressView(value: Float(progress) * Float(stat.value), total: Float(stat.maxValue))
                            .tint(typeColor)
                            .progressViewStyle(LinearProgressViewStyle())
                            .frame(height: 14)

                        Spacer()
                        Text("\(stat.maxValue)")
                            .fontWeight(.bold)
                            .padding(.horizontal, 4)
                    }
                }
            }
        }
        .padding(.all, 10)
        .onAppear {
            DispatchQueue.main.asyncAfter(deadline: .now()) {
                withAnimation(.easeInOut(duration: 2)) {
                    progress = 1.0
                }
            }
        }
    }
}
