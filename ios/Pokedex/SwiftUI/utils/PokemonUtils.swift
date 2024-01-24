import SwiftUI
import pokedex

extension Pokemon {
    func typeColor() -> Color {
        return types.first!.typeColor()
    }
    
    func typeImage() -> Image {
        return types.first!.typeIcon()
    }
}

extension PokemonInfo {
    func typeColor() -> Color {
        return types.first!.typeColor()
    }
    
    func typeImage() -> Image {
        return types.first!.typeIcon()
    }
}

extension PokemonType {
    func typeColor() -> Color {
        return switch name {
        case "bug": Color.bug
        case "grass": Color.grass
        case "fire": Color.fire
        case "poison": Color.poison
        case "fairy": Color.fairy
        case "water": Color.water
        case "ground": Color.ground
        case "fighting": Color.fighting
        case "elecric": Color.electric
        case "ice": Color.ice
        case "dark": Color.dark
        case "normal": Color.normal
        case "dragon": Color.dragon
        case "flying": Color.flying
        case "ghost": Color.ghost
        case "psychic": Color.psychic
        case "rock": Color.rock
        case "steel": Color.steel
        default:
            Color.normal
        }
    }

    func typeIcon() -> Image {
        return switch name {
        case "bug": Constants.Images.bugImage
        case "grass": Constants.Images.grassImage
        case "fire": Constants.Images.fireImage
        case "poison": Constants.Images.poisonImage
        case "fairy": Constants.Images.fairyImage
        case "water": Constants.Images.waterImage
        case "ground": Constants.Images.groundImage
        case "fighting": Constants.Images.fightingImage
        case "elecric": Constants.Images.electricImage
        case "ice": Constants.Images.iceImage
        case "dark": Constants.Images.darkImage
        case "normal": Constants.Images.normalImage
        case "dragon": Constants.Images.dragonImage
        case "flying": Constants.Images.flyingImage
        case "ghost": Constants.Images.ghostImage
        case "psychic": Constants.Images.psychicImage
        case "rock": Constants.Images.rockImage
        case "steel": Constants.Images.steelImage
        default:
            Constants.Images.normalImage
        }
    }
}
