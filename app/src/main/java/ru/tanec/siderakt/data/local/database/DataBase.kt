package ru.tanec.siderakt.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.tanec.siderakt.data.local.dao.ConstellationDao
import ru.tanec.siderakt.data.local.dao.PersonalInfoDao
import ru.tanec.siderakt.data.local.entity.ConstellationEntity

@Database(
    entities=[ConstellationEntity::class, PersonalInfoDao::class],
    exportSchema=false,
    version=1
)

abstract class Database : RoomDatabase() {
    abstract val personalInfoEntity: PersonalInfoDao
    abstract val constellationDao: ConstellationDao
}