package ru.tanexc.siderakt.presentation.test.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.outlined.UnfoldLess
import androidx.compose.material.icons.outlined.UnfoldMore
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import ru.tanexc.siderakt.R
import ru.tanexc.siderakt.domain.model.Constellation
import ru.tanexc.siderakt.domain.model.TestItem
import ru.tanexc.siderakt.presentation.utils.widgets.Picture


@Composable
fun TestCard(
    modifier: Modifier,
    item: TestItem,
    showAnswer: Boolean = false,
    wrongAnswerRadioColor: Color = Color.Red,
    setAnswer: (Constellation) -> Unit
) {

    var testItem: TestItem by remember { mutableStateOf(item.copy()) }

    var isImageCollapsed: Boolean by remember { mutableStateOf(true) }

    Column(modifier) {

        Box(
            Modifier
                .wrapContentHeight()
                .fillMaxWidth()
        ) {

            AnimatedContent(isImageCollapsed, label = "") { state ->
                Picture(
                    modifier = if (!state) {
                        Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(16.dp))
                    } else {
                        Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(16.dp))
                            .height(256.dp)
                    },
                    imageURL = item.constellation.imageURL,
                    imageCache = item.constellation.imageCache,
                    contentDescription = null
                )
            }


            FilledIconButton(
                onClick = { isImageCollapsed = !isImageCollapsed },
                modifier = Modifier
                    .size(48.dp)
                    .align(Alignment.TopEnd)
                    .padding(10.dp)
            ) {
                when (isImageCollapsed) {
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

        Spacer(modifier = Modifier.size(12.dp))

        for (it in item.answers) {
            Row(Modifier.padding(8.dp, 2.dp)) {
                RadioButton(
                    enabled = if (showAnswer) { testItem.answer?.id == it.id } else {true},
                    selected = testItem.answer?.id == it.id,
                    onClick = {
                        testItem = testItem.copy(answer = it)
                        setAnswer(it)
                    },
                    colors = RadioButtonDefaults.colors(
                        selectedColor = if (item.answer?.id == it.id && showAnswer && it.id != item.constellation.id) {
                            wrongAnswerRadioColor
                        } else if (item.answer?.id == it.id && showAnswer) {
                            Color.Green
                        } else {
                            RadioButtonDefaults.colors().selectedColor
                        }
                    )
                )
                Spacer(modifier = Modifier.size(8.dp))
                Text(
                    text = it.title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .weight(1f)
                        .align(CenterVertically)
                        .clickable(
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() },
                            onClick = {
                                testItem = testItem.copy(answer = it)
                                setAnswer(it)
                            }
                        )
                )
            }
        }

        if (showAnswer) {
            Spacer(modifier = Modifier.size(24.dp))
            Text(
                text = stringResource(R.string.correct_answer) + " " + item.constellation.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
        }
    }
}