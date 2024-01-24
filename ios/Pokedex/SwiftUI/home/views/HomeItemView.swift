import SwiftUI

struct HomeItemView: View {
    
    let title : String
    let color : Color
    
    var body: some View {
        
        ZStack(alignment: .trailing) {
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
                .offset(x: 80)
        }
        .frame(maxHeight: 80)
        .padding(.horizontal, 30)
        .background(color)
        .cornerRadius(20)
        .contentShape(Rectangle())
    }
}
