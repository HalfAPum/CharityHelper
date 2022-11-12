package com.example.planthelper.ui.screen.plant.create

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.planthelper.R
import com.example.planthelper.ui.rememberSaveableString
import com.example.planthelper.ui.viewmodel.CreatePlantViewModel
import com.example.planthelper.utils.UnitCallback
import org.koin.androidx.compose.getViewModel

@Composable
fun CreatePlant(
    onPlantSaved: UnitCallback,
    onPlantTypeSearchClicked: UnitCallback,
    openCalendarClicked: UnitCallback,
    viewModel: CreatePlantViewModel = getViewModel()
) = with(viewModel) {
    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        PlantImageEditable(
            createPlantUiState = createPlantUiState,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 20.dp),
            onPhotoPicked = { saveBitmap(it) }
        )

        val plantName = rememberSaveableString(createPlantUiState.plantName)

        OutlinedPlantTextField(
            text = plantName,
            label = stringResource(R.string.name),
            singleLine = true,
            modifier = Modifier.padding(top = 20.dp),
        )

        val plantType = rememberSaveableString()

        OutlinedPlantTextField(
            text = plantType,
            label = stringResource(R.string.plant_type),
            singleLine = false,
            enabled = false,
            modifier = Modifier.padding(top = 20.dp),
            onClick = { onPlantTypeSearchClicked() }
        )

        val birthDate = createPlantUiState.shortPlantBirthDay

        OutlinedPlantTextField(
            text = mutableStateOf(birthDate),
            label = stringResource(R.string.plant_birthday),
            singleLine = true,
            enabled = false,
            modifier = Modifier.padding(top = 20.dp),
            onClick = { openCalendarClicked() }
        )

        Button(
            onClick = {
                updatePlantUiState(
                    plantName = plantName.value
                )

                savePlant()
            }
        ) {
            Text(
                text = stringResource(R.string.save),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LockedSlotPreview() {
    CreatePlant(
        {},
        {},
        {}
    )
}