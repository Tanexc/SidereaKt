package ru.tanec.siderakt.presentation.settings

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material.icons.outlined.Link
import androidx.compose.material.icons.outlined.QuestionMark
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Alignment.Companion.Start
import androidx.compose.ui.Modifier
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
import ru.tanec.siderakt.BuildConfig
import ru.tanec.siderakt.R
import ru.tanec.siderakt.core.util.AUTHOR
import ru.tanec.siderakt.core.util.AUTHOR_NICKNAME
import ru.tanec.siderakt.core.util.AUTHOR_PICTURE_URL
import ru.tanec.siderakt.core.util.CONSTELLATION_DATA_SOURCE_URL
import ru.tanec.siderakt.core.util.DialogState
import ru.tanec.siderakt.core.util.GITHUB_PROFILE_URL
import ru.tanec.siderakt.core.util.GITHUB_REPO_ISSUE_URL
import ru.tanec.siderakt.core.util.GITHUB_REPO_URL
import ru.tanec.siderakt.core.util.SOURCE_SITE
import ru.tanec.siderakt.core.util.Theme
import ru.tanec.siderakt.presentation.main.components.Picture
import ru.tanec.siderakt.presentation.main.components.SelectButtonGroup
import ru.tanec.siderakt.presentation.main.components.SelectButtonItem
import ru.tanec.siderakt.presentation.settings.components.SettingsCard
import ru.tanec.siderakt.presentation.settings.viewModel.SettingsViewModel
import ru.tanec.siderakt.presentation.utils.dialogs.ExitDialog
import ru.tanec.siderakt.presentation.utils.dialogs.OpenLinkDialog

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier
) {
    val viewModel: SettingsViewModel = hiltViewModel()
    val uriHandler = LocalUriHandler.current

    Column(modifier = modifier.padding(10.dp, 0.dp)) {
        LazyColumn {
            item {
                Column {
                    Text(
                        stringResource(R.string.stats),
                        textAlign = TextAlign.Start,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(4.dp),
                        fontWeight = FontWeight.ExtraLight,
                        fontSize = 22.sp,
                    )
                    SettingsCard(
                        modifier = Modifier
                            .height(96.dp),
                        borderColor = viewModel.settings.colorScheme.outline,
                        borderRadius = 16.dp,
                        borderWidth = 1.dp,
                        backgroundColor = viewModel.settings.colorScheme.secondaryContainer.copy(
                            0.3f
                        )
                    ) {
                        Column(modifier = Modifier.padding(12.dp)) {
                            Text(
                                stringResource(R.string.constellations),
                                textAlign = TextAlign.Center,
                                modifier = Modifier.fillMaxWidth(),
                                fontSize = 16.sp,
                                fontFamily = FontFamily(Font(R.font.montserrat))
                            )
                            Text(
                                "${viewModel.settings.learnedNorth() + viewModel.settings.learnedSouth()} " + stringResource(
                                    R.string.of
                                ) + " 88",
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(2.dp),
                                fontSize = 36.sp
                            )
                        }
                    }
                    Row {
                        SettingsCard(
                            modifier = Modifier
                                .height(96.dp)
                                .fillMaxWidth(0.5f),
                            borderColor = viewModel.settings.colorScheme.outline,
                            borderRadius = 16.dp,
                            borderWidth = 1.dp,
                            backgroundColor = viewModel.settings.colorScheme.secondaryContainer.copy(
                                0.3f
                            )
                        ) {
                            Column(modifier = Modifier.padding(12.dp)) {
                                Text(
                                    stringResource(R.string.north),
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(2.dp),
                                    fontSize = 16.sp,
                                    fontFamily = FontFamily(Font(R.font.montserrat))
                                )
                                Text(
                                    "${viewModel.settings.learnedNorth()}",
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(4.dp),
                                    fontSize = 24.sp
                                )
                            }
                        }
                        SettingsCard(
                            modifier = Modifier
                                .height(96.dp)
                                .fillMaxWidth(),
                            borderColor = viewModel.settings.colorScheme.outline,
                            borderRadius = 16.dp,
                            borderWidth = 1.dp,
                            backgroundColor = viewModel.settings.colorScheme.secondaryContainer.copy(
                                0.3f
                            )
                        )
                        {
                            Column(modifier = Modifier.padding(12.dp)) {
                                Text(
                                    stringResource(R.string.south),
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(2.dp),
                                    fontSize = 16.sp,
                                    fontFamily = FontFamily(Font(R.font.montserrat)),
                                )
                                Text(
                                    "${viewModel.settings.learnedSouth()}",
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(4.dp),
                                    fontSize = 24.sp
                                )
                            }
                        }
                    }
                }
            }

            item {
                val items = listOf(
                    SelectButtonItem(
                        title = stringResource(R.string.app_default),
                        onSelected = {
                            viewModel.changeTheme(Theme.Default())
                        }
                    ),
                    SelectButtonItem(
                        title = stringResource(R.string.blue),
                        onSelected = {
                            viewModel.changeTheme(Theme.Blue())
                        }
                    ),
                    SelectButtonItem(
                        title = stringResource(R.string.green),
                        onSelected = {
                            viewModel.changeTheme(Theme.Green())
                        }
                    )
                )
                Column {
                    Text(
                        stringResource(R.string.settings),
                        textAlign = TextAlign.Start,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(4.dp),
                        fontWeight = FontWeight.ExtraLight,
                        fontSize = 22.sp,
                    )

                    SettingsCard(
                        modifier = Modifier
                            .height(108.dp),
                        borderColor = viewModel.settings.colorScheme.outline,
                        borderRadius = 16.dp,
                        borderWidth = 1.dp,
                        backgroundColor = viewModel.settings.colorScheme.secondaryContainer.copy(
                            0.3f
                        )
                    ) {
                        Column(modifier = Modifier.padding(12.dp)) {
                            Text(
                                stringResource(R.string.style),
                                textAlign = TextAlign.Center,
                                modifier = Modifier.fillMaxWidth(),
                                fontSize = 16.sp,
                                fontFamily = FontFamily(Font(R.font.montserrat))
                            )

                            SelectButtonGroup(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp),
                                selectedItemIndex = viewModel.settings.theme()?.id ?: 0,
                                borderColor = viewModel.settings.colorScheme.outline,
                                selectedColor = viewModel.settings.colorScheme.secondaryContainer,
                                items = items,
                                fontSize = 16.dp
                            )
                        }
                    }
                    SettingsCard(
                        modifier = Modifier
                            .wrapContentHeight(),
                        borderColor = viewModel.settings.colorScheme.outline,
                        borderRadius = 16.dp,
                        borderWidth = 1.dp,
                        backgroundColor = viewModel.settings.colorScheme.secondaryContainer.copy(
                            0.3f
                        )
                    ) {
                        Row(
                            modifier = Modifier
                                .padding(12.dp)
                                .wrapContentHeight()
                                .fillMaxWidth()
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth(0.5f)
                                    .wrapContentHeight()
                                    .align(CenterVertically)
                            ) {
                                Text(
                                    stringResource(R.string.dark_theme),
                                    textAlign = TextAlign.Start,
                                    modifier = Modifier
                                        .align(Start)
                                        .padding(8.dp),
                                    fontSize = 16.sp,
                                    fontFamily = FontFamily(Font(R.font.montserrat))
                                )
                            }
                            Column(modifier = Modifier.fillMaxWidth()) {
                                Switch(
                                    checked = viewModel.settings.isThemeInDarkMode(),
                                    onCheckedChange = { viewModel.changeUseDarkTheme() },
                                    modifier = Modifier
                                        .padding(8.dp)
                                        .align(Alignment.End),
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
                        }
                    }
                }
            }

            item {
                Column {
                    Text(
                        stringResource(R.string.about),
                        textAlign = TextAlign.Start,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(4.dp),
                        fontWeight = FontWeight.ExtraLight,
                        fontSize = 22.sp,
                    )

                    SettingsCard(
                        modifier = Modifier
                            .wrapContentHeight()
                            .clickable(
                                interactionSource = MutableInteractionSource(),
                                indication = null
                            ) {
                                viewModel.setUri(GITHUB_REPO_URL)
                                viewModel.showDialog(DialogState.OpenLink)
                              },
                        borderColor = viewModel.settings.colorScheme.outline,
                        borderRadius = 16.dp,
                        borderWidth = 1.dp,
                        backgroundColor = viewModel.settings.colorScheme.secondaryContainer.copy(
                            0.3f
                        ),
                    ) {
                        Row {
                            Box(
                                modifier = Modifier
                                    .size(72.dp)
                                    .padding(12.dp)
                                    .border(
                                        1.dp,
                                        SolidColor(viewModel.settings.colorScheme.outline),
                                        RoundedCornerShape(16.dp)
                                    )
                            ) {
                                Icon(
                                    painterResource(R.drawable.github),
                                    modifier = Modifier.padding(12.dp),
                                    contentDescription = null
                                )
                            }

                            Column(modifier = Modifier.padding(top = 12.dp)) {
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
                    }

                    SettingsCard(
                        modifier = Modifier
                            .wrapContentHeight()
                            .clickable(
                                interactionSource = MutableInteractionSource(),
                                indication = null
                            ) {
                                viewModel.setUri(GITHUB_PROFILE_URL)
                                viewModel.showDialog(DialogState.OpenLink)
                              },
                        borderColor = viewModel.settings.colorScheme.outline,
                        borderRadius = 16.dp,
                        borderWidth = 1.dp,
                        backgroundColor = viewModel.settings.colorScheme.secondaryContainer.copy(
                            0.3f
                        ),
                    ) {
                        Row {
                            Picture(
                                modifier = Modifier
                                    .size(72.dp)
                                    .padding(12.dp)
                                    .border(
                                        1.dp,
                                        SolidColor(viewModel.settings.colorScheme.outline),
                                        RoundedCornerShape(16.dp)
                                    ),
                                imageURL = AUTHOR_PICTURE_URL
                            )
                            Column(modifier = Modifier.padding(top = 12.dp)) {
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
                    }

                    SettingsCard(
                        modifier = Modifier
                            .wrapContentHeight()
                            .clickable(
                                interactionSource = MutableInteractionSource(),
                                indication = null
                            ) {
                                viewModel.setUri(CONSTELLATION_DATA_SOURCE_URL)
                                viewModel.showDialog(DialogState.OpenLink)
                                       },
                        borderColor = viewModel.settings.colorScheme.outline,
                        borderRadius = 16.dp,
                        borderWidth = 1.dp,
                        backgroundColor = viewModel.settings.colorScheme.secondaryContainer.copy(
                            0.3f
                        ),
                    ) {
                        Row {
                            Box(
                                modifier = Modifier
                                    .size(72.dp)
                                    .padding(12.dp)
                                    .border(
                                        1.dp,
                                        SolidColor(viewModel.settings.colorScheme.outline),
                                        RoundedCornerShape(16.dp)
                                    )
                            ) {
                                Icon(
                                    Icons.Outlined.Link,
                                    modifier = Modifier.padding(12.dp),
                                    contentDescription = null
                                )
                            }

                            Column(modifier = Modifier.padding(top = 12.dp)) {
                                Text(
                                    stringResource(R.string.data_source),
                                    textAlign = TextAlign.Start,
                                    modifier = Modifier.fillMaxWidth(),
                                    fontSize = 16.sp,
                                    fontFamily = FontFamily(Font(R.font.montserrat))
                                )
                                Row(
                                    modifier = Modifier.align(Start)
                                ) {
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
                        }
                    }

                    SettingsCard(
                        modifier = Modifier
                            .wrapContentHeight()
                            .clickable(
                                interactionSource = MutableInteractionSource(),
                                indication = null
                            ) {
                                viewModel.setUri(GITHUB_REPO_ISSUE_URL)
                                viewModel.showDialog(DialogState.OpenLink)
                              },
                        borderColor = viewModel.settings.colorScheme.outline,
                        borderRadius = 16.dp,
                        borderWidth = 1.dp,
                        backgroundColor = viewModel.settings.colorScheme.secondaryContainer.copy(
                            0.3f
                        ),
                    ) {
                        Row {
                            Box(
                                modifier = Modifier
                                    .size(72.dp)
                                    .padding(12.dp)
                                    .border(
                                        1.dp,
                                        SolidColor(viewModel.settings.colorScheme.outline),
                                        RoundedCornerShape(16.dp)
                                    )
                            ) {
                                Icon(
                                    Icons.Outlined.QuestionMark,
                                    modifier = Modifier.padding(12.dp),
                                    contentDescription = null
                                )
                            }

                            Column(modifier = Modifier.padding(top = 12.dp)) {
                                Text(
                                    stringResource(R.string.error_report),
                                    textAlign = TextAlign.Start,
                                    modifier = Modifier.fillMaxWidth(),
                                    fontSize = 16.sp,
                                    fontFamily = FontFamily(Font(R.font.montserrat))
                                )
                            }
                        }
                    }

                }
            }
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