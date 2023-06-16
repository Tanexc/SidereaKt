package ru.tanec.siderakt.data.local.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import ru.tanec.siderakt.data.local.entity.ConstellationEntity

@Dao
interface ConstellationDao {
    @Query("SELECT * FROM constellations")
    suspend fun getConstellationList(): List<ConstellationEntity>

    @Query("SELECT * FROM constellations WHERE id = :id")
    suspend fun getConstellationById(id: Long): ConstellationEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun setConstellationList(constellationList: List<ConstellationEntity>)

}