package com.example.criminalintent.database

import androidx.room.TypeConverter
import java.util.Date

class CrimeTimeConverters {
    @TypeConverter
    fun fromDate(date: Date): Long {
        return date.time
    }

    @TypeConverter
    fun toDate(ms: Long): Date {
        return Date(ms)
    }
}