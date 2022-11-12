package com.example.planthelper.data.repository

import com.example.planthelper.data.datasource.remote.helper.PlantInfoApiHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PlantRepository(private val plantHelper: PlantInfoApiHelper) {

    suspend fun getPlants() = withContext(Dispatchers.IO) {
        plantHelper.getPlants()
    }
}