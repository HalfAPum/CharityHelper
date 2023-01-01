package com.narvatov.planthelper.ui.viewmodel.plant.details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.halfapum.general.coroutines.launchCatching
import com.narvatov.planthelper.data.repository.plant.PlantInfoRepository
import com.narvatov.planthelper.data.repository.plant.PlantInfoRepository.Companion.SEARCH_ALL_PLANTS_QUERY
import com.narvatov.planthelper.models.ui.plant.search.EmptySearchUiState
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class SearchViewModel(private val plantInfoRepository: PlantInfoRepository): ViewModel() {

    var searchUiState by mutableStateOf(EmptySearchUiState())
        private set

    init { search(SEARCH_ALL_PLANTS_QUERY) }

    fun search(query: String) {
        launchCatching {
            val plants = plantInfoRepository.searchPlant(query)
            searchUiState = searchUiState.copy(plants = plants)
        }
    }
}