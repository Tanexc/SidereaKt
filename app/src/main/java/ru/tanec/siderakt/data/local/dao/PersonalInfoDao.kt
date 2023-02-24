package ru.tanec.siderakt.data.local.dao

import androidx.room.*
import ru.tanec.siderakt.data.local.entity.PersonalInfoEntity

@Dao
interface PersonalInfoDao {
    @Query("SELECT * FROM personalInformation")
    fun getInfo(): PersonalInfoEntity

    @Insert(onConflict=OnConflictStrategy.REPLACE)
    suspend fun setInfo(info: PersonalInfoEntity)
}