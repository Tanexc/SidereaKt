package ru.tanexc.siderakt.presentation.catalog

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowUpward
import androidx.compose.material.icons.outlined.Error
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import dev.olshevski.navigation.reimagined.hilt.hiltViewModel
import kotlinx.coroutines.launch
import ru.tanexc.siderakt.R
import ru.tanexc.siderakt.core.util.state.State
import ru.tanexc.siderakt.presentation.catalog.viewModel.CatalogViewModel
import ru.tanexc.siderakt.presentation.constellation.ConstellationScreen
import ru.tanexc.siderakt.presentation.utils.isScrollingUp
import ru.tanexc.siderakt.presentation.utils.widgets.ConstellationItem


@SuppressLint("FlowOperatorInvokedInComposition", "CoroutineCreationDuringComposition")
@Composable
fun CatalogScreen(
    modifier: Modifier = Modifier,
    searchBarValue: String
) {
    val viewModel: CatalogViewModel = hiltViewModel()

    LaunchedEffect(searchBarValue) {
        viewModel.updateSearchString(searchBarValue)
    }


    if (viewModel.currentConstellation == null) {
        Box(modifier = modifier.fillMaxSize()) {

            when (val state = viewModel.constellationListState) {
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
                            ConstellationItem(
                                constellation = it,
                                backgroundColor = viewModel.settings.colorScheme.secondaryContainer.copy(
                                    if (viewModel.settings.isThemeInDarkMode()) {
                                        0.2f
                                    } else {
                                        0.7f
                                    }
                                ),
                                borderColor = viewModel.settings.colorScheme.outline
                            ) {
                                viewModel.updateCurrentConstellation(it)
                            }
                        }
                    }
                    AnimatedVisibility(
                        visible = lazyListState.isScrollingUp() && remember { derivedStateOf { lazyListState.firstVisibleItemIndex } }.value != 0,
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
                            containerColor = viewModel.settings.colorScheme.secondaryContainer,
                            contentColor = contentColorFor(backgroundColor = viewModel.settings.colorScheme.secondaryContainer),
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
            }
        }
    }
    else {
        ConstellationScreen(
            Modifier
                .zIndex(11f)
                .fillMaxSize()
                .background(viewModel.settings.colorScheme.surface),
            viewModel.currentConstellation,
            onClose = { viewModel.updateCurrentConstellation(null) }
        )
    }

}