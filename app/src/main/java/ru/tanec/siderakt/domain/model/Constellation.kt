package ru.tanec.siderakt.domain.model

import android.graphics.Bitmap
import android.os.Parcel
import android.os.Parcelable
import androidx.compose.ui.graphics.vector.ImageVector
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

    val icon: ImageVector? = null

    constructor(parcel: Parcel) : this(
        parcel.readLong(),
        parcel.readString()?:"",
        parcel.readString()?:"",
        parcel.readString()?:"",
        parcel.readString()?:"",
        parcel.readString()?:"",
        parcel.readInt(),
        parcel.readString()?:"",
        parcel.readString()?:"",
        parcel.readParcelable(Bitmap::class.java.classLoader)
    )
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
        imageCache
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(id)
        parcel.writeString(title)
        parcel.writeString(info)
        parcel.writeString(imageURL)
        parcel.writeString(declination)
        parcel.writeString(ascent)
        parcel.writeInt(hemisphere)
        parcel.writeString(lat)
        parcel.writeString(alphaStar)
        parcel.writeParcelable(imageCache, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Constellation> {
        override fun createFromParcel(parcel: Parcel): Constellation {
            return Constellation(parcel)
        }

        override fun newArray(size: Int): Array<Constellation?> {
            return arrayOfNulls(size)
        }
    }


}
