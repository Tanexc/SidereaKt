package ru.tanec.siderakt.presentation.main.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import ru.tanec.siderakt.core.util.Scheme
import ru.tanec.siderakt.presentation.main.viewModel.MainViewModel
import ru.tanec.siderakt.presentation.ui.theme.SidereaTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SidereaApp(viewModel: MainViewModel) {

    SidereaTheme(colorScheme = viewModel.appTheme) {
        val modifier = Modifier
        Column {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        stringResource(viewModel.currentScreen.label),
                        textAlign = TextAlign.Center
                    )
                },
                modifier = modifier
            )
            NavHostScreen(
                modifier = modifier,
                startDestination = viewModel.currentScreen,
                onScreenChanged = {
                    viewModel.screenChanged(it)
                }
            )
        }

    }


}