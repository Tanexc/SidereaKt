package ru.tanec.siderakt.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.tanec.siderakt.data.local.dao.ConstellationDao
import ru.tanec.siderakt.data.local.dao.ThemeDao
import ru.tanec.siderakt.data.local.entity.ConstellationEntity
import ru.tanec.siderakt.data.local.entity.ThemeEntity

@Database(
    entities=[ConstellationEntity::class, ThemeEntity::class],
    exportSchema=false,
    version=1
)

abstract class Database : RoomDatabase() {
    abstract val theme: ThemeDao
    abstract val constellationDao: ConstellationDao
}