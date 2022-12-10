package com.narvatov.planthelper.ui.screen.settings

import android.app.TimePickerDialog
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.util.*

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TimePickerButton(
    text: String,
    onTimeChanged: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    CompositionLocalProvider(
        LocalMinimumTouchTargetEnforcement provides false,
    ) {
        OutlinedButton(
            onClick = {
                val calendar = Calendar.getInstance()
                val calendarHour = calendar[Calendar.HOUR_OF_DAY]
                val calendarMinute = calendar[Calendar.MINUTE]

                TimePickerDialog(
                    context,
                    { _, selectedHour: Int, selectedMinute: Int ->
                        val hourString = selectedHour.toString()
                        val minuteString = selectedHour.toString()

                        val hour = if (hourString.length == 1) "0$hourString"
                        else hourString

                        val minute = if (minuteString.length == 1) "0$minuteString"
                        else minuteString

                        //TODO add am/pm
                        onTimeChanged("$hour:$minute")
                    },
                    calendarHour,
                    calendarMinute,
                    //TODO change to selected telephone setting
                    true,
                ).apply {
                    show()
                }
            },
            shape = RoundedCornerShape(20.dp),
            contentPadding = PaddingValues(),
            modifier = modifier.defaultMinSize(1.dp, 1.dp),
        ) {
            Row(modifier = Modifier.padding(start = 12.dp, end = 2.dp).padding(vertical = 4.dp)) {
                Text(
                    text = text,
                    fontSize = 16.sp,
                )

                Icon(
                    imageVector = Icons.Rounded.ArrowDropDown,
                    contentDescription = null,
                )
            }
        }
    }
}