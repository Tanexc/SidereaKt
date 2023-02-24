package ru.tanec.siderakt.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.tanec.siderakt.common.Scheme
import ru.tanec.siderakt.domain.model.DatabaseEntity
import ru.tanec.siderakt.domain.model.Model
import ru.tanec.siderakt.domain.model.PersonalInformation

@Entity(tableName = "personalInformation")
data class PersonalInfoEntity(
    @PrimaryKey
    val selectedTheme: Int,
    val learnedConstellations: Int,
    val learnedNorth: Int,
    val learnedSouth: Int,
    val useDarkTheme: Boolean
) : DatabaseEntity {

    override fun asDomain(): PersonalInformation = PersonalInformation(
        learnedConstellations = learnedConstellations,
        learnedNorth = learnedNorth,
        learnedSouth = learnedSouth,
        selectedTheme = selectedTheme,
        useDarkTheme = useDarkTheme
    )
}