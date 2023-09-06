package ru.tanexc.siderakt.presentation.main.components


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import dev.olshevski.navigation.reimagined.NavBackHandler
import dev.olshevski.navigation.reimagined.NavHost
import dev.olshevski.navigation.reimagined.navigate
import dev.olshevski.navigation.reimagined.popAll
import dev.olshevski.navigation.reimagined.rememberNavController
import ru.tanexc.siderakt.R
import ru.tanexc.siderakt.domain.model.Screen
import ru.tanexc.siderakt.presentation.catalog.CatalogScreen
import ru.tanexc.siderakt.presentation.settings.ProfileScreen
import ru.tanexc.siderakt.presentation.test.screen.TestScreen
import ru.tanexc.siderakt.presentation.ui.theme.Typography


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavHostScreen(
    modifier: Modifier = Modifier,
    startDestination: Screen,
    onScreenChanged: (screen: Screen) -> Unit,
    colorScheme: ColorScheme
) {
    val navController = rememberNavController(startDestination = startDestination)

    var selectedScreen by remember { mutableStateOf(startDestination) }
    var searchBarValue by remember { mutableStateOf("") }
    var bottomBarVisibility by remember { mutableStateOf(true) }
    var isSearchingMode by remember { mutableStateOf(false) }

    val screens = listOf(
        Screen.Profile,
        Screen.Catalog,
        Screen.Test
    )

    Scaffold(modifier = modifier,
        topBar = {
                when (selectedScreen) {
                    Screen.Catalog -> {

                        CenterAlignedTopAppBar(title = {

                            when(isSearchingMode) {
                                true -> BasicTextField(
                                    value = searchBarValue,
                                    onValueChange = {
                                        searchBarValue = it
                                    },
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(48.dp),
                                    enabled = true,
                                    maxLines = 1,
                                    singleLine = true,
                                    cursorBrush = SolidColor(contentColorFor(colorScheme.surface)),
                                    textStyle = Typography.titleLarge.copy(color = contentColorFor(colorScheme.surface)),
                                    decorationBox = { innerTextField ->
                                        Row(
                                            modifier = Modifier
                                                .fillMaxSize()
                                        ) {
                                            Column(
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .align(Alignment.CenterVertically)
                                                    .padding(4.dp)
                                            ) {
                                                innerTextField()
                                            }
                                        }
                                    }
                                )
                                else ->
                                    Text(
                                        stringResource(selectedScreen.label),
                                        textAlign = TextAlign.Center,
                                        fontFamily = FontFamily(Font(R.font.montserrat))
                                    )
                            }
                        },
                            actions = {
                                if (isSearchingMode) IconButton(onClick = {
                                    isSearchingMode = false
                                    searchBarValue = ""
                                }) { Icon(Icons.Outlined.Close, null) }
                                else IconButton(onClick = { isSearchingMode = true }) {
                                    Icon(Icons.Outlined.Search, null)
                                }
                            }
                        )
                    }

                    else -> CenterAlignedTopAppBar(
                        title = {
                            Text(
                                stringResource(selectedScreen.label),
                                textAlign = TextAlign.Center,
                                fontFamily = FontFamily(Font(R.font.montserrat))
                            )
                        }
                    )
                }
            },
        bottomBar = {
            AnimatedVisibility(
                visible = bottomBarVisibility,
                exit = ExitTransition.None,
                enter = EnterTransition.None
            ) {
                BottomAppBar(modifier) {
                    screens.forEach {
                        NavigationBarItem(
                            selected = it.label == startDestination.label,
                            onClick = {
                                onScreenChanged(it)
                                navController.popAll()
                                navController.navigate(it)

                                selectedScreen = it
                            },
                            label = {
                                Text(
                                    stringResource(it.label),
                                    fontFamily = FontFamily(Font(R.font.montserrat))
                                )
                            },
                            icon = {
                                Icon(
                                    when (selectedScreen.label == it.label) {
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
                    bottomBarVisibility = true
                    onScreenChanged(screen)
                }

                is Screen.Catalog -> {
                    CatalogScreen(
                        modifier.padding(innerPadding),
                        searchBarValue
                    )
                    bottomBarVisibility = true
                    onScreenChanged(screen)
                }

                is Screen.Test -> {
                    TestScreen(modifier.padding(innerPadding))
                    bottomBarVisibility = true
                    onScreenChanged(screen)

                }
            }

        }
    }

}