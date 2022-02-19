# AndroidPracticalTask

https://user-images.githubusercontent.com/13369510/154775387-bf47f08b-a7c0-4a0c-bd9f-7a8f1b68545e.mp4

## App Overview
Android Practical is a small demo application based on modern Android tech-stacks especially focus on Jetpack Compose UI using API.
Also fetching data from the network and integrating persisted data in the database via repository patterns. It demonstrating the MVVM pattern using SOLID principle and seperation of concern.
</br>
## App Functionalities
User can explore clothing products. User can add those products as a favourites.

## Configuration details
Sdk versions: minSdk 23, compileSdk 32</br>
Studio version: Android Studio Dolphin | 2021.3.1 Canary 2</br>
Gradle version: 7.3.0-alpha02</br>
Kotlin version: 1.6.10</br>

## Tech stack & Open-source libraries
- [Kotlin](https://kotlinlang.org/) based + [Coroutines](https://github.com/Kotlin/kotlinx.coroutines) + [Flow](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/) for asynchronous.
- Hilt for dependency injection.
- JetPack
  - Compose - A modern toolkit for building native Android UI.
  - Lifecycle - dispose observing data when lifecycle state changes.
  - ViewModel - UI related data holder, lifecycle aware.
  - Room Persistence - construct database.
- Architecture
  - MVVM Architecture (Declarative View - ViewModel - Model)
  - Repository pattern
- [Landscapist](https://github.com/skydoves/landscapist) - Jetpack Compose image loading library with shimmer & circular reveal animations.
- [Retrofit2 & OkHttp3](https://github.com/square/retrofit) - construct the REST APIs and  network data.
- [Sandwich](https://github.com/skydoves/Sandwich) - construct simpler API response and handling error responses.
- [WhatIf](https://github.com/skydoves/whatif) - checking nullable objects.

