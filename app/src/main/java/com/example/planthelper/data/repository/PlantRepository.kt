package com.example.planthelper.data.repository

import com.example.planthelper.data.datasource.remote.helper.PlantInfoApiHelper
import com.example.planthelper.utils.Dispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.core.annotation.Single

@Single
class PlantRepository(
    private val plantHelper: PlantInfoApiHelper,
    private val dispatcher: Dispatcher,
) {

    suspend fun getPlants() = withContext(dispatcher.IO) {
        plantHelper.getPlants()
    }
}