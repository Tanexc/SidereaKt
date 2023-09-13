package ru.tanexc.siderakt.presentation.main.components

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import ru.tanexc.siderakt.core.util.state.DialogState
import ru.tanexc.siderakt.presentation.main.viewModel.MainViewModel
import ru.tanexc.siderakt.presentation.ui.theme.SidereaTheme
import ru.tanexc.siderakt.presentation.utils.widgets.dialogs.ExitDialog

@Composable
fun SidereaApp(viewModel: MainViewModel) {

    val activity: Activity = LocalContext.current as Activity

    SidereaTheme(colorScheme = viewModel.settings.colorScheme) {
        NavHostScreen(
            useOutlineBar = viewModel.settings.isOutlineElements(),
            startDestination = viewModel.currentScreen,
            onScreenChanged = { screen ->
                viewModel.screenChanged(screen)
            },
            colorScheme = viewModel.settings.colorScheme
        )

        when (viewModel.dialogState) {
            is DialogState.Exit -> ExitDialog(
                onDismiss = { viewModel.hideDialog() },
                onConfirm = { activity.finish() }
            )

            else -> {}
        }

        BackHandler(enabled = true) {
            viewModel.showDialog(DialogState.Exit)
        }
    }
}