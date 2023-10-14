package com.example.vtbapiapp.database.interfaces

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.vtbapiapp.database.entitys.LocalityEntity
import com.example.vtbapiapp.database.entitys.withEntity.LocalityWithDepartment
@Dao
interface LocalityDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
     suspend fun insertLocalities(vararg localities: LocalityEntity)
    @Delete
     suspend fun deleteLocalities(vararg localities: LocalityEntity)
    @Update
     suspend fun updateLocalities(vararg localities: LocalityEntity)

    @Transaction
    @Query("SELECT * FROM locality")
     suspend fun getLocalitiesWithDepartment():List<LocalityWithDepartment>

    @Transaction
    @Query("SELECT * FROM locality where id = :id")
     suspend fun getLocalityWithDepartment(id:String): LocalityWithDepartment

    @Query("SELECT * FROM locality")
     suspend fun getLocality(): LocalityEntity
}