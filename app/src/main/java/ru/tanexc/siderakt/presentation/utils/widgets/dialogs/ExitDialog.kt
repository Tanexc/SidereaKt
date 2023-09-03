package ru.tanexc.siderakt.presentation.utils.widgets.dialogs


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ExitToApp
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import ru.tanexc.siderakt.R


@Composable
fun ExitDialog(
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = {
            Text(stringResource(R.string.exit), textAlign = TextAlign.Center)
        },
        text = { Text(stringResource(R.string.exit_question)) },
        icon = { Icon(Icons.Outlined.ExitToApp, null) },
        confirmButton = {
            Text(
                stringResource(R.string.yes),
                modifier = Modifier
                    .clickable { onConfirm() })
        },
        dismissButton = {
            Text(
                stringResource(R.string.dismiss),
                modifier = Modifier
                    .padding(end = 8.dp)
                    .clickable { onDismiss() }
            )
        },
        shape = RoundedCornerShape(28.dp),
        modifier = Modifier
            .defaultMinSize(minWidth = 280.dp)
    )
}