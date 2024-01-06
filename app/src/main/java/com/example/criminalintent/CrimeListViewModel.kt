package com.example.criminalintent

import androidx.lifecycle.ViewModel
import java.util.Date
import java.util.UUID

class CrimeListViewModel : ViewModel() {
    private val crimes = mutableListOf<Crime>()
    val size: Int
        get() = crimes.size
    init {
        for (i in 0 until 100) {
            crimes += Crime(
                UUID.randomUUID(),
                "Crime #$i",
                Date(),
                i % 2 == 0
            )
        }
    }
}