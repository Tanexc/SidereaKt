package ru.tanec.siderakt.domain.model

import android.graphics.Bitmap
import ru.tanec.siderakt.data.local.entity.ConstellationEntity

data class Constellation(
    val id: Long,
    val title: String,
    val info: String,
    val imageURL: String,
    val declination: String,
    val ascent: String,
    val hemisphere: Int,
    val lat: String,
    val alphaStar: String,
    val imageCache: Bitmap?
): Model {
    override fun asDataBaseEntity(): ConstellationEntity = ConstellationEntity(
        id,
        title,
        info,
        imageURL,
        declination,
        ascent,
        hemisphere,
        lat,
        alphaStar,
        imageCache
    )

}
