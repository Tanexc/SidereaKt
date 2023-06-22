package ru.tanec.siderakt.core.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.tanec.siderakt.domain.model.interfaces.SettingsController
import ru.tanec.siderakt.presentation.utils.SettingsControllerImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SettingsControllerModule {

    @Provides
    @Singleton
    fun provideSettingsController(): SettingsController = SettingsControllerImpl()
}