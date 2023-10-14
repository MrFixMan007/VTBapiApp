package com.example.vtbapiapp.database.interfaces

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.vtbapiapp.database.entitys.CountryEntity
import com.example.vtbapiapp.database.entitys.withEntity.CountryWithStates
import com.example.vtbapiapp.database.entitys.DepartmentEntity

@Dao
interface CountryDAO {
    @Transaction
    @Query("SELECT * FROM country")
    suspend fun getAllCountryWithStates(): List<CountryWithStates>

    @Query("SELECT * FROM country")
    suspend fun getAllCountry(): List<CountryEntity>

    @Transaction
    @Query("SELECT department.* FROM department " +
            "INNER JOIN locality ON locality.id = department.locality_id " +
            "INNER JOIN state ON state.id = locality.state_id " +
            "INNER JOIN country ON country.id = state.country_id")
    suspend fun getAllDepartmentsInCountry(): List<DepartmentEntity>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCounties(vararg countries: CountryEntity)

    @Delete
    suspend fun deleteCountries(vararg countries: CountryEntity)

    @Update
    suspend fun updateCountries(vararg countries: CountryEntity)

    @Transaction
    @Query("SELECT department.* FROM department " +
            "INNER JOIN locality ON locality.id = department.locality_id " +
            "INNER JOIN state ON state.id = locality.state_id " +
            "INNER JOIN country ON (country.id = state.country_id)" +
            "where country_id =:id")
    suspend fun getDepartmentsByCountryId(id:String): List<DepartmentEntity>

    @Transaction
    @Query("SELECT * FROM country " +
            "WHERE country.id = :id")
    suspend fun getSatesByCountryId(id:String): List<CountryWithStates>

    @Transaction
    @Query("SELECT * FROM country")
    suspend fun getAllStates(): List<CountryWithStates>

}

