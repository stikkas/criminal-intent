package com.example.criminalintent.database

import androidx.room.Dao
import androidx.room.Query
import com.example.criminalintent.Crime
import kotlinx.coroutines.flow.Flow
import java.util.UUID

@Dao
interface CrimeDao {
    @Query("select * from crime")
    fun getCrimes(): Flow<List<Crime>>

    @Query("select * from crime where id = (:id)")
    suspend fun getCrime(id: UUID): Crime
}