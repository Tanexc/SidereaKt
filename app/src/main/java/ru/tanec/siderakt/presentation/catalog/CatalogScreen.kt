package ru.tanec.siderakt.presentation.catalog

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import dev.olshevski.navigation.reimagined.hilt.hiltViewModel
import ru.tanec.siderakt.presentation.catalog.components.ConstellationItem
import ru.tanec.siderakt.presentation.catalog.viewModel.CatalogViewModel

@Composable
fun CatalogScreen(
    modifier: Modifier = Modifier
) {
    val viewModel: CatalogViewModel = hiltViewModel()
    
    LazyColumn {
        items(viewModel.constellationList) {
            ConstellationItem(modifier, it)
        }
    }
}