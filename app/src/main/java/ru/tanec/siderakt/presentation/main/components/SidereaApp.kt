package ru.tanec.siderakt.presentation.main.components

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import ru.tanec.siderakt.R
import ru.tanec.siderakt.presentation.main.viewModel.MainViewModel
import ru.tanec.siderakt.presentation.ui.theme.SidereaTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SidereaApp(viewModel: MainViewModel) {

    SidereaTheme(colorScheme = viewModel.settings.colorScheme) {
        NavHostScreen(
            startDestination = viewModel.currentScreen,
            onScreenChanged = { screen, topAppBar ->
                viewModel.screenChanged(screen)
                viewModel.setTopAppBar(topAppBar)
            },
            topBar = {
                when (viewModel.topAppBar) {
                    null -> CenterAlignedTopAppBar(
                        title = {
                            Text(
                                stringResource(viewModel.currentScreen.label),
                                textAlign = TextAlign.Center,
                                fontFamily = FontFamily(Font(R.font.montserrat))
                            )
                        }
                    )
                    else -> viewModel.topAppBar!!()
                }

            }
        )

    }


}