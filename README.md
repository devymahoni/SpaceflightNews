# Spaceflight News

Spaceflight News is a modular Android application built for the Migros Android Developer case assignment.

## Features

- Latest spaceflight news listing
- Article search
- Article detail screen
- Favorite/unfavorite articles
- Local favorite persistence with Room
- Loading, empty, and error states
- Edge-to-edge friendly Compose UI

## Tech Stack

- Kotlin
- Jetpack Compose
- MVVM + MVI-style UI state
- Clean Architecture
- Multi-module project structure
- Hilt
- Retrofit
- OkHttp
- Kotlinx Serialization
- Room
- Coil
- JUnit

## Modules

- `app`: Application entry point, Hilt setup, activity hosting
- `domain`: Domain models and repository contracts
- `data`: Remote API, local database, repository implementation
- `feature:news`: News list, detail, favorites UI and presentation logic
- `core:designsystem`: Shared Compose theme

## Architecture

The project follows a modular Clean Architecture approach.

`feature:news` depends on `domain` and consumes data through the `NewsRepository` contract.  
`data` implements the repository contract and combines remote articles with local favorite state.  
UI state is represented with immutable state objects and handled through explicit UI events.

## API

The app uses Spaceflight News API v4:

```text
https://api.spaceflightnewsapi.net/v4/
```

## Local Persistence

Favorite articles are stored locally using Room. Remote articles are enriched with favorite state in the data layer before being exposed to the UI.

## Build

```bash
./gradlew :app:assembleDebug
```

## Tests

```bash
./gradlew testDebugUnitTest
```

## Notes

- Article dates are formatted in UTC for deterministic UI and tests.
- Search works for both remote article results and locally stored favorites.
