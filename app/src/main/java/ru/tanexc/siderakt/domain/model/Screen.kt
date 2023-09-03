package ru.tanexc.siderakt.domain.model


import android.os.Parcelable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.ManageAccounts
import androidx.compose.material.icons.filled.Task
import androidx.compose.material.icons.outlined.AutoAwesome
import androidx.compose.material.icons.outlined.ManageAccounts
import androidx.compose.material.icons.outlined.Task
import androidx.compose.ui.graphics.vector.ImageVector
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize
import ru.tanexc.siderakt.R

@Parcelize
sealed class Screen(
    val label: Int,
    @IgnoredOnParcel
    val iconOutlined: ImageVector,
    @IgnoredOnParcel
    val iconFilled: ImageVector
): Parcelable {

    object Test : Screen(
        label = R.string.test,
        iconOutlined = Icons.Outlined.Task,
        iconFilled = Icons.Filled.Task
    ), Parcelable

    object Catalog : Screen(
        label = R.string.catalog,
        iconOutlined = Icons.Outlined.AutoAwesome,
        iconFilled = Icons.Filled.AutoAwesome
    ), Parcelable

    object Profile : Screen(
        label = R.string.profile,
        iconOutlined = Icons.Outlined.ManageAccounts,
        iconFilled = Icons.Filled.ManageAccounts
    )

    object Constellation : Screen(
        label = R.string.catalog,
        iconOutlined = Icons.Outlined.AutoAwesome,
        iconFilled = Icons.Filled.AutoAwesome
    )
}