package ru.tanec.siderakt.presentation.utils.widgets.dialogs

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ExitToApp
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import ru.tanec.siderakt.R

@Composable
fun TestInfoDialog(
    onConfirm: () -> Unit
) {
    AlertDialog(
        onDismissRequest = { onConfirm() },
        title = {
            Text(stringResource(R.string.advices), textAlign = TextAlign.Center)
        },
        text = { Text(stringResource(R.string.advices_swap)) },
        icon = { Icon(Icons.Outlined.Info, null) },
        confirmButton = {
            Text(
                stringResource(R.string.ok),
                modifier = Modifier
                    .clickable { onConfirm() })
        },
        shape = RoundedCornerShape(28.dp),
        modifier = Modifier
            .defaultMinSize(minWidth = 280.dp)
    )
}