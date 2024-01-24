import SwiftUI
import pokedex

extension Pokemon {
    func typeColor() -> Color {
        return types.first!.typeColor()
    }
}

extension PokemonType {
    func typeColor() -> Color {
        return switch name {
        case "bug":Constants.Colors.bugColor
        case "grass":Constants.Colors.grassColor
        case "fire":Constants.Colors.fireColor
        case "poison":Constants.Colors.poisonColor
        case "fairy": Constants.Colors.fairyColor
        case "water": Constants.Colors.waterColor
        case "ground": Constants.Colors.groundColor
        case "fighting": Constants.Colors.fightingColor
        case "elecric": Constants.Colors.electricColor
        case "ice": Constants.Colors.iceColor
        case "dark": Constants.Colors.darkColor
        case "normal": Constants.Colors.normalColor
        case "dragon": Constants.Colors.dragonColor
        case "flying": Constants.Colors.flyingColor
        case "ghost": Constants.Colors.ghostColor
        case "psychic": Constants.Colors.psychicColor
        case "rock": Constants.Colors.rockColor
        case "steel": Constants.Colors.steelColor
        default:
            Constants.Colors.normalColor
        }
    }
}
