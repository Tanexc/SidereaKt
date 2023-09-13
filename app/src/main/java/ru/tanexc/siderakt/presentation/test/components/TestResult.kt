package ru.tanexc.siderakt.presentation.test.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.gigamole.composeshadowsplus.common.ShadowsPlusType
import com.gigamole.composeshadowsplus.common.shadowsPlus
import dev.olshevski.navigation.reimagined.hilt.hiltViewModel
import ru.tanexc.siderakt.R
import ru.tanexc.siderakt.domain.model.TestItem
import ru.tanexc.siderakt.presentation.test.viewModel.TestViewModel

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun TestResult(
    answerGiven: Int,
    items: List<TestItem>,
    cardColor: Color,
    surfaceColor: Color,
    onCloseTest: () -> Unit
) {

    val viewModel: TestViewModel = hiltViewModel()

    var showAnswers: Boolean by remember { mutableStateOf(false) }

    if (showAnswers) {

        val testLazyListState = rememberLazyListState()

        Column(
            Modifier
                .fillMaxSize()
                .background(surfaceColor)) {

            CenterAlignedTopAppBar(
                modifier = if (viewModel.settingsController.isOutlineElements()) {
                    Modifier.drawWithContent {
                        drawContent()
                        drawRect(
                            viewModel.settingsController.colorScheme.outline,
                            topLeft = Offset(0f, this.size.height),
                            size = Size(this.size.width, density)
                        )
                    }
                } else {
                    Modifier.shadowsPlus(type = ShadowsPlusType.SoftLayer, spread = 2.dp)
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = viewModel.settingsController.colorScheme.surfaceColorAtElevation(
                        1.dp
                    )
                ),
                navigationIcon = {
                    IconButton(
                        onClick = { showAnswers = false },
                        modifier = Modifier.padding(8.dp)
                    ) {
                        Icon(Icons.AutoMirrored.Outlined.ArrowBack, null)
                    }
                },
                title = {}
            )

            Spacer(Modifier.size(16.dp))

            LazyRow(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.Top,
                    flingBehavior = rememberSnapFlingBehavior(testLazyListState),
                    state = testLazyListState
                ) {
                    items(items) {
                        TestCard(
                            modifier = Modifier
                                .fillParentMaxWidth()
                                .padding(16.dp, 0.dp)
                                .background(
                                    cardColor,
                                    RoundedCornerShape(16.dp)
                                )
                                .border(
                                    0.dp,
                                    SolidColor(Color.Transparent),
                                    RoundedCornerShape(16.dp)
                                ),
                            item = it,
                            setAnswer = {},
                            showAnswer = true
                        )
                    }
                }
            }

    } else {

        Column(modifier = Modifier
            .fillMaxSize()
            .background(surfaceColor)) {

            CenterAlignedTopAppBar(
                modifier = if (viewModel.settingsController.isOutlineElements()) {
                    Modifier.drawWithContent {
                        drawContent()
                        drawRect(
                            viewModel.settingsController.colorScheme.outline,
                            topLeft = Offset(0f, this.size.height),
                            size = Size(this.size.width, density)
                        )
                    }
                } else {
                    Modifier.shadowsPlus(type = ShadowsPlusType.SoftLayer, spread = 2.dp)
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = viewModel.settingsController.colorScheme.surfaceColorAtElevation(
                        1.dp
                    )
                ),
                navigationIcon = {
                    IconButton(
                        onClick = { onCloseTest() },
                        modifier = Modifier.padding(8.dp)
                    ) {
                        Icon(Icons.AutoMirrored.Outlined.ArrowBack, null)
                    }
                },
                title = {}
            )

            Spacer(Modifier.size(16.dp))

            Row(Modifier.padding(16.dp, 4.dp)) {
                Text(
                    stringResource(R.string.answers_given), modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                )
                Text("$answerGiven " + stringResource(R.string.of) + " ${items.size}")
            }

            Row(Modifier.padding(16.dp, 4.dp)) {
                Text(
                    stringResource(id = R.string.correct_answers_count), modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                )

                Text("${items.filter { it.answer?.id == it.constellation.id }.size}")
            }

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .padding(16.dp, 4.dp)
                    .fillMaxWidth()
            ) {
                OutlinedButton(
                    onClick = { showAnswers = true },
                    contentPadding = PaddingValues(8.dp)
                ) {
                    Text(
                        stringResource(R.string.check_answers),
                        modifier = Modifier.align(CenterVertically)
                    )
                }

                Button(
                    onClick = { onCloseTest() },
                    contentPadding = PaddingValues(8.dp)
                ) {
                    Text(
                        stringResource(R.string.close_test),
                        modifier = Modifier.align(CenterVertically)
                    )
                }
            }

        }
    }

}