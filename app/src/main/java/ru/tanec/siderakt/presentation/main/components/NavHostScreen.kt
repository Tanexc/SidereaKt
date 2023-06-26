package ru.tanec.siderakt.presentation.main.components


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import dev.olshevski.navigation.reimagined.NavBackHandler
import dev.olshevski.navigation.reimagined.NavHost
import dev.olshevski.navigation.reimagined.navigate
import dev.olshevski.navigation.reimagined.popAll
import dev.olshevski.navigation.reimagined.popUpTo
import dev.olshevski.navigation.reimagined.rememberNavController
import ru.tanec.siderakt.R
import ru.tanec.siderakt.domain.model.Constellation
import ru.tanec.siderakt.domain.model.Screen
import ru.tanec.siderakt.presentation.catalog.CatalogScreen
import ru.tanec.siderakt.presentation.constellation.ConstellationScreen
import ru.tanec.siderakt.presentation.settings.ProfileScreen
import ru.tanec.siderakt.presentation.test.TestScreen


@Composable
fun NavHostScreen(
    modifier: Modifier = Modifier,
    startDestination: Screen,
    onScreenChanged: (screen: Screen, topBar: (@Composable () -> Unit)?) -> Unit,
    topBar: @Composable () -> Unit
) {

    val navController = rememberNavController(startDestination = startDestination)
    val selectedScreen = remember { mutableStateOf(startDestination) }

    val bottomBarVisibility = remember { mutableStateOf(true) }

    val constellationScreenData: MutableState<Constellation?> = remember { mutableStateOf(null) }

    val screens = listOf(
        Screen.Profile,
        Screen.Catalog,
        Screen.Test
    )

    Scaffold(modifier = modifier,
        topBar = topBar,
        bottomBar = {
            AnimatedVisibility(visible = bottomBarVisibility.value, exit = ExitTransition.None, enter = slideInVertically {it}) {
                BottomAppBar(modifier) {
                    screens.forEach {
                        NavigationBarItem(
                            selected = it.label == startDestination.label,
                            onClick = {
                                onScreenChanged(it, null)
                                navController.popAll()
                                navController.navigate(it)

                                selectedScreen.value = it
                            },
                            label = {
                                Text(
                                    stringResource(it.label),
                                    fontFamily = FontFamily(Font(R.font.montserrat))
                                )
                            },
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
            }
        }) { innerPadding ->

        NavBackHandler(navController)
        NavHost(navController) { screen ->
            when (screen) {
                is Screen.Profile -> {
                    ProfileScreen(modifier.padding(innerPadding))
                    bottomBarVisibility.value = true
                    onScreenChanged(screen, null)
                }

                is Screen.Catalog -> {
                    CatalogScreen(modifier.padding(innerPadding),
                        onScreenChange = { _screen, _constellation, _topBar ->
                            onScreenChanged(_screen, _topBar)
                            navController.navigate(_screen)
                            selectedScreen.value = _screen
                            bottomBarVisibility.value = false
                            constellationScreenData.value = _constellation
                        },
                        navigationIconAction = {
                            onScreenChanged(screen, null)
                            navController.navigate(screen)
                            selectedScreen.value = screen
                        })
                    bottomBarVisibility.value = true
                    onScreenChanged(screen, null)
                }

                is Screen.Test -> {
                    TestScreen(modifier.padding(innerPadding))
                    bottomBarVisibility.value = true
                    onScreenChanged(screen, null)

                }

                is Screen.Constellation -> {
                    ConstellationScreen(
                        modifier.padding(innerPadding),
                        constellationScreenData.value
                    )
                }
            }

        }
    }

}