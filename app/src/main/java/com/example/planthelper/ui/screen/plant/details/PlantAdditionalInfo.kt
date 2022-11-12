package com.example.planthelper.ui.screen.plant.details

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.planthelper.R
import com.example.planthelper.models.data.local.Plant
import com.example.planthelper.utils.GenericCallback

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
            ) {
                Text(
                    text = stringResource(R.string.edit),
                    fontSize = 14.sp,
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