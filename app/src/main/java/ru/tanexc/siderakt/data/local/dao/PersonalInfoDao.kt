package ru.tanexc.siderakt.data.local.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import ru.tanexc.siderakt.data.local.entity.PersonalInfoEntity

@Dao
interface PersonalInfoDao {
    @Query("SELECT * FROM personalInformation")
    fun getInfo(): Flow<PersonalInfoEntity>

    @Insert(onConflict=OnConflictStrategy.REPLACE)
    suspend fun setInfo(info: PersonalInfoEntity)
}