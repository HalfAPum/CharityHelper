package com.example.planthelper.ui.screen.plant.create.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Cancel
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.planthelper.R
import com.example.planthelper.ui.ListSpacer
import com.example.planthelper.ui.screen.plant.create.OutlinedPlantTextField
import com.example.planthelper.ui.theme.LightGreyBackground
import com.example.planthelper.ui.viewmodel.CreatePlantViewModel
import com.example.planthelper.ui.viewmodel.SearchViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun SearchPlantType(
    viewModel: CreatePlantViewModel,
    onPlantTypeSelected: () -> Unit,
    searchViewModel: SearchViewModel = getViewModel(),
) = with(searchViewModel) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(20.dp),
        modifier = Modifier
            .fillMaxSize()
            .background(color = LightGreyBackground)
            .padding(20.dp)
    ) {
        item {
            val plantTypeText = rememberSaveable { mutableStateOf("") }

            OutlinedPlantTextField(
                text = plantTypeText,
                label = stringResource(R.string.plant_type_hint),
                singleLine = true,
                onValueChanged = { searchViewModel.search(it) }
            ) { innerTextField ->
                Row {
                    innerTextField()

                    Spacer(modifier = Modifier.weight(1F).background(color = LightGreyBackground))

                    Image(
                        imageVector = Icons.Rounded.Cancel,
                        contentDescription = null,
                        modifier = Modifier
                            .clip(CircleShape)
                            .clickable { plantTypeText.value = "" }
                    )
                }
            }
        }

        ListSpacer()

        items(searchUiState.plants) { plant ->
            SearchCard(
                plant = plant,
                onPlantClicked = {
                    viewModel.plantTypeSelected(plant)
                    onPlantTypeSelected()
                }
            )
        }

        ListSpacer()
    }
}