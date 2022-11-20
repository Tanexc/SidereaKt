package ru.tanec.siderakt.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.tanec.siderakt.common.Scheme
import ru.tanec.siderakt.domain.model.DatabaseEntity
import ru.tanec.siderakt.domain.model.Theme

@Entity
data class ThemeEntity(
    @PrimaryKey
    val selected: Int,
    val id: Int,
) : DatabaseEntity {
    override fun asDomain(): Theme {
        return when (id) {
            1 -> Theme(id = Scheme.Dark())
            2 -> Theme(id = Scheme.Light())
            3 -> Theme(id = Scheme.Blue())
            else -> Theme(id = Scheme.System())
        }
    }
}