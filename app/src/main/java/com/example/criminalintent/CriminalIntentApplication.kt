package com.example.criminalintent

import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.ExecutorCoroutineDispatcher
import kotlinx.coroutines.cancel
import kotlinx.coroutines.newFixedThreadPoolContext

class CriminalIntentApplication @OptIn(DelicateCoroutinesApi::class) constructor(
    private val context: ExecutorCoroutineDispatcher = newFixedThreadPoolContext(1, "myScope"),
    private val scope: CoroutineScope = CoroutineScope(context)

) : Application() {
    override fun onCreate() {
        super.onCreate()
        CrimeRepository.initialize(this, scope)
    }

    override fun onTerminate() {
        super.onTerminate()
        scope.cancel()
        context.close()
    }
}