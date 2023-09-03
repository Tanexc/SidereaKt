package ru.tanexc.siderakt.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.tanexc.siderakt.core.util.Theme
import ru.tanexc.siderakt.domain.interfaces.DatabaseEntity
import ru.tanexc.siderakt.domain.model.SettingsData

@Entity(tableName = "personalInformation")
data class PersonalInfoEntity(
    @PrimaryKey
    val id: Int,
    val selectedTheme: Int,
    val learnedConstellations: Int,
    val learnedNorth: Int,
    val learnedSouth: Int,
    val learnedEquatorial: Int,
    val useDarkTheme: Int
) : DatabaseEntity {

    override fun asDomain(): SettingsData = SettingsData(
        learnedConstellations = learnedConstellations,
        learnedNorth = learnedNorth,
        learnedSouth = learnedSouth,
        learnedEquatorial = learnedEquatorial,
        selectedTheme = Theme.getScheme(selectedTheme),
        useDarkTheme = when(useDarkTheme) {
            1 -> true
            0 -> false
            else -> true
        }
    )
}