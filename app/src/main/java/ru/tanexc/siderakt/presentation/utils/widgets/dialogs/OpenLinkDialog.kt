package ru.tanexc.siderakt.presentation.utils.widgets.dialogs

import android.app.Activity
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Link
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import ru.tanexc.siderakt.R

@Composable
fun OpenLinkDialog(
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {

    val activity = LocalContext.current as Activity

    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = {
            Text(stringResource(R.string.link), textAlign = TextAlign.Center)
        },
        text = { Text(stringResource(R.string.open_link)) },
        icon = { Icon(Icons.Outlined.Link, null) },
        confirmButton = {
            TextButton(onClick = { onConfirm() }) { Text(stringResource(R.string.yes)) }
        },
        dismissButton = {
            TextButton(onClick = { onDismiss() }) { Text(stringResource(R.string.dismiss)) }
        },
        shape = RoundedCornerShape(28.dp),
        modifier = Modifier
            .defaultMinSize(minWidth = 280.dp)
    )
}