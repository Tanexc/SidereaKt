package ru.tanec.siderakt.presentation.catalog

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Error
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import dev.olshevski.navigation.reimagined.hilt.hiltViewModel
import ru.tanec.siderakt.R
import ru.tanec.siderakt.core.util.State
import ru.tanec.siderakt.data.utils.SettingsValues
import ru.tanec.siderakt.presentation.catalog.components.ConstellationItem
import ru.tanec.siderakt.presentation.catalog.viewModel.CatalogViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CatalogScreen(
    modifier: Modifier = Modifier
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

            is State.Success ->


            LazyColumn {

                    items(state.data ?: emptyList()) {
                        if (state.data!!.indexOf(it) == 0) {
                            Spacer(modifier = Modifier.height(64.dp))
                        }
                        ConstellationItem(
                            constellation = it,
                            backgroundColor = viewModel.colorScheme.secondaryContainer.copy(0.15f),
                            borderColor = viewModel.colorScheme.outline
                        )
                    }
                }
        }
    }
}