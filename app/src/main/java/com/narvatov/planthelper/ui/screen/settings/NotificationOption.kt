package com.narvatov.planthelper.ui.screen.settings

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.narvatov.planthelper.ui.WeightedSpacer
import com.narvatov.planthelper.ui.theme.RegularBlack
import com.narvatov.planthelper.utils.GenericCallback
import vn.luongvo.widget.iosswitchview.SwitchView

@Composable
fun NotificationOption(
    text: String,
    enabled: Boolean,
    onCheckChanged: GenericCallback<Boolean>,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = text,
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            color = RegularBlack,
        )

        WeightedSpacer()

        AndroidView(
            modifier = Modifier
                .height(22.dp)
                .width(40.dp),
            factory = { context ->
                SwitchView(context).apply {
                    isChecked = enabled

                    setOnCheckedChangeListener { _, checked ->
                        onCheckChanged(checked)
                    }
                }
            },
        )
    }
}