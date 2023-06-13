package ru.tanec.siderakt.presentation.main.components


import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import dev.olshevski.navigation.reimagined.NavBackHandler
import dev.olshevski.navigation.reimagined.NavHost
import dev.olshevski.navigation.reimagined.navigate
import dev.olshevski.navigation.reimagined.pop
import dev.olshevski.navigation.reimagined.popAll
import dev.olshevski.navigation.reimagined.rememberNavController
import ru.tanec.siderakt.R
import ru.tanec.siderakt.domain.model.Screen
import ru.tanec.siderakt.presentation.catalog.CatalogScreen
import ru.tanec.siderakt.presentation.settings.SettingsScreen
import ru.tanec.siderakt.presentation.test.TestScreen


@Composable
fun NavHostScreen(
    modifier: Modifier = Modifier,
    startDestination: Screen,
    onScreenChanged: (screen: Screen) -> Unit
) {
    val navController = rememberNavController<Screen>(startDestination = Screen.Settings)
    val selectedScreen = remember { mutableStateOf(startDestination) }

    val screens = listOf(
        Screen.Settings,
        Screen.Catalog,
        Screen.Test
    )

    Scaffold(modifier = modifier,
        bottomBar = {
            BottomAppBar {
                screens.forEach {
                    NavigationBarItem(
                        selected = it.label == startDestination.label,
                        onClick = {
                            onScreenChanged(it)
                            navController.navigate(it)
                            navController.popAll()
                            selectedScreen.value = it
                        },
                        label = { Text(stringResource(it.label)) },
                        icon = {
                            Icon(
                                when (selectedScreen.value.label == it.label) {
                                    true -> it.iconFilled
                                    false -> it.iconOutlined
                                },
                                contentDescription = null
                            )
                        },
                        alwaysShowLabel = false
                    )
                }
            }
        }) { innerPadding ->

        NavBackHandler(navController)
        NavHost(navController) { screen ->
            when (screen) {
                is Screen.Settings -> SettingsScreen(modifier.padding(innerPadding))
                is Screen.Catalog -> CatalogScreen(modifier.padding(innerPadding))
                is Screen.Test -> TestScreen(modifier.padding(innerPadding))
            }

        }
    }

}