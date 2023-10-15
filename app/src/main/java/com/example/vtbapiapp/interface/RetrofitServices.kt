package com.example.vtbapiapp.`interface`


import com.example.vtbapiapp.database.dtos.CountryDto
import com.example.vtbapiapp.database.dtos.DepartmentDto
import com.example.vtbapiapp.database.dtos.LocalityDto
import com.example.vtbapiapp.database.dtos.StateDto
import com.example.vtbapiapp.database.dtos.WorkDaysDto
import retrofit2.Call
import retrofit2.http.*


interface RetrofitServices {
    @GET("department/getDepartment")
    fun getDepartment():Call<ArrayList<DepartmentDto>>

    @GET("locality/findAllNamesOnly")
    fun findAllLocalityNamesOnly(): Call<Map<Long, String>>
    @GET("workDays/getWorkDays")
    fun getWorkDays():Call<ArrayList<WorkDaysDto>>

    @GET("state/getStates")
    fun getStates():Call<ArrayList<StateDto>>

    @GET("locality/getLocalities")
    fun getLocalities():Call<ArrayList<LocalityDto>>
    @GET("locality/getLocalityById")
    fun getLocalityById(@Query("id")id:Long):Call<LocalityDto>
    @GET("locality/findLocalityByName")
    fun findLocalityByName(@Query("name")name:String):Call<LocalityDto>

    @GET("department/getWorkloadOfDepartment")
    fun getWorkloadOfDepartment(@Query("id")id:Long):Call<Map<Long,Int>>


    @POST("gpt/createChat")
    fun createChat():Call<String>


    @GET("country/getCountries")
    fun getCountries():Call<ArrayList<CountryDto>>

    @GET("gpt/writeMessage")
    fun writeMessage(
        @Query("chatId") chatId: String,
        @Query("message") message: String
    ): Call<String>



}