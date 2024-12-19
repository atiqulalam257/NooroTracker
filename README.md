# Weather Tracker App 🌤️

A simple weather tracking app built with Kotlin and Jetpack Compose. It allows users to search for a city's weather information using a search bar and displays details such as temperature, humidity, UV index, and more.

# Features

#  🌐 City Search: Search for weather information by entering a city name.

#  🌡️ Weather Details: Displays the current temperature, condition, "feels like" temperature, humidity, and UV index.

#  🖼️ Weather Icons: Dynamically fetch weather condition icons from the API.

#  🚀 Modern Architecture: Implements clean architecture with separation of concerns using Domain, Data, and UI layers.

#  🛠️ Dependency Injection: Uses Hilt for dependency management.

#  📢 API Integration: Fetches weather data from the WeatherAPI.

#  Architecture

This app follows Clean Architecture principles:

#  UI Layer:

Handles user interactions and displays weather data.

Built using Jetpack Compose.

#  Domain Layer:

Contains business logic, including the WeatherRepository interface and GetWeatherUseCase.

#  Data Layer:

Responsible for API calls and data mapping.

Includes WeatherApiService and WeatherRepositoryImpl.

#  Project Structure

📂 com.noorotracker
├── 📂 data
│   ├── 📂 mapper        # Maps API responses to domain models
│   ├── 📂 remote        # Retrofit service and API response models
│   └── 📂 repository    # WeatherRepositoryImpl implementation
├── 📂 domain
│   ├── 📂 model         # Domain models
│   ├── 📂 repository    # WeatherRepository interface
│   └── 📂 usecase        # GetWeatherUseCase
├── 📂 ui
│   ├── 📂 home          # Home screen composable
│   └── 📂 search        # Search bar composable
└── 📂 di                # Hilt modules for dependency injection

#  API Integration

The app uses the WeatherAPI for weather data. Follow these steps to set up the API:

Sign up on WeatherAPI and get your API key.

Replace the placeholder YOUR_API_KEY in the WeatherApiService implementation with your API key.

Getting Started

Prerequisites

Android Studio Flamingo or higher.

Minimum SDK version: 21.

Internet connection (to fetch weather data).

Installation

#  Clone this repository:

git clone https://github.com/yourusername/weather-tracker.git

Open the project in Android Studio.

Add your WeatherAPI key in WeatherApiService.

How It Works

Search for a City

The app provides a SearchBar composable where the user can enter a city's name. When the user taps the search icon, the app fetches weather data using the WeatherRepository.

SearchBar(onSearch = { city ->
    viewModel.loadWeather(city)
})

Display Weather Data

The HomeScreen observes weather data from the WeatherViewModel and displays it dynamically.

val weather = viewModel.weather.collectAsState().value

weather?.let {
    Text("City: ${it.location?.name}")
    Text("Temperature: ${it.current?.temp_c}°C")
}

Dependency Injection

The app uses Hilt for dependency injection. The NetworkModule provides dependencies like Retrofit, OkHttpClient, and WeatherRepository.

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideWeatherService(): WeatherService {
        return Retrofit.Builder()
            .baseUrl("https://api.weatherapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherService::class.java)
    }
    @Provides
    fun provideWeatherRepository(apiService: WeatherService): WeatherRepository {
        return WeatherRepositoryImpl(apiService) // Replace with actual implementation
    }

    @Provides
    fun provideGetWeatherUseCase(weatherRepository: WeatherRepositoryImpl): GetWeatherUseCase {
        return GetWeatherUseCase(weatherRepository)
    }

    @Provides
    @Singleton
    fun provideDataStore(@ApplicationContext context: Context): DataStore<Preferences> =
        PreferenceDataStoreFactory.create { context.preferencesDataStoreFile("weather_prefs") }
}

# Libraries Used

Jetpack Compose - For building declarative UI.

Retrofit - For API integration.

Hilt - For dependency injection.

Coroutines - For asynchronous programming.

WeatherAPI - Weather data provider.
