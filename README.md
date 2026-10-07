# Rick & Morty Android App

A native Android application built as part of an Android Developer technical challenge.

The app uses the [Rick and Morty API](https://rickandmortyapi.com/) to browse characters, inspect their details and explore the episodes in which they appear.

The main goal of the project was to build a clean and maintainable Android application using modern Android development practices, while keeping the implementation simple enough for the scope of the challenge.

## Features

- Browse Rick and Morty characters
- Paginated character loading
- Filter characters
- View character details
- View the episodes in which a character appears
- Shared element transitions between character list and detail

## Architecture

The project follows a **layered architecture** combined with **MVVM** in the presentation layer.

The codebase is organised using a **feature-first** approach. Each feature contains the code related to that functionality instead of grouping the whole application by technical layer.

The main layers are:

- **Presentation** — Compose UI, ViewModels and UI state.
- **Domain** — Business models, repository contracts and use cases.
- **Data** — API models, mappers and repository implementations.

### Presentation

The UI is implemented using **Jetpack Compose**.

ViewModels expose immutable UI state using `StateFlow`, while Composables observe that state and render the corresponding UI.

This keeps the UI declarative and avoids putting business or data-access logic directly inside Composables.

### Domain

The domain layer contains the models and use cases used by the application.

For example, `GetCharactersUseCase` is responsible for retrieving characters without the presentation layer needing to know how or where the data is obtained.

Repository interfaces are also defined in this layer, keeping the domain independent from the concrete data sources.

### Data

The data layer contains the implementation details related to external data sources.

The Rick and Morty API is accessed through Retrofit, with Kotlin Serialization used to deserialize the API responses.

DTOs are mapped into domain models before being exposed to the rest of the application. This prevents API-specific models from leaking into the domain or presentation layers.

### Dependency Injection

**Hilt** is used to provide dependencies across the application.

Network components, repositories and other dependencies are provided through dedicated Hilt modules, keeping object creation outside the classes that consume those dependencies.

## Tech Stack

| Technology | Usage |
| --- | --- |
| Kotlin | Main programming language |
| Jetpack Compose | UI |
| Material 3 | UI components and theming |
| Kotlin Flow | Reactive state handling |
| Kotlin Coroutines	| Asynchronous operations |
| Retrofit | REST API client |
| Kotlin Serialization | JSON serialization/deserialization |
| Hilt | Dependency injection |
| Coil | Image loading |
| JUnit | Unit testing |
| MockK | Test mocking |

## Technical Decisions

### In-memory character cache

One of the decisions made during the implementation was how to cache the characters retrieved from the API.

The character cache is kept as an in-memory variable inside the repository. The cache lives for as long as the application process is alive, so it is lost when the process is killed and needs to be rebuilt the next time the application starts.

I considered using Room to make this cache persistent, but decided that it would add unnecessary complexity for the scope and nature of this application.

The app does not need offline support and the amount of data being cached is relatively small. Because of this, an in-memory cache provides the benefits needed here without introducing a database and the additional persistence layer that would come with it.

This also leaves the repository as the single place responsible for managing the cached data.

### Handling API rate limiting

During development, one of the main issues encountered was the API returning **HTTP 429 Too Many Requests** when a high number of requests were made within a short period of time.

This was particularly noticeable while browsing the character list, where a large number of requests can be triggered within a short period of time.

The API provides a `Retry-After` header with the number of seconds that should be waited before making another request.

Instead of treating a `429` as a regular error, the project implements a retry mechanism around requests that can be rate limited.

The `executeRetryAfter` method handles this behaviour:

1. Execute the request normally.
2. If the request succeeds, return the result.
3. If the response is not a `429`, propagate the error normally.
4. If the response is a `429`, read the `Retry-After` header.
5. Wait for the specified amount of time plus one additional second.
6. Retry the request.
7. Repeat the process up to three additional times.
8. If all attempts fail, return the error to the caller.

The extra second is intentional. During development, there were cases where waiting exactly the amount of time specified by `Retry-After` was not enough and the next request still returned `429`. Adding a small safety margin made the retry mechanism more reliable.

The retry logic is kept outside the UI so that rate-limit handling remains a networking/data concern rather than something the presentation layer needs to know about.

### Image loading failures

Images are loaded independently from the character data.

If an image cannot be loaded because of a temporary API or rate-limit issue, the UI does not fail completely. Instead, a placeholder is displayed indicating that the image could not be loaded.

Character data can still be displayed and the list continues to load as soon as the API becomes available again.

This means that a failure in an individual image request does not prevent the user from interacting with the rest of the application.

## Testing

The project includes unit tests for parts of the domain and presentation layers.

For example, mapper tests verify that API DTOs are correctly converted into domain models, while ViewModel tests verify state changes when loading characters.

The intention was to test the behaviour of the application rather than implementation details, keeping the tests focused on the responsibilities of each component.

## Future Improvements

The current implementation intentionally focuses on the requirements of the challenge, but there are several natural extensions that could be added.

### Origin and location

The character detail could be extended to display the character's **origin** and **current location**, following a similar approach to the existing episodes section.

### Navigable episodes and locations

Episodes and locations could also become clickable.

An episode detail screen could display all the characters appearing in that episode, while a location detail screen could display the characters currently associated with that location.

The existing `CharacterCarousel` was designed as a reusable component, so it could be reused to display these character lists.

This would allow the application to evolve into a more interconnected navigation flow:

```text
Character
   ↓
Episode
   ↓
Character
   ↓
Location
   ↓
Character
   ↓
...
```

The same repository-level caching approach could be extended to episodes and locations. Once data has already been retrieved, it could be reused from memory instead of requesting it again from the API.

This would reduce unnecessary network requests while also making navigation between related entities faster.

### Favourite characters

Another relatively small addition would be the ability to mark characters as favourites.

A favourite button could be added to the character detail screen, together with a filter to display only saved characters.

For this use case, a small persistent collection of character IDs would be enough. Rather than storing complete character objects, only the IDs would need to be persisted.

A lightweight key-value solution such as **DataStore Preferences** would be a good fit here. It avoids introducing a database for a very small piece of persistent state, while still keeping favourites available after the application process is restarted.

When the favourites filter is selected, the repository could use the stored IDs to retrieve the corresponding characters from the API, using the in-memory cache whenever the required data is already available and only requesting the missing characters when necessary.

## API

The application uses the public **Rick and Morty API**:

https://rickandmortyapi.com/

## Conclusion

This project was built with a focus on keeping the architecture simple, testable and easy to extend while using modern Android development practices.

Some implementation decisions, such as the in-memory cache and the custom handling of API rate limiting, were made specifically around the behaviour of the API and the requirements of the application rather than introducing additional infrastructure that was not necessary for the current scope.

The current structure also leaves room to extend the application with episodes, locations, favourites and more interconnected navigation without having to significantly change the existing architecture.
