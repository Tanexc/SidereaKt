package ru.tanec.siderakt.domain.model

import ru.tanec.siderakt.common.Scheme
import ru.tanec.siderakt.data.local.entity.ThemeEntity

data class Theme(
    val id: Scheme,
) : Model {
    override fun asDataBaseEntity(): ThemeEntity = ThemeEntity(
        0,
        id.id
    )
}