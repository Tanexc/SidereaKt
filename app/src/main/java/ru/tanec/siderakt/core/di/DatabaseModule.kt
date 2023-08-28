package ru.tanec.siderakt.core.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.tanec.siderakt.data.local.database.MainDatabase
import ru.tanec.siderakt.data.repository.ConstellationRepositoryImpl
import ru.tanec.siderakt.data.repository.PersonalInfoRepositoryImpl
import ru.tanec.siderakt.data.repository.TestRepositoryImpl
import ru.tanec.siderakt.domain.repository.ConstellationRepository
import ru.tanec.siderakt.domain.repository.PersonalInfoRepository
import ru.tanec.siderakt.domain.repository.TestRepository
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