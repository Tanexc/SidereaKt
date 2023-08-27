package ru.tanec.siderakt.data.local.entity

import android.graphics.Bitmap
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.tanec.siderakt.domain.model.Constellation
import ru.tanec.siderakt.domain.interfaces.DatabaseEntity

// hemisphere
// 1 - north
// 2 - south
// 0 - equatorial

@Entity(tableName = "constellations")
data class ConstellationEntity(
    @PrimaryKey
    val id: Long,
    val title: String,
    val info: String,
    val imageURL: String,
    val declination: String,
    val ascent: String,
    val hemisphere: Int,
    val lat: String,
    val alphaStar: String,
    val imageCache: Bitmap?,
    val learned: Boolean
) : DatabaseEntity {
    override fun asDomain(): Constellation = Constellation(
        id,
        title,
        info,
        imageURL,
        declination,
        ascent,
        hemisphere,
        lat,
        alphaStar,
        imageCache,
        learned
    )
}