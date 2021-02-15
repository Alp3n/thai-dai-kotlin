package com.example.thaidai.backend.di

import com.example.thaidai.backend.network.ItemDtoMapper
import com.example.thaidai.backend.network.ItemService
import com.example.thaidai.backend.repository.ItemRepository
import com.example.thaidai.backend.repository.ItemRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideItemRepository(
        itemService: ItemService,
        itemDtoMapper: ItemDtoMapper
    ): ItemRepository {
        return ItemRepositoryImpl(itemService, itemDtoMapper)
    }
}