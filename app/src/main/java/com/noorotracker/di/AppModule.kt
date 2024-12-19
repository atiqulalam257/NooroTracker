package com.noorotracker.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.noorotracker.data.api.WeatherService
import com.noorotracker.data.repository.WeatherRepositoryImpl
import com.noorotracker.domain.repository.WeatherRepository
import com.noorotracker.domain.usecase.GetWeatherUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

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
