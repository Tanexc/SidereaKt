package ru.tanec.siderakt.domain.model

import ru.tanec.siderakt.data.local.dao.PersonalInfoDao
import ru.tanec.siderakt.data.local.entity.PersonalInfoEntity

data class PersonalInformation(
    val learnedConstellations: Int,
    val learnedNorth: Int,
    val learnedSouth: Int,
    val selectedTheme: Int,
    val useDarkTheme: Boolean
) : Model {
    override fun asDataBaseEntity(): PersonalInfoEntity = PersonalInfoEntity(
        learnedConstellations = learnedConstellations,
        learnedNorth = learnedNorth,
        learnedSouth = learnedSouth,
        selectedTheme = selectedTheme,
        useDarkTheme = useDarkTheme
    )
}