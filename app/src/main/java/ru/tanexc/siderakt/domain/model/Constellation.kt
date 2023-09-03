package ru.tanexc.siderakt.domain.model

import android.graphics.Bitmap
import androidx.compose.ui.graphics.vector.ImageVector
import ru.tanexc.siderakt.data.local.entity.ConstellationEntity
import ru.tanexc.siderakt.domain.interfaces.Model

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
    val imageCache: Bitmap?,
    val learned: Boolean
): Model {

    val icon: ImageVector? = null

    override fun asDatabaseEntity(): ConstellationEntity = ConstellationEntity(
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
