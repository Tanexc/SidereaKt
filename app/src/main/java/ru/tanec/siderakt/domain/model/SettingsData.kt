package ru.tanec.siderakt.domain.model

import ru.tanec.siderakt.core.util.Theme
import ru.tanec.siderakt.data.local.entity.PersonalInfoEntity
import ru.tanec.siderakt.domain.interfaces.Model

data class SettingsData(
    val learnedConstellations: Int,
    val learnedNorth: Int,
    val learnedSouth: Int,
    val selectedTheme: Theme,
    val useDarkTheme: Boolean
) : Model {


    override fun asDatabaseEntity(): PersonalInfoEntity = PersonalInfoEntity(
        id=0,
        learnedConstellations = learnedConstellations,
        learnedNorth = learnedNorth,
        learnedSouth = learnedSouth,
        selectedTheme = selectedTheme.id,
        useDarkTheme = useDarkTheme.toInt()
    )

    fun Boolean.toInt(): Int {
        return when(this) {
            true -> 1
            else -> 0
        }
    }
}
