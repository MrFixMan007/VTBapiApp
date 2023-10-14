package com.example.vtbapiapp.database.interfaces

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.vtbapiapp.database.entitys.StateEntity
import com.example.vtbapiapp.database.entitys.withEntity.StateWithLocalities
@Dao
interface StateDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
     suspend fun insertStates(vararg states: StateEntity)
    @Delete
     suspend fun deleteStates(vararg states: StateEntity)
    @Update
     suspend fun updateStates(vararg states: StateEntity)

    @Transaction
    @Query("SELECT * FROM state")
     suspend fun getStatesWithLocalities():List<StateWithLocalities>

    @Transaction
    @Query("SELECT * FROM state where id = :id")
     suspend fun getStateWithLocalities(id:String): StateWithLocalities
}