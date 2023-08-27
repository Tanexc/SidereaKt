package ru.tanec.siderakt.domain.interfaces

interface DatabaseEntity {
    fun asDomain(): Model
}