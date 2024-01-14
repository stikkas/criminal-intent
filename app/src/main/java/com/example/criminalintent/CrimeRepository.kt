package com.example.criminalintent

import android.content.Context
import androidx.room.Room
import com.example.criminalintent.database.CrimeDatabase
import java.util.UUID

private const val DATABASE_NAME = "crime-database"

class CrimeRepository private constructor(ctx: Context) {
    private val database: CrimeDatabase = Room.databaseBuilder(
        ctx.applicationContext,
        CrimeDatabase::class.java,
        DATABASE_NAME
    ).build()

    suspend fun getCrimes(): List<Crime> = database.crimeDao().getCrimes()
    suspend fun getCrime(id: UUID): Crime = database.crimeDao().getCrime(id)
    companion object {
        private var INSTANCE: CrimeRepository? = null
        fun initialize(ctx: Context) {
            if (INSTANCE == null) {
                INSTANCE = CrimeRepository(ctx)
            }
        }

        fun get(): CrimeRepository {
            return INSTANCE ?: throw IllegalStateException("CrimeRepository must be initialized")
        }
    }
}