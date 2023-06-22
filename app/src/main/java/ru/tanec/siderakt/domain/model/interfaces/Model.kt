package ru.tanec.siderakt.domain.model.interfaces

import android.os.Parcelable

interface Model {
    fun asDatabaseEntity(): DatabaseEntity
}