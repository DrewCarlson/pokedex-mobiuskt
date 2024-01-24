package pokedex.ui.theme

import androidx.compose.ui.graphics.Color
import pokedex.model.Pokemon
import pokedex.model.PokemonInfo
import pokedex.model.PokemonType

val Black = Color(0xFF090f0b)

val Green300 = Color(0xFF2EB688)
val Green400 = Color(0xFF145526)
val Green500 = Color(0xFF046D4A)

val Red300 = Color(0xFFF33736)
val Red400 = Color(0xFFcb290b)
val Red500 = Color(0xFF9C2221)

val Blue300 = Color(0xFF54B1DF)
val Blue500 = Color(0xFF1E3DA8)

val Yellow300 = Color(0xFFF1A22C)
val Yellow400 = Color(0xFFfaae41)
val Yellow500 = Color(0xFFCB5C0D)

val Blue400 = Color(0xFF4572E8)

val LightGray400 = Color(0xFFb8b6b3)

val DarkGray400 = Color(0xFF3e4047)

val Gray400 = Color(0xFF595C61)

val BugLight = Color(0xFFABBC42)
val DarkLight = Color(0xFF595265)
val DragonLight = Color(0xFF435FD4)
val ElectricLight = Color(0xFFF8D97E)
val FairyLight = Color(0xFFE894B8)
val FireLight = Color(0xFFEA7472)
val FlyingLight = Color(0xFF9FC6F5)
val GhostLight = Color(0xFF715585)
val IceLight = Color(0xFFA0C7F4)
val PoisonLight = Color(0xFF8A6C9F)
val PsychicLight = Color(0xFFCF678B)
val RockLight = Color(0xFFA17771)
val SteelLight = Color(0xFF949494)
val WaterLight = Color(0xFF87BBF9)
val FightingLight = Color(0xFFCD6C65)
val GrassLight = Color(0xFF71CDB2)
val GroundLight = Color(0xFFB28B83)
val NormalLight = Color(0xFFBEBEBE)

val Bug = Color(0xFFA6B91A)
val Dark = Color(0xFF705746)
val Dragon = Color(0xFF6F35FC)
val Electric = Color(0xFFF7D02C)
val Fairy = Color(0xFFD685AD)
val Fire = Color(0xFFEE8130)
val Flying = Color(0xFFA98FF3)
val Ghost = Color(0xFF735797)
val Ice = Color(0xFF96D9D6)
val Poison = Color(0xFFA33EA1)
val Psychic = Color(0xFFF95587)
val Rock = Color(0xFFB6A136)
val Steel = Color(0xFFB7B7CE)
val Water = Color(0xFF6390F0)
val Fighting = Color(0xFFC22E28)
val Grass = Color(0xFF7AC74C)
val Ground = Color(0xFFE2BF65)
val Normal = Color(0xFFA8A77A)

fun Pokemon.composeColor(): Color {
    return types.first().composeColor()
}

fun PokemonType.composeColor(): Color {
    return name.composeColor()
}

fun PokemonInfo.composeColor(): Color {
    return types.first().composeColor()
}

private fun String.composeColor2(): Color {
    return when (this) {
        "bug" -> BugLight
        "dark" -> DarkLight
        "dragon" -> DragonLight
        "electric" -> ElectricLight
        "fairy" -> FairyLight
        "fire" -> FireLight
        "flying" -> FlyingLight
        "ghost" -> GhostLight
        "ice" -> IceLight
        "poison" -> PoisonLight
        "psychic" -> PsychicLight
        "rock" -> RockLight
        "steel" -> SteelLight
        "water" -> WaterLight
        "fighting" -> FightingLight
        "grass" -> GrassLight
        "ground" -> GroundLight
        "normal" -> NormalLight
        else -> error("Unknown type: $this")
    }
}

private fun String.composeColor(): Color {
    return when (this) {
        "bug" -> Bug
        "dark" -> Dark
        "dragon" -> Dragon
        "electric" -> Electric
        "fairy" -> Fairy
        "fire" -> Fire
        "flying" -> Flying
        "ghost" -> Ghost
        "ice" -> Ice
        "poison" -> Poison
        "psychic" -> Psychic
        "rock" -> Rock
        "steel" -> Steel
        "water" -> Water
        "fighting" -> Fighting
        "grass" -> Grass
        "ground" -> Ground
        "normal" -> Normal
        else -> error("Unknown type: $this")
    }
}
