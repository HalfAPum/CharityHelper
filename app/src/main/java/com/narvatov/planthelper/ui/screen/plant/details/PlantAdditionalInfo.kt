package com.narvatov.planthelper.ui.screen.plant.details

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.narvatov.planthelper.R
import com.narvatov.planthelper.models.data.local.Plant
import com.narvatov.planthelper.ui.theme.AnotherGrey
import com.narvatov.planthelper.utils.GenericCallback

@Composable
fun PlantAdditionalInfo(
    plant: Plant,
    onEditClicked: GenericCallback<Plant>,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = plant.plantName,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
            )

            Spacer(modifier = Modifier.weight(1F))

            Button(
                onClick = { onEditClicked(plant) },
                shape = RoundedCornerShape(20.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = AnotherGrey)
            ) {
                Text(
                    text = stringResource(R.string.edit),
                    fontSize = 14.sp,
                    color = Color.White,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }
        }

        Text(
            text = plant.originName,
            fontSize = 16.sp,
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}