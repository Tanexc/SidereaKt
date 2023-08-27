package ru.tanec.siderakt.domain.model.interfaces

interface Model {
    fun asDatabaseEntity(): DatabaseEntity
}