package ru.tanexc.siderakt.data.local.dao

import androidx.room.*
import ru.tanexc.siderakt.data.local.entity.ConstellationEntity

@Dao
interface ConstellationDao {
    @Query("SELECT * FROM constellations")
    suspend fun getConstellationList(): List<ConstellationEntity>

    @Query("SELECT * FROM constellations WHERE id = :id")
    suspend fun getConstellationById(id: Long): ConstellationEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun editConstellation(constellation: ConstellationEntity)

}