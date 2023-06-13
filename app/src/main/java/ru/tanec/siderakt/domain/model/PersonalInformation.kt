package ru.tanec.siderakt.domain.model

import ru.tanec.siderakt.data.local.entity.PersonalInfoEntity

data class PersonalInformation(
    val learnedConstellations: Int,
    val learnedNorth: Int,
    val learnedSouth: Int,
    val selectedTheme: Int,
    val useDarkTheme: Boolean
) : Model {
    override fun asDatabaseEntity(): PersonalInfoEntity = PersonalInfoEntity(
        id=0,
        learnedConstellations = learnedConstellations,
        learnedNorth = learnedNorth,
        learnedSouth = learnedSouth,
        selectedTheme = selectedTheme,
        useDarkTheme = useDarkTheme.toInt()
    )
}

fun Boolean.toInt(): Int {
    return when(this) {
        true -> 1
        else -> 0
    }
}