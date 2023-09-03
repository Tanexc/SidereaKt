package ru.tanexc.siderakt.presentation.constellation

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.outlined.UnfoldLess
import androidx.compose.material.icons.outlined.UnfoldMore
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.TopEnd
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import dev.olshevski.navigation.reimagined.hilt.hiltViewModel
import ru.tanexc.siderakt.presentation.ui.theme.Typography
import ru.tanexc.siderakt.presentation.utils.widgets.ItemCard
import ru.tanexc.siderakt.R
import ru.tanexc.siderakt.domain.model.Constellation
import ru.tanexc.siderakt.presentation.constellation.viewModel.ConstellationViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ConstellationScreen(
    modifier: Modifier = Modifier,
    constellation: Constellation? = null
) {

    val viewModel: ConstellationViewModel = hiltViewModel()

    if (constellation != null) {
        LazyColumn(modifier = modifier) {
            item {
                AnimatedContent(targetState = viewModel.isImageCollapsed, label = "") { targetState ->
                    Box(modifier = Modifier
                        .wrapContentHeight()
                        .fillMaxWidth()) {
                        SubcomposeAsyncImage(
                            modifier = if (!targetState) {
                                Modifier
                                    .fillMaxWidth()
                                    .clip(RoundedCornerShape(16.dp))
                                    .padding(6.dp)
                            } else {
                                Modifier
                                    .fillMaxWidth()
                                    .clip(RoundedCornerShape(16.dp))
                                    .padding(6.dp)
                                    .height(256.dp)
                            },
                            model = constellation.imageURL,
                            contentDescription = "",
                        ) {
                            when (painter.state) {
                                is AsyncImagePainter.State.Loading ->
                                    Box(
                                        modifier = Modifier
                                            .align(Center)
                                    ) {
                                        CircularProgressIndicator(
                                            modifier = Modifier
                                                .align(Center)
                                                .height(48.dp)
                                        )
                                    }

                                is AsyncImagePainter.State.Success ->
                                    SubcomposeAsyncImageContent(
                                        modifier = Modifier
                                            .align(Center)
                                            .clip(RoundedCornerShape(16.dp)),
                                        contentScale = ContentScale.FillWidth
                                    )

                                else ->
                                    Box(
                                        modifier = Modifier
                                            .align(Center)
                                    ) {
                                        Icon(
                                            Icons.Default.Error,
                                            null,
                                            modifier = Modifier.align(Center)
                                        )
                                        Text(
                                            text = stringResource(R.string.check_internet),
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .align(Alignment.BottomCenter)
                                                .padding(4.dp),
                                            textAlign = TextAlign.Center,
                                            maxLines = 1
                                        )

                                    }

                            }

                        }
                        FilledIconButton(
                            onClick = { viewModel.changeImageCollapsedState() },
                            modifier = Modifier
                                .size(48.dp)
                                .align(TopEnd)
                                .padding(10.dp)
                        ) {
                            when (viewModel.isImageCollapsed) {
                                true -> Icon(
                                    Icons.Outlined.UnfoldMore,
                                    contentDescription = null,
                                    modifier = Modifier.padding(2.dp)
                                )

                                else -> Icon(
                                    Icons.Outlined.UnfoldLess,
                                    contentDescription = null,
                                    modifier = Modifier.padding(2.dp)
                                )
                            }

                        }
                    }

                }
            }

            item {
                Row(Modifier.padding(start = 2.dp, end = 2.dp, bottom = 2.dp)) {
                    ItemCard(
                        modifier = Modifier
                            .fillMaxWidth(0.5f)
                            .height(128.dp)
                            .padding(4.dp),
                        borderColor = viewModel.settings.colorScheme.outline,
                        backgroundColor = viewModel.settings.colorScheme.secondaryContainer.copy(
                            if (viewModel.settings.isThemeInDarkMode()) {
                                0.2f
                            } else {
                                0f
                            }
                        )
                    ) {
                        Column(Modifier.fillMaxWidth()) {
                            Text(
                                stringResource(R.string.ascent),
                                modifier = Modifier
                                    .padding(top = 8.dp, bottom = 4.dp)
                                    .fillMaxWidth(),
                                maxLines = 2,
                                textAlign = TextAlign.Center,
                                lineHeight = 16.5.sp
                            )
                            Box(Modifier.fillMaxSize()) {
                                Column(
                                    Modifier
                                        .wrapContentHeight()
                                        .align(Center)) {
                                    Text(
                                        stringResource(R.string.from) + " " + constellation.ascent.split(
                                            "/"
                                        )[0],
                                        modifier = Modifier
                                            .align(CenterHorizontally)
                                            .padding(top = 4.dp, bottom = 2.dp)
                                    )
                                    Text(
                                        stringResource(R.string.to) + " " + constellation.ascent.split(
                                            "/"
                                        )[1],
                                        modifier = Modifier
                                            .align(CenterHorizontally)
                                            .padding(bottom = 8.dp, top = 2.dp)
                                    )
                                }
                            }
                        }

                    }
                    ItemCard(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(128.dp)
                            .padding(4.dp),
                        borderColor = viewModel.settings.colorScheme.outline,
                        backgroundColor = viewModel.settings.colorScheme.secondaryContainer.copy(
                            if (viewModel.settings.isThemeInDarkMode()) {
                                0.2f
                            } else {
                                0f
                            }
                        )
                    ) {
                        Column(Modifier.fillMaxWidth()) {
                            Text(
                                stringResource(R.string.declination),
                                modifier = Modifier
                                    .basicMarquee()
                                    .padding(top = 8.dp, bottom = 2.dp)
                                    .align(CenterHorizontally),
                                maxLines = 1
                            )
                            Box(Modifier.fillMaxSize()) {
                                Column(
                                    Modifier
                                        .wrapContentHeight()
                                        .align(Center)) {
                                    Text(
                                        stringResource(R.string.from) + " " + constellation.declination.split(
                                            "/"
                                        )[0],
                                        modifier = Modifier
                                            .align(CenterHorizontally)
                                            .padding(top = 4.dp, bottom = 2.dp)
                                    )
                                    Text(
                                        stringResource(R.string.to) + " " + constellation.declination.split(
                                            "/"
                                        )[1],
                                        modifier = Modifier
                                            .align(CenterHorizontally)
                                            .padding(bottom = 8.dp, top = 2.dp)
                                    )
                                }
                            }

                        }

                    }

                }
            }

            item {
                Row(Modifier.padding(start = 2.dp, end = 2.dp, bottom = 2.dp)) {

                    ItemCard(
                        modifier = Modifier.fillMaxWidth(0.5f).padding(4.dp), borderColor = viewModel.settings.colorScheme.outline,
                        backgroundColor = viewModel.settings.colorScheme.secondaryContainer.copy(
                            if (viewModel.settings.isThemeInDarkMode()) {
                                0.2f
                            } else {
                                0f
                            }
                        )
                    ) {

                        Column(Modifier.fillMaxWidth()) {
                            Text(
                                stringResource(R.string.location),
                                modifier = Modifier
                                    .padding(top = 8.dp, bottom = 4.dp)
                                    .align(CenterHorizontally)
                            )
                            Text(
                                when (constellation.hemisphere) {
                                    0 -> stringResource(R.string.equatorial)
                                    1 -> stringResource(R.string.north_1)
                                    else -> stringResource(R.string.south_1)
                                },
                                modifier = Modifier
                                    .align(CenterHorizontally)
                                    .padding(top = 4.dp, bottom = 8.dp)
                            )
                        }

                    }
                    ItemCard(
                        modifier = Modifier.fillMaxWidth().padding(4.dp), borderColor = viewModel.settings.colorScheme.outline,
                        backgroundColor = viewModel.settings.colorScheme.secondaryContainer.copy(
                            if (viewModel.settings.isThemeInDarkMode()) {
                                0.2f
                            } else {
                                0f
                            }
                        )
                    ) {

                        Column(Modifier.fillMaxWidth()) {
                            Text(
                                stringResource(R.string.alpha),
                                modifier = Modifier
                                    .padding(top = 8.dp, bottom = 4.dp)
                                    .align(CenterHorizontally)
                            )
                            Text(
                                constellation.alphaStar,
                                modifier = Modifier
                                    .align(CenterHorizontally)
                                    .padding(top = 4.dp, bottom = 8.dp)
                            )
                        }

                    }

                }

            }

            item {
                ItemCard(
                    Modifier.padding(4.dp, 4.dp, 4.dp, 0.dp),
                    borderColor = viewModel.settings.colorScheme.outline,
                    backgroundColor = viewModel.settings.colorScheme.secondaryContainer.copy(
                        if (viewModel.settings.isThemeInDarkMode()) {
                            0.2f
                        } else {
                            0f
                        }
                    )
                ) {
                    Text(
                        text = constellation.info,
                        modifier = Modifier.padding(16.dp),
                        textAlign = TextAlign.Justify,
                        style = Typography.bodyLarge
                    )
                }
            }

        }
    }
}