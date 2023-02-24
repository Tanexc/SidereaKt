package ru.tanec.siderakt.domain.model

interface DatabaseEntity {
    fun asDomain(): Model
}