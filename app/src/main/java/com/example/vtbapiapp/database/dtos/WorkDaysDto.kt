package com.example.vtbapiapp.database.dtos

import java.sql.Time

data class WorkDaysDto (
    val id: Long,
    val mon_s: Time,
    val mon_f: Time,
    val tue_s: Time,
    val tue_f: Time,
    val wed_s: Time,
    val wed_f: Time,
    val thu_s: Time,
    val thu_f: Time,
    val fri_s: Time,
    val fri_f: Time,
    val sat_s: Time,
    val sat_f: Time,
    val sun_s: Time,
    val sun_f: Time
)

