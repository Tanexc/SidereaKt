package ru.tanexc.siderakt.core.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.tanexc.siderakt.data.local.database.MainDatabase
import ru.tanexc.siderakt.data.repository.ConstellationRepositoryImpl
import ru.tanexc.siderakt.data.repository.PersonalInfoRepositoryImpl
import ru.tanexc.siderakt.data.repository.TestRepositoryImpl
import ru.tanexc.siderakt.domain.repository.ConstellationRepository
import ru.tanexc.siderakt.domain.repository.PersonalInfoRepository
import ru.tanexc.siderakt.domain.repository.TestRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext appContext: Context,
    ): MainDatabase = Room.databaseBuilder(
        context = appContext,
        klass = MainDatabase::class.java,
        name = "SidereaDatabase",
    )   .createFromAsset("database/main.db")
        .build()

    @Provides
    @Singleton
    fun provideConstellationRepository(
        db: MainDatabase
    ): ConstellationRepository = ConstellationRepositoryImpl(db.constellationDao)

    @Provides
    @Singleton
    fun providePersonalInfoRepository(
        db: MainDatabase
    ): PersonalInfoRepository = PersonalInfoRepositoryImpl(db.personalInfoDao)

    @Provides
    @Singleton
    fun provideTestRepository(
        db: MainDatabase
    ): TestRepository = TestRepositoryImpl(db.constellationDao)

}