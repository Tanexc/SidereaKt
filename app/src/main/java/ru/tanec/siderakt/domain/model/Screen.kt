package ru.tanec.siderakt.domain.model


import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.ManageAccounts
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Stars
import androidx.compose.material.icons.filled.Task
import androidx.compose.material.icons.outlined.AutoAwesome
import androidx.compose.material.icons.outlined.List
import androidx.compose.material.icons.outlined.ManageAccounts
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.Stars
import androidx.compose.material.icons.outlined.Task
import androidx.compose.ui.graphics.vector.ImageVector
import ru.tanec.siderakt.R

sealed class Screen(
    val label: Int,
    val iconOutlined: ImageVector,
    val iconFilled: ImageVector
) {

    object Test : Screen(
        label = R.string.test,
        iconOutlined = Icons.Outlined.Task,
        iconFilled = Icons.Filled.Task
    )

    object Catalog : Screen(
        label = R.string.catalog,
        iconOutlined = Icons.Outlined.AutoAwesome,
        iconFilled = Icons.Filled.AutoAwesome
    )

    object Settings : Screen(
        label = R.string.settigs,
        iconOutlined = Icons.Outlined.ManageAccounts,
        iconFilled = Icons.Filled.ManageAccounts
    )

}