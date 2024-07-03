# Festival app

## Description

This is a Kotlin Multiplatform project targeting Android, iOS, Web, Desktop.

For now, doesn't work on Desktop and Web because libraries are not available yet.

* `/composeApp` is for code that will be shared across your Compose Multiplatform applications.
  It contains several subfolders:
    - `commonMain` is for code that’s common for all targets.
    - Other folders are for Kotlin code that will be compiled for only the platform indicated in the
      folder name.
      For example, if you want to use Apple’s CoreCrypto for the iOS part of your Kotlin app,
      `iosMain` would be the right folder for such calls.

* `/iosApp` contains iOS applications. Even if you’re sharing your UI with Compose Multiplatform,
  you need this entry point for your iOS app. This is also where you should add SwiftUI code for
  your project.

Learn more
about [Kotlin Multiplatform](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html),
[Compose Multiplatform](https://github.com/JetBrains/compose-multiplatform/#compose-multiplatform),
[Kotlin/Wasm](https://kotl.in/wasm/)…

## Goal

A app to show the show list for a festival and set a remainder to do not miss.

Also, is for explore Kotlin Multiplatform with Compose Multiplatform.

## Features

- Show a list of DJ shows
- Get list from remote and cached on local database
- Set alarm for a show
    - Handled permissions
  - Save on local database
  - Show alarm icon on show list

TODO:

- Show notification when alarm is triggered
- Add a home card to promote @afterjune track
- Replace top app bar for bottom bar
- Add info screen
    - Add info about how create and repo
    - Disclaimer about not relation with festival
    - Button for donations
- Create icon and apply on app
- Improve architecture
- Improve use of viewmodel and types (sealed class and state flow)
- Apply _beautiful_ design
