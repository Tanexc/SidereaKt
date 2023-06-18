package ru.tanec.siderakt.presentation.catalog.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.tanec.siderakt.presentation.ui.theme.Typography

@Composable
fun CatalogSearchBar(
    modifier: Modifier = Modifier,
    backgroundColor: Color,
    fontColor: Color,
    onSearchChange: (String) -> Unit,
    onActiveChange: () -> Unit
) {

    val textFieldValue: MutableState<String> = remember { mutableStateOf("") }

    Row(
        modifier = modifier
            .background(
                backgroundColor,
                RoundedCornerShape(50)
            )
    ) {
        Column(
            modifier = Modifier
                .align(CenterVertically)
                .wrapContentSize()
                .padding(8.dp)
        ) {
            Icon(Icons.Outlined.Search, null)
        }

        Column {
            BasicTextField(
                value = textFieldValue.value,
                onValueChange = {
                    textFieldValue.value = it
                    onSearchChange(it)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                enabled = true,
                textStyle = Typography.bodyLarge.copy(color = fontColor),
                cursorBrush = SolidColor(fontColor),
                decorationBox = { innerTextField ->
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .align(CenterVertically)
                                .padding(4.dp)
                        ) {
                            innerTextField()
                        }
                    }
                }
            )
        }


    }
}