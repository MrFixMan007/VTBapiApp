package com.example.vtbapiapp

import com.example.vtbapiapp.database.dtos.DepartmentDto

data class SearchDepartment(val departmentDto: DepartmentDto, val dist:String, val load:String, val workTime:String)