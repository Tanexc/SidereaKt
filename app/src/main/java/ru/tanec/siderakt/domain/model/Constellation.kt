package ru.tanec.siderakt.domain.model

import android.graphics.Bitmap
import ru.tanec.siderakt.data.local.entity.ConstellationEntity

data class Constellation(
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
): Model {
    override fun asDataBaseEntity(): ConstellationEntity = ConstellationEntity(
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
