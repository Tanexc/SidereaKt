package ru.tanexc.siderakt.presentation.test.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.HorizontalRule
import androidx.compose.material.icons.filled.Start
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Timer
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MultiChoiceSegmentedButtonRow
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import ru.tanexc.siderakt.R
import ru.tanexc.siderakt.core.util.state.TimerTimeState
import ru.tanexc.siderakt.presentation.test.viewModel.TestViewModel
import ru.tanexc.siderakt.presentation.ui.theme.Typography
import ru.tanexc.siderakt.presentation.utils.widgets.ItemCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TestConfig(
    modifier: Modifier,
    viewModel: TestViewModel
) {

    var notLearned: Boolean by remember { mutableStateOf(false) }
    var north: Boolean by remember { mutableStateOf(false) }
    var south: Boolean by remember { mutableStateOf(false) }
    var equatorial: Boolean by remember { mutableStateOf(false) }
    var count: Int by remember { mutableIntStateOf(0) }
    val maxCount =
        (if (north) 28 - (if (notLearned) viewModel.settingsController.learnedNorth() else 0) else 0) +
                (if (south) 45 - (if (notLearned) viewModel.settingsController.learnedSouth() else 0) else 0) +
                (if (equatorial) 15 - (if (notLearned) viewModel.settingsController.learnedEquatorial() else 0) else 0)

    var timeState: TimerTimeState by remember { mutableStateOf(TimerTimeState.Short) }
    
    LaunchedEffect(maxCount) {
        if (count > maxCount) {
            count = maxCount
        }
    }

    Box(modifier) {

        Column {
            ItemCard(
                Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                borderColor = viewModel.settingsController.colorScheme.outline,
                backgroundColor = viewModel.settingsController.colorScheme.secondaryContainer.copy(
                    0.3f
                )
            ) {
                Column(Modifier.padding(8.dp, 8.dp)) {
                    Row {
                        Checkbox(checked = notLearned, onCheckedChange = {
                            notLearned = !notLearned
                        })

                        Text(
                            stringResource(R.string.not_learned), modifier = Modifier.align(
                                Alignment.CenterVertically
                            )
                        )
                    }

                    Row {
                        Checkbox(
                            enabled = if (notLearned) viewModel.settingsController.learnedNorth() < 28 else true,
                            checked = north,
                            onCheckedChange = {
                                north = !north
                            }
                        )

                        Text(
                            stringResource(R.string.north),
                            modifier = Modifier.align(Alignment.CenterVertically)
                        )
                    }

                    Row {
                        Checkbox(
                            enabled = if (notLearned) viewModel.settingsController.learnedSouth() < 45 else true,
                            checked = south,
                            onCheckedChange = {
                                south = !south
                            }
                        )

                        Text(
                            stringResource(R.string.south),
                            modifier = Modifier.align(Alignment.CenterVertically)
                        )
                    }

                    Row {
                        Checkbox(
                            enabled = if (notLearned) viewModel.settingsController.learnedEquatorial() < 15 else true,
                            checked = equatorial,
                            onCheckedChange = {
                                equatorial = !equatorial
                            }
                        )

                        Text(
                            stringResource(R.string.equatorials), modifier = Modifier.align(
                                Alignment.CenterVertically
                            )
                        )
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        FilledIconButton(
                            onClick = { count -= 1 },
                            enabled = count > 0,
                            modifier = Modifier.size(56.dp),
                            shape = RoundedCornerShape(16.dp)
                        ) {
                            Icon(Icons.Filled.HorizontalRule, null)
                        }

                        Text(
                            text = count.toString(),
                            modifier = Modifier
                                .padding(4.dp)
                                .width(56.dp)
                                .align(Alignment.CenterVertically),
                            textAlign = TextAlign.Center,
                            style = Typography.titleLarge
                        )

                        FilledIconButton(
                            onClick = { count += 1 },
                            enabled = count < maxCount,
                            modifier = Modifier.size(56.dp),
                            shape = RoundedCornerShape(16.dp)
                        ) {
                            Icon(Icons.Filled.Add, null)
                        }
                    }

                    Spacer(modifier = Modifier.size(24.dp))

                    Row {
                        Checkbox(
                            checked = viewModel.enableTimer,
                            onCheckedChange = {
                                viewModel.updateEnableTimer()
                            }
                        )

                        Text(
                            stringResource(R.string.enable_timer),
                            modifier = Modifier
                                .align(
                                    Alignment.CenterVertically
                                )
                                .weight(1f)
                        )

                        IconButton(onClick = { viewModel.showSnackBar() }) {
                            Icon(Icons.Outlined.Info, null)
                        }
                    }
                    
                    AnimatedVisibility(visible = viewModel.enableTimer, enter = expandVertically(), exit = shrinkVertically()) {
                        SingleChoiceSegmentedButtonRow(modifier = Modifier.border(1.dp, brush = SolidColor(viewModel.settingsController.colorScheme.outline), shape = RoundedCornerShape(50)).clip(RoundedCornerShape(50)).fillMaxWidth()) {
                            for (state: TimerTimeState in listOf(TimerTimeState.Short, TimerTimeState.SemiShort, TimerTimeState.Medium, TimerTimeState.Large)) {
                                SegmentedButton(
                                    selected = timeState == state,
                                    onClick = { timeState = state }
                                ) {
                                    Text(text = "${state.time / 60} ${stringResource(R.string.minutes)}")
                                }
                            }
                        }
                    }
                    
                    Row {
                        
                    }
                }
            }

            Spacer(modifier = Modifier.size(24.dp))

            OutlinedButton(
                enabled = count != 0,
                onClick = { viewModel.startTest(count, notLearned, north, south, equatorial, timeState.time) },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text(stringResource(R.string.start), modifier = Modifier.padding(4.dp, 0.dp))
                Icon(
                    imageVector = Icons.Filled.Start,
                    contentDescription = null
                )
            }
        }

        AnimatedVisibility(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(8.dp),
            visible = viewModel.isSnackBarVisible,
            enter = slideInVertically { it },
            exit = slideOutVertically { it }
        ) {
            Snackbar {
                Row {
                    Icon(
                        imageVector = Icons.Outlined.Info,
                        contentDescription = null
                    )
                    Text(
                        stringResource(R.string.timer_info),
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .padding(4.dp)
                            .fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }

}