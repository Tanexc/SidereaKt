package ru.tanec.siderakt.common.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.tanec.siderakt.data.local.database.Database
import ru.tanec.siderakt.data.repository.ConstellationRepositoryImpl
import ru.tanec.siderakt.data.repository.PersonalInfoRepositoryImpl
import ru.tanec.siderakt.domain.repository.ConstellationRepository
import ru.tanec.siderakt.domain.repository.PersonalInfoRepository
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
        name = "SidereaDatabase",
    )   .createFromAsset("database/main.db")
        .build()

    @Provides
    @Singleton
    fun provideConstellationRepository(
        db: Database
    ): ConstellationRepository = ConstellationRepositoryImpl(db.constellationDao)

    @Provides
    @Singleton
    fun providePersonalInfoRepository(
        db: Database
    ): PersonalInfoRepository = PersonalInfoRepositoryImpl(db.personalInfoDao)


}