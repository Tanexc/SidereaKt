package ru.tanexc.siderakt.presentation.main.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SelectButtonGroup(
    modifier: Modifier = Modifier,
    items: List<SelectButtonItem> = listOf(),
    selectedItemIndex: Int,
    borderWidth: Dp = 1.dp,
    borderColor: Color,
    selectedColor: Color,
    fontSize: Dp = 12.dp,
    fontWeight: FontWeight = FontWeight.ExtraLight
) {
    val selectedItem: MutableState<Int> = remember { mutableIntStateOf(selectedItemIndex) }
    val width: Float = 1f / items.size

    Box(
        modifier = modifier.border(
            borderWidth,
            borderColor,
            RoundedCornerShape(50)
        )
    ) {
        Row {
            items.forEach {
                Box(
                    modifier = Modifier
                        .background(
                            if (items.indexOf(it) == selectedItem.value) {
                                selectedColor.copy(0.5f)
                            } else {
                                selectedColor.copy(0f)
                            },
                            when (items.indexOf(it)) {
                                0 -> RoundedCornerShape(50, 0, 0, 50)
                                items.lastIndex -> RoundedCornerShape(0, 50, 50, 0)
                                else -> RectangleShape
                            }
                        )
                        .height(40.dp)
                        .fillMaxWidth(width / (1f - width * items.indexOf(it)))
                        .clickable(
                            interactionSource = MutableInteractionSource(),
                            indication = null
                        ) {
                            selectedItem.value = items.indexOf(it)
                            it.onSelected()
                        }
                ) {
                    Text(
                        it.title,
                        modifier = Modifier.align(Alignment.Center),
                        fontSize = 16.sp,
                        fontWeight = fontWeight
                    )
                }
                if (items.indexOf(it) != items.lastIndex) {
                    Spacer(
                        modifier = Modifier
                            .background(borderColor)
                            .fillMaxHeight()
                            .width(borderWidth)
                    )
                }
            }
        }
    }
}