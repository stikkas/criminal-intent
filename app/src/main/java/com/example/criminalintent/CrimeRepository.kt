package com.example.criminalintent

import android.content.Context
import androidx.room.Room
import com.example.criminalintent.database.CrimeDatabase
import com.example.criminalintent.database.migration_1_2
import com.example.criminalintent.database.migration_2_3
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import java.util.UUID

private const val DATABASE_NAME = "crime-database"

class CrimeRepository private constructor(ctx: Context, private val scope: CoroutineScope) {
    private val database: CrimeDatabase = Room.databaseBuilder(
        ctx.applicationContext,
        CrimeDatabase::class.java,
        DATABASE_NAME
    )
        .addMigrations(migration_1_2, migration_2_3)
        .build()

    fun getCrimes(): Flow<List<Crime>> = database.crimeDao().getCrimes()
    suspend fun getCrime(id: UUID): Crime = database.crimeDao().getCrime(id)

    fun updateCrime(crime: Crime) {
        scope.launch {
            database.crimeDao().updateCrime(crime)
        }
    }

    suspend fun addCrime(crime: Crime) {
        database.crimeDao().addCrime(crime)
    }

    companion object {
        private var INSTANCE: CrimeRepository? = null
        fun initialize(ctx: Context, scope: CoroutineScope) {
            if (INSTANCE == null) {
                INSTANCE = CrimeRepository(ctx, scope)
            }
        }

        fun get(): CrimeRepository {
            return INSTANCE ?: throw IllegalStateException("CrimeRepository must be initialized")
        }
    }
}