package com.narvatov.planthelper.ui.screen.plant.create

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.narvatov.planthelper.R
import com.narvatov.planthelper.ui.WeightedSpacer
import com.narvatov.planthelper.ui.navigation.Calendar
import com.narvatov.planthelper.ui.navigation.SearchPlantType
import com.narvatov.planthelper.ui.navigation.UiNavigationEventPropagator.navigate
import com.narvatov.planthelper.ui.theme.AnotherGrey
import com.narvatov.planthelper.ui.theme.PrimaryColor
import com.narvatov.planthelper.ui.theme.Shapes
import com.narvatov.planthelper.ui.viewmodel.plant.create.CreatePlantViewModel
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun CreatePlant(
    plantId: Long? = null,
) {
    val createMode = plantId == null
    val editMode = plantId != null

    val viewModel = getViewModel<CreatePlantViewModel>(parameters = { parametersOf(plantId) })

    with(viewModel) {
        Column(modifier = Modifier.padding(horizontal = 16.dp)) {
            PlantImageEditable(
                createPlantUiState = createPlantUiState,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 30.dp),
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
                onClick = { if (createMode) navigate(SearchPlantType) }
            )

            val birthDate = createPlantUiState.shortPlantBirthDay

            OutlinedPlantTextField(
                text = birthDate,
                label = stringResource(R.string.plant_birthday),
                singleLine = true,
                enabled = false,
                modifier = Modifier.padding(top = 20.dp),
                onClick = { navigate(Calendar) }
            )

            val saveButtonComposable = @Composable {
                Card(
                    backgroundColor = PrimaryColor,
                    modifier = Modifier
                        .run {
                            if (createMode) {
                                this
                                    .align(Alignment.CenterHorizontally)
                                    .padding(top = 20.dp)
                            }
                            else this
                        }
                        .size(width = 160.dp, height = 40.dp)
                        .clip(shape = Shapes.large)
                        .clickable { savePlant() }
                ) {
                    Box {
                        Text(
                            text = stringResource(if (createMode) R.string.add_plant else R.string.update_plant),
                            color = Color.White,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                }
            }

            if (editMode) {
                Row(modifier = Modifier.padding(top = 20.dp)) {
                    saveButtonComposable()

                    WeightedSpacer()

                    Card(
                        backgroundColor = AnotherGrey,
                        modifier = Modifier.size(width = 140.dp, height = 40.dp)
                            .clip(shape = Shapes.large)
                            .clickable { deletePlant() }
                    ) {
                        Box {
                            Text(
                                text = stringResource(R.string.delete_plant),
                                fontSize = 20.sp,
                                color = Color.White,
                                fontWeight = FontWeight.Normal,
                                modifier = Modifier.align(Alignment.Center)
                            )
                        }
                    }
                }
            } else saveButtonComposable()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LockedSlotPreview() {
    CreatePlant()
}