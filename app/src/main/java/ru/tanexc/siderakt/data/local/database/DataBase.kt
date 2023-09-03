package ru.tanexc.siderakt.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ru.tanexc.siderakt.data.local.dao.ConstellationDao
import ru.tanexc.siderakt.data.local.dao.PersonalInfoDao
import ru.tanexc.siderakt.data.local.entity.ConstellationEntity
import ru.tanexc.siderakt.data.local.entity.PersonalInfoEntity
import ru.tanexc.siderakt.data.utils.BitmapConverter

@Database(
    entities=[ConstellationEntity::class, PersonalInfoEntity::class],
    exportSchema=false,
    version=1
)
@TypeConverters(
    BitmapConverter::class
)
abstract class MainDatabase : RoomDatabase() {
    abstract val personalInfoDao: PersonalInfoDao
    abstract val constellationDao: ConstellationDao
}