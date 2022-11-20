package ru.tanec.siderakt.data.local.dao

import androidx.room.*
import ru.tanec.siderakt.data.local.entity.ThemeEntity

@Dao
interface ThemeDao {
    @Query("SELECT * FROM themeentity")
    fun getTheme(): ThemeEntity

    @Insert(onConflict=OnConflictStrategy.REPLACE)
    suspend fun setTheme(theme: ThemeEntity)
}