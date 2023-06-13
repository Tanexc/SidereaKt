package ru.tanec.siderakt.domain.model

interface Model {
    fun asDatabaseEntity(): DatabaseEntity
}