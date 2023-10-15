package com.example.vtbapiapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.vtbapiapp.database.entitys.AtmEntity
import com.example.vtbapiapp.database.entitys.AvailabilityEntity
import com.example.vtbapiapp.database.entitys.CountryEntity
import com.example.vtbapiapp.database.entitys.DepartmentEntity
import com.example.vtbapiapp.database.entitys.FavoritesEntity
import com.example.vtbapiapp.database.entitys.LocalityEntity
import com.example.vtbapiapp.database.entitys.MyLocality
import com.example.vtbapiapp.database.entitys.PartEntity
import com.example.vtbapiapp.database.entitys.RecentlyDepartment
import com.example.vtbapiapp.database.entitys.StateEntity
import com.example.vtbapiapp.database.entitys.WorkDaysEntity
import com.example.vtbapiapp.database.entitys.WorkloadEntity
import com.example.vtbapiapp.database.interfaces.CountryDAO
import com.example.vtbapiapp.database.interfaces.DepartmentDAO
import com.example.vtbapiapp.database.interfaces.FavoriteDepartmentDAO
import com.example.vtbapiapp.database.interfaces.LocalityDAO
import com.example.vtbapiapp.database.interfaces.MyLocalityDAO
import com.example.vtbapiapp.database.interfaces.RecentlyDepartmentDAO
import com.example.vtbapiapp.database.interfaces.StateDAO
import com.example.vtbapiapp.database.interfaces.WorkDaysDAO

@Database(entities = [CountryEntity::class, DepartmentEntity::class, LocalityEntity::class,
    StateEntity::class, WorkDaysEntity::class, FavoritesEntity::class, RecentlyDepartment::class,
                     MyLocality::class, AtmEntity::class, PartEntity::class, AvailabilityEntity::class,
    WorkloadEntity::class], exportSchema = false,version = 5)
@TypeConverters(Converters::class)
abstract  class AppDatabase : RoomDatabase(){
    abstract fun countryDao(): CountryDAO
    abstract fun departmentDAO(): DepartmentDAO
    abstract fun localityDAO(): LocalityDAO
    abstract fun stateDAO(): StateDAO
    abstract fun workDaysDAO(): WorkDaysDAO
    abstract fun myLocalityDAO(): MyLocalityDAO
    abstract fun favoriteDepartmentDAO(): FavoriteDepartmentDAO
    abstract fun recentlyDepartmentDAO(): RecentlyDepartmentDAO
}