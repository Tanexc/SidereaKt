package ru.tanec.siderakt.presentation.catalog

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.ArrowUpward
import androidx.compose.material.icons.outlined.Error
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import dev.olshevski.navigation.reimagined.hilt.hiltViewModel
import kotlinx.coroutines.launch
import ru.tanec.siderakt.R
import ru.tanec.siderakt.core.util.State
import ru.tanec.siderakt.data.utils.SettingsValues
import ru.tanec.siderakt.domain.model.Constellation
import ru.tanec.siderakt.domain.model.Screen
import ru.tanec.siderakt.presentation.catalog.components.CatalogSearchBar
import ru.tanec.siderakt.presentation.catalog.components.ConstellationItem
import ru.tanec.siderakt.presentation.catalog.viewModel.CatalogViewModel
import ru.tanec.siderakt.presentation.utils.isScrollingUp


@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@SuppressLint("FlowOperatorInvokedInComposition", "CoroutineCreationDuringComposition")
@Composable
fun CatalogScreen(
    modifier: Modifier = Modifier,
    onScreenChange: (Screen, Constellation, @Composable () -> Unit) -> Unit,
    navigationIconAction: () -> Unit
) {
    val viewModel: CatalogViewModel = hiltViewModel()

    Box(modifier = modifier.fillMaxSize()) {

        val state = viewModel.constellationListState

        when (state) {
            is State.Loading -> CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )

            is State.Error -> {
                Icon(
                    Icons.Outlined.Error,
                    null,
                    modifier = Modifier.align(Alignment.Center)
                )
                Text(state.message ?: stringResource(R.string.error))
            }

            is State.Success -> {

                val lazyListState: LazyListState = rememberLazyListState()
                val coroutineScope = rememberCoroutineScope()

                LazyColumn(state = lazyListState) {

                    items(state.data?.filter {
                        it.title.lowercase().contains(viewModel.searchString)
                    } ?: emptyList()) {
                        if (state.data!!.indexOf(it) == 0) {
                            Spacer(modifier = Modifier.height(64.dp))
                        }
                        ConstellationItem(
                            constellation = it,
                            backgroundColor = viewModel.colorScheme.secondaryContainer.copy(
                                if (SettingsValues.sidereaUseDarkTheme.value) {
                                    0.2f
                                } else {
                                    0.7f
                                }
                            ),
                            borderColor = viewModel.colorScheme.outline
                        ) {
                            onScreenChange(
                                Screen.Constellation,
                                it
                            ) {
                                CenterAlignedTopAppBar(title = {
                                    Text(
                                        it.title + "( " + it.lat + " )",
                                        modifier = Modifier.basicMarquee(),
                                        fontFamily = FontFamily(Font(R.font.montserrat))
                                    )
                                },
                                navigationIcon = {Icon(Icons.Outlined.ArrowBack, null, modifier = Modifier.clickable(onClick = { navigationIconAction() }))})
                            }
                        }
                    }
                }

                CatalogSearchBar(
                    modifier = Modifier
                        .padding(4.dp)
                        .absoluteOffset(
                            x = 0.dp,
                            y = if (lazyListState.firstVisibleItemIndex == 0) {
                                (-1 * lazyListState.firstVisibleItemScrollOffset).dp / 2
                            } else {
                                (-52).dp

                            }

                        ),
                    backgroundColor = viewModel.colorScheme.secondaryContainer.copy(
                        if (SettingsValues.sidereaUseDarkTheme.value) {
                            0.2f
                        } else {
                            0.7f
                        }
                    ),
                    fontColor = viewModel.colorScheme.onSecondaryContainer,
                    onActiveChange = {},
                    onSearchChange = {
                        viewModel.updateSearchString(it)
                    }
                )
                AnimatedVisibility(
                    visible = lazyListState.isScrollingUp(),
                    modifier = Modifier.align(Alignment.BottomEnd),
                    enter = slideInVertically { it },
                    exit = slideOutVertically { it }
                ) {
                    FloatingActionButton(
                        onClick = {
                            coroutineScope.launch {
                                lazyListState.animateScrollToItem(0)
                            }
                        },
                        containerColor = viewModel.colorScheme.secondaryContainer,
                        contentColor = contentColorFor(backgroundColor = viewModel.colorScheme.secondaryContainer),
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .padding(16.dp)
                            .height(56.dp)
                    ) {
                        Icon(
                            Icons.Outlined.ArrowUpward,
                            contentDescription = null,
                            modifier = Modifier.align(
                                Alignment.Center
                            )
                        )
                    }
                }

            }

            else -> {}
        }
    }
}