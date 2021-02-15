package com.example.thaidai.backend.di

import com.example.thaidai.backend.network.ItemDtoMapper
import com.example.thaidai.backend.network.ItemService
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideItemMapper(): ItemDtoMapper {
        return ItemDtoMapper()
    }

    @Singleton
    @Provides
    fun provideItemService(): ItemService {
        return Retrofit.Builder()
            .baseUrl("https://6029d8bb6c995100176edb69.mockapi.io/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(ItemService::class.java)
    }


}