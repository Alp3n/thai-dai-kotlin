package com.example.thaidai.backend.di

import android.content.Context
import com.example.thaidai.Application
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideApplication(@ApplicationContext app: Context) : Application {
        return app as Application
    }

    @Singleton
    @Provides
    fun provideRandomString(): String {
        return "Hey look a random String!"
    }
}