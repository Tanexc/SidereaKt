package ru.tanexc.siderakt.core.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.tanexc.siderakt.presentation.utils.SettingsControllerImpl
import ru.tanexc.siderakt.domain.interfaces.SettingsController
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SettingsControllerModule {

    @Provides
    @Singleton
    fun provideSettingsController(): SettingsController = SettingsControllerImpl()
}