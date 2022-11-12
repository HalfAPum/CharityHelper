package com.example.planthelper.ui.screen.plant.create

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.planthelper.utils.UnitCallback

@Composable
fun OutlinedPlantTextField(
    text: MutableState<String>,
    label: String,
    singleLine: Boolean = false,
    enabled: Boolean = true,
    modifier: Modifier = Modifier,
    onClick: UnitCallback? = null
) {
    Card(
        shape = RoundedCornerShape(20.dp),
        border = BorderStroke(1.dp, Color(0xFFCDCDCD)),
        modifier = modifier.fillMaxWidth(),
    ) {
        val onClickModifier = if (onClick == null) Modifier
            else Modifier.clickable { onClick() }

        BasicTextField(
            value = text.value,
            onValueChange = { text.value = it },
            textStyle = TextStyle(fontSize = 16.sp),
            singleLine = singleLine,
            enabled = enabled,
            decorationBox = @Composable { innerTextField ->
                Column(modifier = Modifier.padding(vertical = 4.dp, horizontal = 18.dp)) {
                   Text(
                       text = label,
                       fontSize = 14.sp,
                       color = Color(0xFF535353),
                       modifier = Modifier.padding(bottom = 4.dp)
                   )

                   innerTextField()
                }
            },
            //If you just set disabled OnClick it breaks inner tap logic
            modifier = onClickModifier,
        )
    }
}