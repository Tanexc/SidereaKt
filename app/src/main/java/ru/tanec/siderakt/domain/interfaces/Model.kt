package ru.tanec.siderakt.domain.interfaces

interface Model {
    fun asDatabaseEntity(): DatabaseEntity
}