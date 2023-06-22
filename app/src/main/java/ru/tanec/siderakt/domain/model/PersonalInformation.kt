package ru.tanec.siderakt.domain.model

import android.os.Parcel
import android.os.Parcelable
import ru.tanec.siderakt.data.local.entity.PersonalInfoEntity

data class PersonalInformation(
    val learnedConstellations: Int,
    val learnedNorth: Int,
    val learnedSouth: Int,
    val selectedTheme: Int,
    val useDarkTheme: Boolean
) : Model {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readByte() != 0.toByte()
    )

    override fun asDatabaseEntity(): PersonalInfoEntity = PersonalInfoEntity(
        id=0,
        learnedConstellations = learnedConstellations,
        learnedNorth = learnedNorth,
        learnedSouth = learnedSouth,
        selectedTheme = selectedTheme,
        useDarkTheme = useDarkTheme.toInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(learnedConstellations)
        parcel.writeInt(learnedNorth)
        parcel.writeInt(learnedSouth)
        parcel.writeInt(selectedTheme)
        parcel.writeByte(if (useDarkTheme) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PersonalInformation> {
        override fun createFromParcel(parcel: Parcel): PersonalInformation {
            return PersonalInformation(parcel)
        }

        override fun newArray(size: Int): Array<PersonalInformation?> {
            return arrayOfNulls(size)
        }
    }
}

fun Boolean.toInt(): Int {
    return when(this) {
        true -> 1
        else -> 0
    }
}