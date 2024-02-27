package com.example.barook_android_assignment.di

import android.content.Context
import com.example.barook_android_assignment.BaseApplication
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideApplication(@ApplicationContext app: Context) =
        app as BaseApplication


    @ActivityScoped
    @Provides
    fun provideActivity(@ActivityContext activityContext: Context) = activityContext

}