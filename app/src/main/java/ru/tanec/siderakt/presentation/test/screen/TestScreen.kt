package ru.tanec.siderakt.presentation.test.screen

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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import dev.olshevski.navigation.reimagined.hilt.hiltViewModel
import ru.tanec.siderakt.R
import ru.tanec.siderakt.core.util.state.DialogState
import ru.tanec.siderakt.core.util.state.TestState
import ru.tanec.siderakt.presentation.test.components.TestCard
import ru.tanec.siderakt.presentation.test.components.TestConfig
import ru.tanec.siderakt.presentation.test.components.TestResult
import ru.tanec.siderakt.presentation.test.viewModel.TestViewModel
import ru.tanec.siderakt.presentation.utils.widgets.ItemCard
import ru.tanec.siderakt.presentation.utils.widgets.dialogs.TestInfoDialog

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TestScreen(
    modifier: Modifier = Modifier
) {
    val viewModel: TestViewModel = hiltViewModel()
    var countOfAnswer: Int by remember { mutableIntStateOf(0) }
    var dialogState: DialogState? by remember { mutableStateOf(null) }

    when (viewModel.testState) {
        is TestState.NotStarted -> TestConfig(modifier.fillMaxSize(), viewModel)

        is TestState.Started -> Box(
            modifier = Modifier
                .fillMaxSize()
                .background(viewModel.settngsController.colorScheme.surface)
                .zIndex(10f)
        ) {

            val testLazyListState = rememberLazyListState()


            Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier
                .fillMaxWidth()
                .zIndex(11f)) {
                IconButton(onClick = { viewModel.closeTest() }, modifier = Modifier.padding(8.dp)) {
                    Icon(Icons.Outlined.ArrowBack, null)
                }
                IconButton(onClick = { dialogState = DialogState.TestInfo }, modifier = Modifier.padding(8.dp)) {
                    Icon(Icons.Outlined.Info, null)
                }
            }



            Column(Modifier.fillMaxSize()) {
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
                                    .padding(16.dp, 64.dp, 16.dp, 0.dp)
                                    .background(
                                        viewModel.settngsController.colorScheme.tertiaryContainer,
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
                                    if (it.answer == null) {
                                        countOfAnswer += 1
                                    }
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
                        backgroundColor = viewModel.settngsController.colorScheme.tertiaryContainer
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
                                Text("$countOfAnswer")
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
            answerGiven = countOfAnswer,
            items = viewModel.testData?: emptyList(),
            cardColor = viewModel.settngsController.colorScheme.tertiaryContainer,
            showInfoDialog = { dialogState = DialogState.TestInfo },
            surfaceColor = viewModel.settngsController.colorScheme.surface,
            onCloseTest = { viewModel.closeTest() }

        )

    }

    when(dialogState) {
        is DialogState.TestInfo -> TestInfoDialog(onConfirm = { dialogState = null })
        else -> {}
    }

}

