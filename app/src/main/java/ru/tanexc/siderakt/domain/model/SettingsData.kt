package ru.tanexc.siderakt.domain.model

import ru.tanexc.siderakt.core.util.Theme
import ru.tanexc.siderakt.data.local.entity.PersonalInfoEntity
import ru.tanexc.siderakt.domain.interfaces.Model

data class SettingsData(
    val learnedConstellations: Int,
    val learnedNorth: Int,
    val learnedSouth: Int,
    val learnedEquatorial: Int,
    val selectedTheme: Theme,
    val useDarkTheme: Boolean,
    val outlineElements: Boolean
) : Model {


    override fun asDatabaseEntity(): PersonalInfoEntity = PersonalInfoEntity(
        id=0,
        learnedConstellations = learnedConstellations,
        learnedNorth = learnedNorth,
        learnedSouth = learnedSouth,
        learnedEquatorial = learnedEquatorial,
        selectedTheme = selectedTheme.id,
        useDarkTheme = useDarkTheme.toInt(),
        outlineElements = outlineElements.toInt()
    )

    fun Boolean.toInt(): Int {
        return when(this) {
            true -> 1
            else -> 0
        }
    }
}
