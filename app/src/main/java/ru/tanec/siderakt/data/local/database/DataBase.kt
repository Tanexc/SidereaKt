package ru.tanec.siderakt.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ru.tanec.siderakt.data.utils.BitmapConverter
import ru.tanec.siderakt.data.local.dao.ConstellationDao
import ru.tanec.siderakt.data.local.dao.PersonalInfoDao
import ru.tanec.siderakt.data.local.entity.ConstellationEntity
import ru.tanec.siderakt.data.local.entity.PersonalInfoEntity

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