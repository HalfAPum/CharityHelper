package com.example.planthelper.data.datasource.local.dao

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.example.planthelper.models.data.local.Plant
import kotlinx.coroutines.flow.Flow

@Dao
interface PlantDao {

    @Insert(onConflict = REPLACE)
    suspend fun insert(item: Plant)

    @Insert(onConflict = REPLACE)
    suspend fun insert(items: List<Plant>)

    @Update
    suspend fun update(item: Plant)

    @Delete
    suspend fun delete(item: Plant)

    @Query("SELECT * FROM Plant WHERE id = :id")
    fun getPlantFlow(id: String) : Flow<Plant>

    @Query("SELECT * FROM Plant")
    fun getAllPlantsFlow() : Flow<List<Plant>>

}