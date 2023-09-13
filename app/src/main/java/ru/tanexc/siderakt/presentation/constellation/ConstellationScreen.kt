package ru.tanexc.siderakt.presentation.constellation

import android.widget.Space
import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.UnfoldLess
import androidx.compose.material.icons.outlined.UnfoldMore
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.TopEnd
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.olshevski.navigation.reimagined.hilt.hiltViewModel
import ru.tanexc.siderakt.R
import ru.tanexc.siderakt.domain.model.Constellation
import ru.tanexc.siderakt.presentation.constellation.viewModel.ConstellationViewModel
import ru.tanexc.siderakt.presentation.ui.theme.Typography
import ru.tanexc.siderakt.presentation.utils.widgets.ItemCard
import ru.tanexc.siderakt.presentation.utils.widgets.Picture

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConstellationScreen(
    modifier: Modifier = Modifier,
    constellation: Constellation? = null,
    onClose: () -> Unit
) {

    val viewModel: ConstellationViewModel = hiltViewModel()

    val borderWidth = if (viewModel.settings.isOutlineElements()) {
        1.dp
    } else {
        0.dp
    }

    if (constellation != null) {

        Column(modifier) {

            CenterAlignedTopAppBar(
                modifier = if (viewModel.settings.isOutlineElements()) {
                    Modifier.drawWithContent {
                        drawContent()
                        drawRect(
                            viewModel.settings.colorScheme.outline,
                            topLeft = Offset(0f, this.size.height),
                            size = Size(this.size.width, density)
                        )
                    }
                } else {
                    Modifier.shadow(elevation = 12.dp)
                },
                title = {
                    Text(
                        constellation.title + " ( " + constellation.lat + " )",
                        style = Typography.titleLarge,
                        textAlign = TextAlign.Center,
                        fontFamily = FontFamily(Font(R.font.montserrat))
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { onClose() }) {
                        Icon(Icons.Outlined.ArrowBack, null)
                    }
                }
            )

            LazyColumn(Modifier.padding(8.dp, 0.dp)) {
                item {
                    Spacer(Modifier.size(16.dp))
                    AnimatedContent(
                        targetState = viewModel.isImageCollapsed,
                        label = ""
                    ) { targetState ->
                        Box(
                            modifier = Modifier
                                .wrapContentHeight()
                                .fillMaxWidth()
                        ) {
                            Picture(
                                modifier = if (!targetState) {
                                    Modifier
                                        .fillMaxWidth()
                                        .clip(RoundedCornerShape(16.dp))
                                } else {
                                    Modifier
                                        .fillMaxWidth()
                                        .clip(RoundedCornerShape(16.dp))
                                        .height(256.dp)
                                }
                                    .border(
                                        width = borderWidth,
                                        shape = RoundedCornerShape(16.dp),
                                        brush = if (borderWidth > 0.dp) SolidColor(viewModel.settings.colorScheme.outline) else SolidColor(
                                            Color.Transparent
                                        )
                                    ),
                                imageURL = constellation.imageURL,
                                contentDescription = "",
                            )
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
                    Spacer(Modifier.size(4.dp))

                }

                item {

                    Row(
                        Modifier
                            .padding(0.dp, 4.dp)
                            .height(intrinsicSize = IntrinsicSize.Max)
                    ) {

                        ItemCard(
                            modifier = Modifier
                                .weight(0.5f)
                                .fillMaxHeight(),
                            borderWidth = borderWidth,
                            borderColor = viewModel.settings.colorScheme.outline,
                            backgroundColor = viewModel.settings.colorScheme.secondaryContainer.copy(
                                0.3f
                            )
                        ) {

                            Column(
                                Modifier
                                    .padding(8.dp)
                                    .fillMaxWidth(), horizontalAlignment = CenterHorizontally
                            ) {

                                Text(
                                    stringResource(R.string.ascent),
                                    textAlign = TextAlign.Center,
                                    style = Typography.bodyLarge.copy(
                                        fontSize = 18.sp,
                                        fontWeight = FontWeight.Bold,
                                        fontFamily = FontFamily(Font(R.font.montserrat))
                                    )
                                )

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

                        Spacer(modifier = Modifier.size(8.dp))

                        ItemCard(
                            modifier = Modifier
                                .weight(0.5f)
                                .fillMaxHeight(),
                            borderWidth = borderWidth,
                            borderColor = viewModel.settings.colorScheme.outline,
                            backgroundColor = viewModel.settings.colorScheme.secondaryContainer.copy(
                                0.3f
                            )
                        ) {

                            Column(
                                Modifier
                                    .padding(8.dp)
                                    .fillMaxWidth(), horizontalAlignment = CenterHorizontally
                            ) {

                                Text(
                                    stringResource(R.string.declination),
                                    textAlign = TextAlign.Center,
                                    style = Typography.bodyLarge.copy(
                                        fontSize = 18.sp,
                                        fontWeight = FontWeight.Bold,
                                        fontFamily = FontFamily(Font(R.font.montserrat))
                                    )
                                )

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

                item {

                    Row(
                        Modifier
                            .padding(0.dp, 4.dp)
                            .height(intrinsicSize = IntrinsicSize.Max)
                    ) {

                        ItemCard(
                            modifier = Modifier
                                .weight(0.5f)
                                .fillMaxHeight(),
                            borderWidth = borderWidth,
                            borderColor = viewModel.settings.colorScheme.outline,
                            backgroundColor = viewModel.settings.colorScheme.secondaryContainer.copy(
                                0.3f
                            )
                        ) {

                            Column(
                                Modifier
                                    .padding(8.dp)
                                    .fillMaxWidth(), horizontalAlignment = CenterHorizontally
                            ) {

                                Text(
                                    stringResource(R.string.alpha),
                                    textAlign = TextAlign.Center,
                                    style = Typography.bodyLarge.copy(
                                        fontSize = 18.sp,
                                        fontWeight = FontWeight.Bold,
                                        fontFamily = FontFamily(Font(R.font.montserrat))
                                    )
                                )

                                Text(
                                    constellation.alphaStar,
                                    modifier = Modifier
                                        .align(CenterHorizontally)
                                        .padding(top = 4.dp, bottom = 2.dp)
                                )
                            }

                        }



                        Spacer(modifier = Modifier.size(8.dp))

                        ItemCard(
                            modifier = Modifier
                                .weight(0.5f)
                                .fillMaxHeight(),
                            borderWidth = borderWidth,
                            borderColor = viewModel.settings.colorScheme.outline,
                            backgroundColor = viewModel.settings.colorScheme.secondaryContainer.copy(
                                0.3f
                            )
                        ) {

                            Column(
                                Modifier
                                    .padding(8.dp)
                                    .fillMaxWidth(), horizontalAlignment = CenterHorizontally
                            ) {

                                Text(
                                    stringResource(R.string.location),
                                    textAlign = TextAlign.Center,
                                    style = Typography.bodyLarge.copy(
                                        fontSize = 18.sp,
                                        fontWeight = FontWeight.Bold,
                                        fontFamily = FontFamily(Font(R.font.montserrat))
                                    )
                                )

                                Text(
                                    when (constellation.hemisphere) {
                                        1 -> stringResource(R.string.north)
                                        2 -> stringResource(R.string.south)
                                        else -> stringResource(R.string.equatorial)
                                    },
                                    modifier = Modifier
                                        .align(CenterHorizontally)
                                        .padding(top = 4.dp, bottom = 2.dp)
                                )
                            }

                        }

                    }

                }

                item {

                    Row(
                        Modifier
                            .padding(0.dp, 4.dp)
                    ) {

                        ItemCard(
                            modifier = Modifier
                                .fillMaxWidth(),
                            borderWidth = borderWidth,
                            borderColor = viewModel.settings.colorScheme.outline,
                            backgroundColor = viewModel.settings.colorScheme.secondaryContainer.copy(
                                0.3f
                            )
                        ) {

                            Text(
                                constellation.info,
                                modifier = Modifier.padding(16.dp, 8.dp),
                                textAlign = TextAlign.Justify,
                                style = Typography.bodyLarge.copy(
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold,
                                    fontFamily = FontFamily(Font(R.font.montserrat))
                                )
                            )

                        }

                    }

                }

            }
        }
    }
}