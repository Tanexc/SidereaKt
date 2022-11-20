package ru.tanec.siderakt.data.local.entity

import android.graphics.Bitmap
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.tanec.siderakt.domain.model.Constellation
import ru.tanec.siderakt.domain.model.DatabaseEntity

@Entity
data class ConstellationEntity(
    @PrimaryKey
    val id: Long,
    val title: String,
    val info: String,
    val image: String,
    val declination: String,
    val ascent: String,
    val polusharie: Int,
    val lat: String,
    val alpha: String,
    val bitmap: Bitmap?
): DatabaseEntity {
    override fun asDomain(): Constellation = Constellation(
            id,
            title,
            info,
            image,
            declination,
            ascent,
            polusharie,
            lat,
            alpha,
            bitmap
        )
}