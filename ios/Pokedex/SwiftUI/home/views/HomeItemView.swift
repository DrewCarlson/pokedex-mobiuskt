import SwiftUI

struct HomeItemView: View {
    
    let title : String
    let color : Color
    
    var body: some View {
        
        ZStack(alignment: .leading) {
            Constants.Images.pokeballBackground
                .offset(x:-80, y: -40)
            
            HStack {
                Text(title)
                    .fontWeight(.bold)
                    .font(.title2)
                    .foregroundColor(.white)
                Spacer()
            }
            
            Constants.Images.pokeballBackground
                .resizable()
                .frame(width: 150, height: 150)
                .offset(x:270, y: -10)
        }
        .frame(maxHeight: 80)
        .padding(.horizontal, 30)
        .background(color)
        .cornerRadius(20)
        .contentShape(Rectangle())
    }
}

struct HomeItemView_Previews: PreviewProvider {
    static var previews: some View {
        VStack(spacing: 20) {
            HomeItemView(title: "Pokédex", color: Constants.Colors.grassColor)
            
            HomeItemView(title: "Moves", color: Constants.Colors.fireColor)
            
            HomeItemView(title: "Abilities", color: Constants.Colors.waterColor)
            
            HomeItemView(title: "Items", color: Constants.Colors.electricColor)
        }
        .padding(20)
    }
}
