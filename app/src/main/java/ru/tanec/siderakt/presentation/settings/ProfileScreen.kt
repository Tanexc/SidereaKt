package ru.tanec.siderakt.presentation.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.olshevski.navigation.reimagined.hilt.hiltViewModel
import ru.tanec.siderakt.R
import ru.tanec.siderakt.presentation.settings.components.SettingsCard
import ru.tanec.siderakt.presentation.settings.viewModel.SettingsViewModel

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier
) {
    val viewModel: SettingsViewModel = hiltViewModel()

    Column(modifier = modifier.padding(10.dp)) {
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
                        borderColor = viewModel.colorScheme.outline,
                        borderRadius = 16.dp,
                        borderWidth = 1.dp,
                        backgroundColor = viewModel.colorScheme.secondaryContainer.copy(0.3f)
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
                                "${viewModel.personalInfo?.learnedConstellations ?: "NA"} " + stringResource(
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
                            borderColor = viewModel.colorScheme.outline,
                            borderRadius = 16.dp,
                            borderWidth = 1.dp,
                            backgroundColor = viewModel.colorScheme.secondaryContainer.copy(0.3f)
                        ) {
                            Column(modifier = Modifier.padding(12.dp)) {
                                Text(
                                    stringResource(R.string.north),
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(2.dp),
                                    fontSize = 12.sp,
                                    fontFamily = FontFamily(Font(R.font.montserrat))
                                )
                                Text(
                                    "${viewModel.personalInfo?.learnedNorth}",
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(8.dp),
                                    fontSize = 24.sp
                                )
                            }
                        }
                        SettingsCard(
                            modifier = Modifier
                                .height(96.dp)
                                .fillMaxWidth(),
                            borderColor = viewModel.colorScheme.outline,
                            borderRadius = 16.dp,
                            borderWidth = 1.dp,
                            backgroundColor = viewModel.colorScheme.secondaryContainer.copy(0.3f)
                        )
                        {
                            Column(modifier = Modifier.padding(12.dp)) {
                                Text(
                                    stringResource(R.string.south),
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(2.dp),
                                    fontSize = 12.sp,
                                    fontFamily = FontFamily(Font(R.font.montserrat)),
                                )
                                Text(
                                    "${viewModel.personalInfo?.learnedSouth}",
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(8.dp),
                                    fontSize = 24.sp
                                )
                            }
                        }
                    }
                }
            }

            item {
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
                }
            }


        }



    }


}