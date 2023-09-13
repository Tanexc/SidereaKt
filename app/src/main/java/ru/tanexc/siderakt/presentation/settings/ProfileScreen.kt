package ru.tanexc.siderakt.presentation.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Bookmark
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material.icons.outlined.BorderStyle
import androidx.compose.material.icons.outlined.BugReport
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material.icons.outlined.ColorLens
import androidx.compose.material.icons.outlined.DarkMode
import androidx.compose.material.icons.outlined.Link
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Alignment.Companion.Start
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.olshevski.navigation.reimagined.hilt.hiltViewModel
import ru.tanec.siderakt.presentation.settings.viewModel.SettingsViewModel
import ru.tanexc.siderakt.BuildConfig
import ru.tanexc.siderakt.R
import ru.tanexc.siderakt.core.util.AUTHOR
import ru.tanexc.siderakt.core.util.AUTHOR_NICKNAME
import ru.tanexc.siderakt.core.util.AUTHOR_PICTURE_URL
import ru.tanexc.siderakt.core.util.CONSTELLATION_DATA_SOURCE_URL
import ru.tanexc.siderakt.core.util.GITHUB_PROFILE_URL
import ru.tanexc.siderakt.core.util.GITHUB_REPO_ISSUE_URL
import ru.tanexc.siderakt.core.util.GITHUB_REPO_URL
import ru.tanexc.siderakt.core.util.SOURCE_SITE
import ru.tanexc.siderakt.core.util.Theme
import ru.tanexc.siderakt.core.util.state.DialogState
import ru.tanexc.siderakt.presentation.utils.widgets.ItemCard
import ru.tanexc.siderakt.presentation.utils.widgets.Picture
import ru.tanexc.siderakt.presentation.utils.widgets.dialogs.OpenLinkDialog


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier
) {
    val viewModel: SettingsViewModel = hiltViewModel()
    val uriHandler = LocalUriHandler.current

    var selectedTheme: Theme? by remember { mutableStateOf(viewModel.settings.theme()) }

    LaunchedEffect(selectedTheme) {
        viewModel.changeTheme(selectedTheme!!)
    }

    LazyColumn(modifier.padding(16.dp, 0.dp)) {

        item {

            Text(
                stringResource(R.string.stats),
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp),
                fontWeight = FontWeight.ExtraLight,
                fontSize = 22.sp,
            )

            ItemCard(
                backgroundColor = viewModel.settings.colorScheme.tertiaryContainer.copy(if (!viewModel.settings.isThemeInDarkMode()) .6f else 1f),
                borderRadius = 22.dp,
                borderColor = if (viewModel.settings.isOutlineElements()) {
                    viewModel.settings.colorScheme.outline
                } else {
                    Color.Transparent
                }
            ) {
                Column {
                    Spacer(modifier = Modifier.size(16.dp))
                    Row(Modifier.padding(22.dp, 0.dp)) {
                        Icon(
                            painter = painterResource(R.drawable.alpha_a),
                            contentDescription = null,
                            tint = contentColorFor(viewModel.settings.colorScheme.tertiaryContainer),
                            modifier = Modifier.align(CenterVertically)
                        )
                        Spacer(modifier = Modifier.size(12.dp))
                        Text(
                            stringResource(R.string.constellations) + ": ",
                            modifier = Modifier
                                .align(CenterVertically)
                                .weight(1f),
                            color = contentColorFor(viewModel.settings.colorScheme.tertiaryContainer)
                        )
                        Text(
                            "${viewModel.settings.learned()}",
                            color = contentColorFor(viewModel.settings.colorScheme.tertiaryContainer),
                            modifier = Modifier.align(CenterVertically)
                        )
                    }

                    Spacer(
                        modifier = Modifier
                            .padding(8.dp, 16.dp)
                            .fillMaxWidth()
                            .height(1.dp)
                            .background(viewModel.settings.colorScheme.outline.copy(0.25f))
                    )

                    Row(Modifier.padding(22.dp, 0.dp)) {
                        Icon(
                            painterResource(R.drawable.alpha_n),
                            null,
                            tint = contentColorFor(viewModel.settings.colorScheme.tertiaryContainer),
                            modifier = Modifier.align(CenterVertically)
                        )
                        Spacer(modifier = Modifier.size(12.dp))
                        Text(
                            stringResource(R.string.north) + ": ",
                            modifier = Modifier
                                .align(CenterVertically)
                                .weight(1f),
                            color = contentColorFor(viewModel.settings.colorScheme.tertiaryContainer)
                        )
                        Text(
                            "${viewModel.settings.learnedNorth()}",
                            color = contentColorFor(viewModel.settings.colorScheme.tertiaryContainer),
                            modifier = Modifier.align(CenterVertically)
                        )
                    }

                    Spacer(
                        modifier = Modifier
                            .padding(8.dp, 16.dp)
                            .fillMaxWidth()
                            .height(1.dp)
                            .background(viewModel.settings.colorScheme.outline.copy(0.25f))
                    )

                    Row(Modifier.padding(22.dp, 0.dp)) {
                        Icon(
                            painterResource(R.drawable.alpha_s),
                            null,
                            tint = contentColorFor(viewModel.settings.colorScheme.tertiaryContainer),
                            modifier = Modifier.align(CenterVertically)
                        )
                        Spacer(modifier = Modifier.size(12.dp))
                        Text(
                            stringResource(R.string.south) + ": ",
                            modifier = Modifier
                                .align(CenterVertically)
                                .weight(1f),
                            color = contentColorFor(viewModel.settings.colorScheme.tertiaryContainer)
                        )
                        Text(
                            "${viewModel.settings.learnedSouth()}",
                            color = contentColorFor(viewModel.settings.colorScheme.tertiaryContainer),
                            modifier = Modifier.align(CenterVertically)
                        )
                    }

                    Spacer(
                        modifier = Modifier
                            .padding(8.dp, 16.dp)
                            .fillMaxWidth()
                            .height(1.dp)
                            .background(viewModel.settings.colorScheme.outline.copy(0.25f))
                    )

                    Row(Modifier.padding(22.dp, 0.dp)) {
                        Icon(
                            painterResource(R.drawable.alpha_e),
                            null,
                            tint = contentColorFor(viewModel.settings.colorScheme.tertiaryContainer),
                            modifier = Modifier.align(CenterVertically)
                        )
                        Spacer(modifier = Modifier.size(12.dp))
                        Text(
                            stringResource(R.string.equatorials) + ": ",
                            modifier = Modifier
                                .align(CenterVertically)
                                .weight(1f),
                            color = contentColorFor(viewModel.settings.colorScheme.tertiaryContainer)
                        )
                        Text(
                            "${viewModel.settings.learnedEquatorial()}",
                            color = contentColorFor(viewModel.settings.colorScheme.tertiaryContainer),
                            modifier = Modifier.align(CenterVertically)
                        )
                    }
                    Spacer(modifier = Modifier.size(16.dp))
                }

            }


        }

        item {

            Text(
                stringResource(R.string.settings),
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp),
                fontWeight = FontWeight.ExtraLight,
                fontSize = 22.sp,
            )

            ItemCard(
                backgroundColor = viewModel.settings.colorScheme.tertiaryContainer.copy(if (!viewModel.settings.isThemeInDarkMode()) .6f else 1f),
                borderRadius = 22.dp,
                borderColor = if (viewModel.settings.isOutlineElements()) {
                    viewModel.settings.colorScheme.outline
                } else {
                    Color.Transparent
                }
            ) {
                Column {
                    Spacer(modifier = Modifier.size(16.dp))

                    Row(Modifier.padding(22.dp, 0.dp)) {

                        val items = listOf(
                            Theme.Default(),
                            Theme.Orange(),
                            Theme.Green()
                        )

                        Column {

                            Row {

                                Icon(
                                    Icons.Outlined.ColorLens,
                                    null,
                                    tint = contentColorFor(viewModel.settings.colorScheme.tertiaryContainer),
                                    modifier = Modifier.align(CenterVertically)
                                )

                                Spacer(modifier = Modifier.size(12.dp))

                                Text(
                                    stringResource(R.string.main_color),
                                    color = contentColorFor(viewModel.settings.colorScheme.tertiaryContainer)
                                )

                            }
                            Spacer(modifier = Modifier.size(8.dp))

                            SingleChoiceSegmentedButtonRow(
                                modifier = Modifier
                                    .border(
                                        1.dp,
                                        brush = SolidColor(viewModel.settings.colorScheme.outline),
                                        shape = RoundedCornerShape(50),
                                    )
                                    .clip(RoundedCornerShape(50))
                                    .fillMaxWidth()
                            ) {
                                for (theme in items) {
                                    SegmentedButton(
                                        selected = selectedTheme?.id == theme.id,
                                        onClick = { selectedTheme = theme },
                                        shape = RoundedCornerShape(0.dp)
                                    ) {
                                        Text(stringResource(theme.label))
                                    }
                                }
                            }
                        }
                    }

                    Spacer(
                        modifier = Modifier
                            .padding(8.dp, 12.dp)
                            .fillMaxWidth()
                            .height(1.dp)
                            .background(viewModel.settings.colorScheme.outline.copy(0.25f))
                    )

                    Row(Modifier
                        .padding(22.dp, 0.dp)
                        .clickable(
                            interactionSource = remember {
                                MutableInteractionSource()
                            },
                            indication = null
                        ) { viewModel.changeUseDarkTheme() }) {
                        Icon(
                            Icons.Outlined.DarkMode,
                            contentDescription = null,
                            tint = contentColorFor(viewModel.settings.colorScheme.tertiaryContainer),
                            modifier = Modifier.align(CenterVertically)
                        )
                        Spacer(modifier = Modifier.size(12.dp))
                        Text(
                            stringResource(R.string.dark_theme),
                            modifier = Modifier
                                .align(CenterVertically)
                                .weight(1f),
                            color = contentColorFor(viewModel.settings.colorScheme.tertiaryContainer)
                        )
                        Switch(
                            checked = viewModel.settings.isThemeInDarkMode(),
                            onCheckedChange = { viewModel.changeUseDarkTheme() },
                            modifier = Modifier,
                            thumbContent = {
                                when (viewModel.settings.isThemeInDarkMode()) {
                                    true -> Icon(
                                        Icons.Outlined.Check,
                                        null,
                                        modifier = Modifier.padding(4.dp)
                                    )

                                    else -> {}
                                }

                            }
                        )
                    }

                    Spacer(
                        modifier = Modifier
                            .padding(8.dp, 16.dp)
                            .fillMaxWidth()
                            .height(1.dp)
                            .background(viewModel.settings.colorScheme.outline.copy(0.25f))
                    )

                    Row(
                        Modifier
                            .padding(22.dp, 0.dp)
                            .clickable(
                                interactionSource = remember {
                                    MutableInteractionSource()
                                },
                                indication = null
                            ) { viewModel.changeOutlineElemnts() }) {
                        Icon(
                            Icons.Outlined.BorderStyle,
                            null,
                            tint = contentColorFor(viewModel.settings.colorScheme.tertiaryContainer),
                            modifier = Modifier.align(CenterVertically)
                        )
                        Spacer(modifier = Modifier.size(12.dp))
                        Text(
                            stringResource(R.string.use_border),
                            modifier = Modifier
                                .align(CenterVertically)
                                .weight(1f),
                            color = contentColorFor(viewModel.settings.colorScheme.tertiaryContainer)
                        )
                        Switch(
                            checked = viewModel.settings.isOutlineElements(),
                            onCheckedChange = { viewModel.changeOutlineElemnts() },
                            modifier = Modifier,
                            thumbContent = {
                                when (viewModel.settings.isOutlineElements()) {
                                    true -> Icon(
                                        Icons.Outlined.Check,
                                        null,
                                        modifier = Modifier.padding(4.dp)
                                    )

                                    else -> {}
                                }

                            }
                        )
                    }

                    Spacer(
                        modifier = Modifier
                            .padding(8.dp, 16.dp)
                            .fillMaxWidth()
                            .height(1.dp)
                            .background(viewModel.settings.colorScheme.outline.copy(0.25f))
                    )

                    Row(
                        Modifier
                            .padding(22.dp, 0.dp)
                            .clickable(
                                interactionSource = remember {
                                    MutableInteractionSource()
                                },
                                indication = null
                            ) { viewModel.changeMarkLearned() }) {
                        Icon(
                            if (viewModel.settings.isMarkLearned()) Icons.Outlined.Bookmark else Icons.Outlined.BookmarkBorder,
                            null,
                            tint = contentColorFor(viewModel.settings.colorScheme.tertiaryContainer),
                            modifier = Modifier.align(CenterVertically)
                        )
                        Spacer(modifier = Modifier.size(12.dp))
                        Text(
                            stringResource(R.string.mark_learned),
                            modifier = Modifier
                                .align(CenterVertically)
                                .weight(1f),
                            color = contentColorFor(viewModel.settings.colorScheme.tertiaryContainer)
                        )
                        Switch(
                            checked = viewModel.settings.isMarkLearned(),
                            onCheckedChange = { viewModel.changeMarkLearned() },
                            modifier = Modifier,
                            thumbContent = {
                                when (viewModel.settings.isMarkLearned()) {
                                    true -> Icon(
                                        Icons.Outlined.Check,
                                        null,
                                        modifier = Modifier.padding(4.dp)
                                    )

                                    else -> {}
                                }

                            }
                        )
                    }

                    Spacer(modifier = Modifier.size(16.dp))
                }

            }


        }

        item {

            Text(
                stringResource(R.string.about),
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp),
                fontWeight = FontWeight.ExtraLight,
                fontSize = 22.sp,
            )

            ItemCard(
                backgroundColor = viewModel.settings.colorScheme.tertiaryContainer.copy(if (!viewModel.settings.isThemeInDarkMode()) .6f else 1f),
                borderRadius = 22.dp,
                borderColor = if (viewModel.settings.isOutlineElements()) {
                    viewModel.settings.colorScheme.outline
                } else {
                    Color.Transparent
                }
            ) {
                Column {
                    Spacer(modifier = Modifier.size(16.dp))

                    Row(
                        Modifier
                            .padding(22.dp, 0.dp)
                            .clickable {
                                viewModel.setUri(GITHUB_REPO_URL)
                                viewModel.showDialog(DialogState.OpenLink)
                            }) {
                        Icon(
                            painterResource(R.drawable.github),
                            null,
                            tint = contentColorFor(viewModel.settings.colorScheme.tertiaryContainer),
                            modifier = Modifier
                                .align(CenterVertically)
                                .size(24.dp)
                        )
                        Spacer(modifier = Modifier.size(12.dp))
                        Column(
                            modifier = Modifier
                                .align(CenterVertically)
                        ) {
                            Text(
                                stringResource(R.string.code),
                                textAlign = TextAlign.Start,
                                modifier = Modifier.fillMaxWidth(),
                                fontSize = 16.sp,
                                fontFamily = FontFamily(Font(R.font.montserrat))
                            )
                            Row(
                                modifier = Modifier.align(Start)
                            ) {
                                Text(
                                    stringResource(R.string.version),
                                    modifier = Modifier,
                                    fontSize = 14.sp,
                                    fontFamily = FontFamily(Font(R.font.montserrat)),
                                    fontWeight = FontWeight.Bold,
                                    color = viewModel.settings.colorScheme.onSecondaryContainer.copy(
                                        0.5f
                                    )
                                )
                                Text(
                                    " " + BuildConfig.VERSION_NAME,
                                    modifier = Modifier,
                                    fontSize = 14.sp,
                                    fontFamily = FontFamily(Font(R.font.montserrat)),
                                    color = viewModel.settings.colorScheme.onSecondaryContainer.copy(
                                        0.5f
                                    )
                                )
                            }
                        }
                    }

                    Spacer(
                        modifier = Modifier
                            .padding(8.dp, 16.dp)
                            .fillMaxWidth()
                            .height(1.dp)
                            .background(viewModel.settings.colorScheme.outline.copy(0.25f))
                    )

                    Row(
                        Modifier
                            .padding(22.dp, 0.dp)
                            .clickable {
                                viewModel.setUri(CONSTELLATION_DATA_SOURCE_URL)
                                viewModel.showDialog(DialogState.OpenLink)
                            }
                    )
                    {
                        Icon(
                            Icons.Outlined.Link,
                            null,
                            tint = contentColorFor(viewModel.settings.colorScheme.tertiaryContainer),
                            modifier = Modifier
                                .align(CenterVertically)
                                .size(24.dp)
                        )
                        Spacer(modifier = Modifier.size(12.dp))
                        Column(
                            modifier = Modifier
                                .align(CenterVertically)
                        ) {
                            Text(
                                stringResource(R.string.data_source),
                                textAlign = TextAlign.Start,
                                modifier = Modifier.fillMaxWidth(),
                                fontSize = 16.sp,
                                fontFamily = FontFamily(Font(R.font.montserrat))
                            )
                            Text(
                                SOURCE_SITE,
                                modifier = Modifier,
                                fontSize = 14.sp,
                                fontFamily = FontFamily(Font(R.font.montserrat)),
                                fontWeight = FontWeight.Bold,
                                color = viewModel.settings.colorScheme.onSecondaryContainer.copy(
                                    0.5f
                                )
                            )

                        }
                    }

                    Spacer(
                        modifier = Modifier
                            .padding(8.dp, 16.dp)
                            .fillMaxWidth()
                            .height(1.dp)
                            .background(viewModel.settings.colorScheme.outline.copy(0.25f))
                    )

                    Row(
                        Modifier
                            .padding(22.dp, 0.dp)
                            .clickable {
                                viewModel.setUri(GITHUB_REPO_ISSUE_URL)
                                viewModel.showDialog(DialogState.OpenLink)
                            })
                    {
                        Icon(
                            Icons.Outlined.BugReport,
                            null,
                            tint = contentColorFor(viewModel.settings.colorScheme.tertiaryContainer),
                            modifier = Modifier
                                .align(CenterVertically)
                                .size(24.dp)
                        )
                        Spacer(modifier = Modifier.size(12.dp))
                        Text(
                            stringResource(R.string.error_report),
                            textAlign = TextAlign.Start,
                            modifier = Modifier
                                .fillMaxWidth()
                                .align(CenterVertically),
                            fontSize = 16.sp,
                            fontFamily = FontFamily(Font(R.font.montserrat))
                        )

                    }

                    Spacer(modifier = Modifier.size(16.dp))
                }
            }


        }

        item {

            Spacer(modifier = Modifier.size(8.dp))

            ItemCard(
                backgroundColor = viewModel.settings.colorScheme.tertiaryContainer.copy(if (!viewModel.settings.isThemeInDarkMode()) .6f else 1f),
                borderRadius = 22.dp,
                borderColor = if (viewModel.settings.isOutlineElements()) {
                    viewModel.settings.colorScheme.outline
                } else {
                    Color.Transparent
                }
            ) {
                Column {
                    Spacer(modifier = Modifier.size(16.dp))
                    Row(Modifier.padding(22.dp, 0.dp).clickable {
                        viewModel.setUri(GITHUB_PROFILE_URL)
                        viewModel.showDialog(DialogState.OpenLink)
                    }) {
                        Picture(
                            modifier = Modifier
                                .size(48.dp)
                                .border(
                                    1.dp,
                                    SolidColor(viewModel.settings.colorScheme.outline),
                                    RoundedCornerShape(16.dp)
                                )
                                .align(CenterVertically),
                            imageURL = AUTHOR_PICTURE_URL,
                            contentDescription = null
                        )
                        Spacer(modifier = Modifier.size(12.dp))
                        Column(Modifier.align(CenterVertically)) {
                            Text(
                                AUTHOR,
                                textAlign = TextAlign.Start,
                                modifier = Modifier.fillMaxWidth(),
                                fontSize = 16.sp,
                                fontFamily = FontFamily(Font(R.font.montserrat))
                            )
                            Row(
                                modifier = Modifier.align(Start)
                            ) {
                                Text(
                                    "Github ",
                                    modifier = Modifier,
                                    fontSize = 14.sp,
                                    fontFamily = FontFamily(Font(R.font.montserrat)),
                                    fontWeight = FontWeight.Bold,
                                    color = viewModel.settings.colorScheme.onSecondaryContainer.copy(
                                        0.5f
                                    )
                                )
                                Text(
                                    AUTHOR_NICKNAME,
                                    modifier = Modifier,
                                    fontSize = 14.sp,
                                    fontFamily = FontFamily(Font(R.font.montserrat)),
                                    color = viewModel.settings.colorScheme.onSecondaryContainer.copy(
                                        0.5f
                                    )
                                )
                            }
                        }
                    }
                    Spacer(modifier = Modifier.size(16.dp))
                }
            }

            Spacer(modifier = Modifier.size(16.dp))


        }
    }

    when (viewModel.dialogState) {
        is DialogState.OpenLink -> OpenLinkDialog(
            onDismiss = { viewModel.hideDialog() },
            onConfirm = {
                uriHandler.openUri(viewModel.uri!!)
                viewModel.hideDialog()
            }
        )

        else -> {}
    }
}