package ru.tanec.siderakt.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.tanec.siderakt.core.util.Theme
import ru.tanec.siderakt.domain.model.SettingsData
import ru.tanec.siderakt.domain.model.interfaces.DatabaseEntity

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

    override fun asDomain(): SettingsData = SettingsData(
        learnedConstellations = learnedConstellations,
        learnedNorth = learnedNorth,
        learnedSouth = learnedSouth,
        selectedTheme = Theme.getScheme(selectedTheme),
        useDarkTheme = when(useDarkTheme) {
            1 -> true
            0 -> false
            else -> true
        }
    )
}