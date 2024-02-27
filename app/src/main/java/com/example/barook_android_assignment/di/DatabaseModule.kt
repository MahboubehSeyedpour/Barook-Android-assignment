package com.example.barook_android_assignment.di

import androidx.room.Room
import com.example.barook_android_assignment.BaseApplication
import com.example.barook_android_assignment.data.db.BarookDatabase
import com.example.barook_android_assignment.data.db.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(context: BaseApplication) =
        Room.databaseBuilder(
            context.applicationContext,
            BarookDatabase::class.java,
            DATABASE_NAME
        ).build()
}