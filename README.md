## Description
Basic application demonstrating the ability to download content from a Rest Service and displaying that content to the user. This application upon launch will allow the user to type a query string, then perform a search and display that content to the user.

## Architecture
- Modularization - code are separate into single functionality modules
- Clean Architecture -  Layered separation of functionality and a strict dependency direction. Helping in preventing complicated dependency tree and circular dependencies

#### Modules:
Internal modules are centered around core functionalities including network calls and data access
- internal/repo - This module allows access to the data, including the de-serialization of data from the remote api. This module represents the network layer and repository layer for the applications

UI/Feature modules contain all the ui components, including view models, views, fragments and activities
- feature/album - ui features associated with albums. This includes the detail view for albums
- feature/search - Search functionality. This module contains all the component and ui code for getting the user input and displaying the search result
- app - Top level application. This module contains the initialization of the dependency graph

## Third Party Libraries
- Expoxy - UI library which help when building reusable models - https://github.com/airbnb/epoxy
- Glide - Image loading and caching library - https://bumptech.github.io/glide/
- Retrofit/OkHttp - Library for making network call and support various authentication methods - https://square.github.io/retrofit/
