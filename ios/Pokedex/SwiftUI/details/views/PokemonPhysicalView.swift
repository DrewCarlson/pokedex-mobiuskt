import SwiftUI
import pokedex


struct PokemonPhysicalView: View {
    
    let description : String
    let height : Int
    let weight : Float
    
    var body: some View {
        VStack {
            Text(description)
                .multilineTextAlignment(.center)
            
            HStack {
                Spacer()
                
                VStack(alignment: .center) {
                    HStack {
                        Image(systemName: "scalemass")
                        Text("\(String(format: "%.1f", weight / 10)) kg")
                            .fontWeight(.bold)
                    }
                    
                    HStack {
                        Text("Weight")
                    }
                }
                
                Spacer()
                
                VStack(alignment: .center) {
                    HStack {
                        Image(systemName: "ruler")
                            .rotationEffect(Angle(degrees: 90))
                            .frame(width: 12)
                        Text("\(String(format: "%.1f", Float(height) / 10)) m")
                            .fontWeight(.bold)
                    }
                    
                    HStack {
                        Text("Height")
                    }
                }
                
                Spacer()
            }
            .padding(.all, 20)
            .background(.gray.opacity(0.2))
            .cornerRadius(20)
            .padding(.all, 20)
        }
        .padding(.all, 10)
        .padding(.bottom, 0)
    }
}
