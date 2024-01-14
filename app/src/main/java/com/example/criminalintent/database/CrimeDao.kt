package com.example.criminalintent.database

import androidx.room.Dao
import androidx.room.Query
import com.example.criminalintent.Crime
import java.util.UUID

@Dao
interface CrimeDao {
    @Query("select * from crime")
    suspend fun getCrimes(): List<Crime>

    @Query("select * from crime where id = (:id)")
    suspend fun getCrime(id: UUID): Crime
}