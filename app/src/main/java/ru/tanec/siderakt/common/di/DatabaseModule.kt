package ru.tanec.siderakt.common.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.tanec.siderakt.data.local.database.Database
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext appContext: Context,
    ): Database = Room.databaseBuilder(
        context = appContext,
        klass = Database::class.java,
        name = "SidereaDatabase"
    )
        .build()

}