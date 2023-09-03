package ru.tanexc.siderakt.presentation.utils.widgets.dialogs

import android.app.Activity
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Link
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
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