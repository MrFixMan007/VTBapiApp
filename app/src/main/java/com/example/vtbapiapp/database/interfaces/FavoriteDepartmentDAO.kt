package com.example.vtbapiapp.database.interfaces

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.vtbapiapp.database.entitys.withEntity.FavoriteDepartmentWithDepartment
import com.example.vtbapiapp.database.entitys.FavoritesEntity

@Dao
interface FavoriteDepartmentDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
     suspend fun insertFavorite(vararg favoritesEntity: FavoritesEntity)

    @Delete
     suspend fun deleteFavorite(vararg favoritesEntity: FavoritesEntity)

    @Query("DELETE FROM favorites WHERE department_id = :departmentId")
    suspend fun deleteFavoriteByDepartmentId(departmentId: Long)

    @Transaction
    @Query("SELECT * FROM favorites")
    suspend fun  getAllFavorites():List<FavoriteDepartmentWithDepartment>
}