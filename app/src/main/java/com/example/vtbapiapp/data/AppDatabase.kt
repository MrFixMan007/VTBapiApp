package com.example.vtbapiapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.vtbapiapp.data.dao.DepartmentDao
import com.example.vtbapiapp.data.entities.Department

@Database(
    entities = [
        Department::class,
               ],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun departmentDao(): DepartmentDao
    companion object{
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase{
            return (instance ?: synchronized(this){
                instance ?: Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java, "database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { instance = it }
            })
        }
    }
}