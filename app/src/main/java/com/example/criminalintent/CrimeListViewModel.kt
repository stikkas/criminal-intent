package com.example.criminalintent

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.delay
import java.util.Date
import java.util.UUID

private const val TAG = "CrimeListViewModel"

class CrimeListViewModel : ViewModel() {
    val crimes = mutableListOf<Crime>()
    val size: Int
        get() = crimes.size


    suspend fun loadCrimes(): List<Crime> {
        Log.d(TAG, "loadCrimes has been invoked")
        return if (size == 0) {
            delay(5000)
            crimes += (0 until 100).map {
                Crime(
                    UUID.randomUUID(),
                    "Crime #$it",
                    Date(),
                    it % 2 == 0
                )
            }
            crimes
        } else crimes
    }
}