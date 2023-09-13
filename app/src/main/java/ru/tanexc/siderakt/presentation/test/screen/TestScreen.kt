package ru.tanexc.siderakt.presentation.test.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.zIndex
import com.gigamole.composeshadowsplus.common.ShadowsPlusType
import com.gigamole.composeshadowsplus.common.shadowsPlus
import dev.olshevski.navigation.reimagined.hilt.hiltViewModel
import ru.tanexc.siderakt.R
import ru.tanexc.siderakt.core.util.state.DialogState
import ru.tanexc.siderakt.core.util.state.TestState
import ru.tanexc.siderakt.presentation.test.components.TestCard
import ru.tanexc.siderakt.presentation.test.components.TestConfig
import ru.tanexc.siderakt.presentation.test.components.TestResult
import ru.tanexc.siderakt.presentation.test.viewModel.TestViewModel
import ru.tanexc.siderakt.presentation.utils.widgets.ItemCard
import ru.tanexc.siderakt.presentation.utils.widgets.dialogs.EndTestDialog
import ru.tanexc.siderakt.presentation.utils.widgets.dialogs.TestInfoDialog

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun TestScreen(
    modifier: Modifier = Modifier
) {
    val viewModel: TestViewModel = hiltViewModel()
    var dialogState: DialogState? by remember { mutableStateOf(null) }

    when (viewModel.testState) {
        is TestState.NotStarted -> TestConfig(modifier.fillMaxSize(), viewModel)

        is TestState.Started -> Box(
            modifier = Modifier
                .fillMaxSize()
                .background(viewModel.settingsController.colorScheme.surface)
                .zIndex(10f)
                .navigationBarsPadding()
        ) {

            val testLazyListState = rememberLazyListState()
            Column(Modifier.fillMaxSize()) {
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
                        Modifier.shadowsPlus(type= ShadowsPlusType.SoftLayer, spread = 2.dp)
                    },
                    colors = TopAppBarDefaults.mediumTopAppBarColors(
                        containerColor = viewModel.settingsController.colorScheme.surfaceColorAtElevation(
                            1.dp
                        )
                    ),
                    title = {
                        if (viewModel.enableTimer) {
                            Text(
                                text = "${viewModel.timerTime / 60}".padStart(
                                    2,
                                    '0'
                                ) + ":" + "${viewModel.timerTime % 60}".padStart(2, '0')
                            )
                        }
                    },
                    navigationIcon = {
                        IconButton(onClick = { dialogState = DialogState.FinishTest }, modifier = Modifier.padding(8.dp)) {
                            Icon(Icons.Outlined.ArrowBack, null)
                        }
                    },
                    actions = {
                        IconButton(onClick = { dialogState = DialogState.TestInfo }, modifier = Modifier.padding(8.dp)) {
                            Icon(Icons.Outlined.Info, null)
                        }
                    }
                )
                Spacer(Modifier.size(16.dp))
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.Top,
                    flingBehavior = rememberSnapFlingBehavior(testLazyListState),
                    state = testLazyListState
                ) {

                    viewModel.testData?.let { items ->
                        items(items) {
                            TestCard(
                                modifier = Modifier
                                    .fillParentMaxWidth()
                                    .padding(16.dp, 0.dp)
                                    .background(
                                        viewModel.settingsController.colorScheme.tertiaryContainer.copy(
                                            if (!viewModel.settingsController.isThemeInDarkMode()) .6f else 1f
                                        ),
                                        RoundedCornerShape(16.dp)
                                    )
                                    .border(
                                        0.dp,
                                        SolidColor(Color.Transparent),
                                        RoundedCornerShape(16.dp)
                                    ),
                                item = it,
                                setAnswer = { answer ->
                                    viewModel.setItemAnswer(answer, items.indexOf(it))
                                }
                            )
                        }
                    }
                }

                Box(
                    Modifier
                        .fillMaxSize()
                        .weight(1f)) {
                    ItemCard(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                            .padding(16.dp)
                            .align(Alignment.Center),
                        borderRadius = 16.dp,
                        backgroundColor = viewModel.settingsController.colorScheme.tertiaryContainer.copy(if (!viewModel.settingsController.isThemeInDarkMode()) .6f else 1f)
                    ) {

                        Column(
                            modifier = Modifier
                                .padding(16.dp)
                                .fillMaxHeight(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.SpaceBetween
                        ) {
                            Row {
                                Text(
                                    stringResource(R.string.answers_given), modifier = Modifier
                                        .fillMaxWidth()
                                        .weight(1f)
                                )
                                Text("${viewModel.countOfAnswer}")
                            }
                            FilledTonalButton(onClick = { viewModel.endTest() }) {
                                Row {
                                    Text(stringResource(R.string.end_test), modifier = Modifier.align(CenterVertically))
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Icon(Icons.Filled.Check, null)
                                }
                            }
                        }


                    }
                }
            }

        }

        is TestState.Ended -> TestResult(
            modifier.fillMaxSize(),
            answerGiven = viewModel.countOfAnswer,
            items = viewModel.testData?: emptyList(),
            cardColor = viewModel.settingsController.colorScheme.tertiaryContainer.copy(if (!viewModel.settingsController.isThemeInDarkMode()) .6f else 1f),
            showInfoDialog = { dialogState = DialogState.TestInfo },
            surfaceColor = viewModel.settingsController.colorScheme.surface,
            onCloseTest = { viewModel.closeTest() }

        )

    }

    when(dialogState) {
        is DialogState.TestInfo -> TestInfoDialog(onConfirm = { dialogState = null })
        is DialogState.FinishTest -> EndTestDialog(onDismiss = { dialogState = null }) {
            viewModel.endTest()
            dialogState = null
        }
        else -> {}
    }

}

