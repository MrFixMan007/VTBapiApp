package com.example.vtbapiapp.database.dtos

import java.sql.Time

data class WorkDaysDto (
    val id: Long,
    val mon_s: String,
    val mon_f: String,
    val tue_s: String,
    val tue_f: String,
    val wed_s: String,
    val wed_f: String,
    val thu_s: String,
    val thu_f: String,
    val fri_s: String,
    val fri_f: String,
    val sat_s: String,
    val sat_f: String,
    val sun_s: String,
    val sun_f: String
)

