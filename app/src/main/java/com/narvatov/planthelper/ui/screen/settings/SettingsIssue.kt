package com.narvatov.planthelper.ui.screen.settings

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.narvatov.planthelper.R
import com.narvatov.planthelper.ui.LargePrimaryButton
import com.narvatov.planthelper.ui.theme.RegularBlack
import com.narvatov.planthelper.ui.theme.RegularGrey
import com.narvatov.planthelper.ui.theme.Shapes

@Composable
fun SettingsIssues() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = stringResource(R.string.found_issue),
            fontSize = 18.sp,
            fontWeight = FontWeight.Normal,
            color = RegularBlack,
            modifier = Modifier.padding(top = 20.dp)
        )

        var reportMessage by remember { mutableStateOf("") }

        Card(
            shape = Shapes.large,
            border = BorderStroke(
                width = 1.dp,
                color = Color(0xFFCDCDCD)
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp),
        ) {
            Box {
                BasicTextField(
                    value = reportMessage,
                    onValueChange = { reportMessage = it },
                    textStyle = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal,
                        color = RegularGrey,
                        fontStyle = FontStyle.Normal,
                    ),
                    decorationBox = @Composable { innerTextField ->
                        Box(
                            modifier = Modifier
                                .padding(
                                    vertical = 14.dp,
                                    horizontal = 20.dp
                                )
                        ) {
                            if (reportMessage.isBlank()) {
                                Text(
                                    text = stringResource(R.string.enter_text),
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Normal,
                                    color = RegularGrey,
                                    fontStyle = FontStyle.Italic,
                                )
                            }

                            innerTextField()
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(220.dp),
                )
            }
        }

        LargePrimaryButton(
            text = stringResource(R.string.send),
            modifier = Modifier.padding(top = 20.dp),
            onClick = {
                reportMessage = ""
//                navigate(Purchase)
            }
        )
    }
}