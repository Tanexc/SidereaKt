package ru.tanexc.siderakt.presentation.main.components

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import ru.tanexc.siderakt.R
import ru.tanexc.siderakt.core.util.state.DialogState
import ru.tanexc.siderakt.domain.model.Screen
import ru.tanexc.siderakt.presentation.main.viewModel.MainViewModel
import ru.tanexc.siderakt.presentation.ui.theme.SidereaTheme
import ru.tanexc.siderakt.presentation.ui.theme.Typography
import ru.tanexc.siderakt.presentation.utils.widgets.dialogs.ExitDialog

@Composable
fun SidereaApp(viewModel: MainViewModel) {

    val activity: Activity = LocalContext.current as Activity

    val systemUIController = rememberSystemUiController()

    systemUIController.setSystemBarsColor(
        viewModel.settings.colorScheme.surfaceColorAtElevation(1.dp)
    )

    systemUIController.setNavigationBarColor(
        viewModel.settings.colorScheme.surfaceColorAtElevation(1.dp)
    )

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