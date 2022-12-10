package com.narvatov.planthelper.ui.screen.plant.create

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.halfapum.general.coroutines.launchCatching
import com.narvatov.planthelper.R
import com.narvatov.planthelper.ui.theme.LightGreyBackground
import com.narvatov.planthelper.ui.viewmodel.plant.create.CreatePlantViewModel
import com.narvatov.planthelper.utils.UnitCallback
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.getViewModel

@Composable
fun CreatePlant(
    onPlantSaved: UnitCallback,
    onPlantTypeSearchClicked: UnitCallback,
    openCalendarClicked: UnitCallback,
    viewModel: CreatePlantViewModel = getViewModel()
) = with(viewModel) {
    val scope = rememberCoroutineScope()
    scope.launchCatching {
        savePlantActionSharedFlow.collectLatest {
            onPlantSaved()
        }
    }

    Spacer(modifier = Modifier
        .fillMaxWidth()
        .height(10.dp)
        .background(LightGreyBackground)
    )

    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        PlantImageEditable(
            createPlantUiState = createPlantUiState,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 20.dp),
            onPhotoPicked = { saveBitmap(it) }
        )

        val plantName = createPlantUiState.plantName

        OutlinedPlantTextField(
            text = plantName,
            label = stringResource(R.string.name),
            errorLabel = stringResource(R.string.empty_name),
            singleLine = true,
            error = createPlantUiState.isPlantNameError,
            modifier = Modifier.padding(top = 20.dp),
            onValueChanged = { plantNameChanged(it) }
        )

        val plantType = createPlantUiState.plantType

        OutlinedPlantTextField(
            text = plantType,
            label = stringResource(R.string.plant_type),
            errorLabel = stringResource(R.string.empty_plant_type),
            singleLine = false,
            enabled = false,
            error = createPlantUiState.isPlantTypeError,
            modifier = Modifier.padding(top = 20.dp),
            onClick = { onPlantTypeSearchClicked() }
        )

        val birthDate = createPlantUiState.shortPlantBirthDay

        OutlinedPlantTextField(
            text = birthDate,
            label = stringResource(R.string.plant_birthday),
            singleLine = true,
            enabled = false,
            modifier = Modifier.padding(top = 20.dp),
            onClick = { openCalendarClicked() }
        )

        Button(
            onClick = { savePlant() },
            shape = RoundedCornerShape(20.dp),
            elevation = ButtonDefaults.elevation(),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 20.dp)
        ) {
            Text(
                text = stringResource(R.string.save),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 52.dp)
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