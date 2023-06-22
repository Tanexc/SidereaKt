package ru.tanec.siderakt.domain.model

import android.os.Parcelable

interface Model: Parcelable {
    fun asDatabaseEntity(): DatabaseEntity
}