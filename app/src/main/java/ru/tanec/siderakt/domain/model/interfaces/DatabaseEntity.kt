package ru.tanec.siderakt.domain.model.interfaces

interface DatabaseEntity {
    fun asDomain(): Model
}