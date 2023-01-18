package com.narvatov.planthelper.ui.screen.plant.create

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.narvatov.planthelper.R
import com.narvatov.planthelper.ui.theme.RegularGrey
import com.narvatov.planthelper.ui.theme.Shapes
import com.narvatov.planthelper.utils.UnitCallback

@Composable
fun OutlinedPlantTextField(
    text: String = "",
    label: String,
    errorLabel: String = label,
    singleLine: Boolean = false,
    enabled: Boolean = true,
    error: Boolean = false,
    isEdit: Boolean = false,
    onClick: UnitCallback? = null,
    onValueChanged: (String) -> Unit = {},
    modifier: Modifier = Modifier,
    content: (@Composable ColumnScope.(@Composable () -> Unit) -> Unit)? = null,
) = OutlinedPlantTextField(
    text = mutableStateOf(text),
    label = label,
    errorLabel = errorLabel,
    singleLine = singleLine,
    enabled = enabled,
    error = error,
    isEdit = isEdit,
    onClick = onClick,
    onValueChanged = onValueChanged,
    modifier = modifier,
    content = content,
)

@Composable
fun OutlinedPlantTextField(
    text: MutableState<String>,
    label: String,
    errorLabel: String = label,
    singleLine: Boolean = false,
    enabled: Boolean = true,
    error: Boolean = false,
    isEdit: Boolean = false,
    onClick: UnitCallback? = null,
    onValueChanged: (String) -> Unit = {},
    modifier: Modifier = Modifier,
    content: (@Composable ColumnScope.(@Composable () -> Unit) -> Unit)? = null,
) {
    Card(
        shape = Shapes.large,
        border = BorderStroke(
            1.dp,
            if (error) Color(0xFFCC1111) else Color(0xFFCDCDCD)
        ),
        modifier = modifier.fillMaxWidth(),
    ) {
        Box {
            val onClickModifier = if (onClick == null) Modifier
            else Modifier.clickable { onClick() }

            BasicTextField(
                value = text.value,
                onValueChange = {
                    text.value = it
                    onValueChanged(it)
                },
                textStyle = TextStyle(fontSize = 16.sp),
                singleLine = singleLine,
                enabled = enabled,
                decorationBox = @Composable { innerTextField ->
                    Column(modifier = Modifier.padding(vertical = 4.dp, horizontal = 18.dp)) {

                        //Label
                        Text(
                            text = if (error) errorLabel else label,
                            fontSize = 14.sp,
                            color = if (error) Color(0xFFCC1111) else RegularGrey,
                            modifier = Modifier.padding(bottom = 4.dp)
                        )

                        if (content == null) {
                            //Input field
                            innerTextField()
                        } else {
                            //Custom content
                            content(innerTextField)
                        }
                    }
                },
                //If you just set disabled OnClick it breaks inner tap logic
                modifier = onClickModifier,
            )

            if (isEdit) {
                Image(
                    painter = painterResource(R.drawable.ic_edit_small),
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .padding(end = 20.dp)
                )
            }
        }
    }
}