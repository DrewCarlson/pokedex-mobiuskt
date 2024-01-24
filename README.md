# Mobius.kt Pokedex

A demo application using various approaches to connecting multiplatform [Mobius.kt](https://drewcarlson.github.io/mobius.kt/) code with UI Frameworks.

- Supports: [SwiftUI](https://developer.apple.com/xcode/swiftui/), [Compose Multiplatform](https://www.jetbrains.com/lp/compose-multiplatform/)
- In progress: Compose for HTML
- Planned: Compose WASM

This project is based on [MohamedRejeb/Pokedex](https://github.com/MohamedRejeb/Pokedex) and
https://github.com/ThomasBernard03/PokedexSwiftUI
https://github.com/MatheusFerreiraZx/PokedexUI
https://github.com/oskarko/Pokedex


TODO:

- Remove cocoapods
- Update iOS Xcode project and compose UI setup
- Split compose UI into a new module
- Add SwiftUI implementation
- Finish readme


Pokedex is kotlin multiplatform project with 99% shared code, built with Compose multiplatform, Coroutines, Flow, Decompose, MVIKotlin, Koin, Ktor, SqlDelight, and Material 3 based on MVI architecture
<br>
<br>

![pokedex](https://user-images.githubusercontent.com/41842296/224551967-1c09e59d-25c2-4a7b-ace8-4676cfd26672.png)

## Open-source libraries
- [Mobius.kt](https://drewcarlson.github.io/mobius.kt/) for presentation architecture.
- [Kotlin](https://kotlinlang.org/) based, [Coroutines](https://github.com/Kotlin/kotlinx.coroutines) + [Flow](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/) for asynchronous.
- [Kotlin Serialization](https://github.com/Kotlin/kotlinx.serialization): Kotlin multiplatform / multi-format serialization.
- [Compose multiplatform](https://github.com/JetBrains/compose-multiplatform): a modern UI framework for Kotlin.
- [PreCompose](https://tlaster.github.io/PreCompose/): for navigation.
- [Ktor](https://github.com/ktorio/ktor): for making network requests.
- [SqlDelight](https://github.com/cashapp/sqldelight): for caching data.
- [Koin](https://github.com/InsertKoinIO/koin): a pragmatic lightweight dependency injection framework.
- [Material 3](https://m3.material.io/components): Material 3 components.

## Screenshots
  ### Android
  
  <div style="display: flex; width: 100%">
  <img src="https://user-images.githubusercontent.com/41842296/224555659-f75bcddc-21a9-42f4-804d-198a5f06dcb1.png" width="31%"/>
  <img src="https://user-images.githubusercontent.com/41842296/224555672-03a6dcd5-f26f-4ecf-aa1e-3db66d278b8b.png" width="31%"/>
  <img src="https://user-images.githubusercontent.com/41842296/224555677-1fc807fc-57c6-46e0-bb27-afa085181a5c.png" width="31%"/>
  </div>
  
  ### IOS
  
  <div style="display: flex">
  <img src="https://user-images.githubusercontent.com/41842296/224555698-71d1fb5d-9359-483b-8d98-64f952a44a60.png" width="31%"/>
  <img src="https://user-images.githubusercontent.com/41842296/224555703-5a53cc2c-5375-4b07-bac1-aed03f34ca87.png" width="31%"/>
  <img src="https://user-images.githubusercontent.com/41842296/224555708-05edf0fa-7b74-4f2b-b4fd-f4d7d82e911b.png" width="31%"/>
  </div>
  
  ### Desktop
  <img width="1511" alt="Screenshot 2023-03-12 at 4 44 04 PM" src="https://user-images.githubusercontent.com/41842296/224555755-5d033ac5-061e-41d7-92b1-4e5c807dfb67.png">



## PokeAPI

<div>
Pokedex uses <a href="https://pokeapi.co/">PokeAPI</a> for fetching data related to Pokémon.

<img src="https://user-images.githubusercontent.com/24237865/83422649-d1b1d980-a464-11ea-8c91-a24fdf89cd6b.png" align="right" width="21%"/>
</div>

## Authors

- [@MohamedRejeb](https://www.github.com/MohamedRejeb): Original networking and database code, Compose UI implementation.

## Original design

Adapted from [Pokédex Apps design](https://dribbble.com/shots/17332968-Pok-dex-Apps-Design-Exploration/) by [Nur Asmara](https://dribbble.com/nurasmara/).
