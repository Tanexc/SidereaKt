package ru.tanexc.siderakt.domain.interfaces

interface Model {
    fun asDatabaseEntity(): DatabaseEntity
}