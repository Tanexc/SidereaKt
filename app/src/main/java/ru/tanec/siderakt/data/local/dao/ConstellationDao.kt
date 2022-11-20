package ru.tanec.siderakt.data.local.dao

import androidx.room.*
import ru.tanec.siderakt.data.local.entity.ConstellationEntity

interface ConstellationDao {


    @Query("SELECT * FROM constellationentity")
    fun getConstellationList(): List<ConstellationEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun setConstellationList(constellationList: List<ConstellationEntity>)

}