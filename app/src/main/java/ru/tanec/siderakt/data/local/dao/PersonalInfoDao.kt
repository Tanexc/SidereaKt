package ru.tanec.siderakt.data.local.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import ru.tanec.siderakt.data.local.entity.PersonalInfoEntity

@Dao
interface PersonalInfoDao {
    @Query("SELECT * FROM personalInformation")
    fun getInfo(): Flow<PersonalInfoEntity>

    @Insert(onConflict=OnConflictStrategy.REPLACE)
    suspend fun setInfo(info: PersonalInfoEntity)
}