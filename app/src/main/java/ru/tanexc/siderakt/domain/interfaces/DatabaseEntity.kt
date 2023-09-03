package ru.tanexc.siderakt.domain.interfaces

interface DatabaseEntity {
    fun asDomain(): Model
}