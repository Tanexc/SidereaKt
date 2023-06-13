package ru.tanec.siderakt.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.tanec.siderakt.domain.model.DatabaseEntity
import ru.tanec.siderakt.domain.model.PersonalInformation

@Entity(tableName = "personalInformation")
data class PersonalInfoEntity(
    @PrimaryKey
    val id: Int,
    val selectedTheme: Int,
    val learnedConstellations: Int,
    val learnedNorth: Int,
    val learnedSouth: Int,
    val useDarkTheme: Int
) : DatabaseEntity {

    override fun asDomain(): PersonalInformation = PersonalInformation(
        learnedConstellations = learnedConstellations,
        learnedNorth = learnedNorth,
        learnedSouth = learnedSouth,
        selectedTheme = selectedTheme,
        useDarkTheme = when(useDarkTheme) {
            1 -> true
            0 -> false
            else -> true
        }
    )
}