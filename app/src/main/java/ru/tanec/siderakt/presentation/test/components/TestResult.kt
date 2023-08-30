package ru.tanec.siderakt.presentation.test.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
import ru.tanec.siderakt.R
import ru.tanec.siderakt.domain.model.TestItem

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TestResult(
    modifier: Modifier,
    answerGiven: Int,
    items: List<TestItem>,
    cardColor: Color,
    surfaceColor: Color,
    showInfoDialog: () -> Unit,
    onCloseTest: () -> Unit
) {

    var showAnswers: Boolean by remember { mutableStateOf(false) }

    if (showAnswers) {

        val testLazyListState = rememberLazyListState()

        Box(
            Modifier.fillMaxSize().zIndex(10f).background(surfaceColor)
        ) {

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .zIndex(11f)
            ) {
                IconButton(onClick = { showAnswers = false }, modifier = Modifier.padding(8.dp)) {
                    Icon(Icons.Outlined.ArrowBack, null)
                }
                IconButton(onClick = { showInfoDialog() }, modifier = Modifier.padding(8.dp)) {
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
                    items(items) {
                        TestCard(
                            modifier = Modifier
                                .fillParentMaxWidth()
                                .padding(16.dp, 64.dp, 16.dp, 0.dp)
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
        }


    } else {

        Column(modifier = modifier.padding(16.dp)) {
            Spacer(modifier = Modifier.size(12.dp))
            Row(Modifier.padding(8.dp, 4.dp), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(
                    stringResource(R.string.answers_given), modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                )
                Text("$answerGiven " + stringResource(R.string.of) + " ${items.size}")
            }

            Row(Modifier.padding(8.dp, 4.dp)) {
                Text(
                    stringResource(id = R.string.correct_answers_count), modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                )

                Text("${items.filter { it.answer?.id == it.constellation.id }.size}")
            }

            Row(
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier.padding(8.dp, 4.dp)
            ) {
                TextButton(
                    onClick = { showAnswers = true },
                    contentPadding = PaddingValues(0.dp)
                ) {
                    Text(
                        stringResource(R.string.check_answers),
                        modifier = Modifier.align(CenterVertically)
                    )
                }

                TextButton(
                    onClick = { onCloseTest() },
                    contentPadding = PaddingValues(0.dp)
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