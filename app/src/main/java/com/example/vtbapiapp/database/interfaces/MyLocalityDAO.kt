package com.example.vtbapiapp.database.interfaces

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.vtbapiapp.database.entitys.LocalityEntity
import com.example.vtbapiapp.database.entitys.MyLocality
import com.example.vtbapiapp.database.entitys.withEntity.MyLocalityWithLocality

@Dao
interface MyLocalityDAO {
//    @Insert
//    suspend fun insertMyLocality(localityEntity: LocalityEntity)
    @Delete
    suspend fun deleteMyLocality(localityEntity: MyLocality)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMyLocality(myLocality: MyLocality)
    @Transaction
    @Query("SELECT * FROM mylocality LIMIT 1")
    suspend fun getMyLocalityWithLocality(): MyLocalityWithLocality?
    @Transaction
    @Query("SELECT * FROM mylocality LIMIT 1")
    suspend fun getMyLocality(): MyLocality?

}