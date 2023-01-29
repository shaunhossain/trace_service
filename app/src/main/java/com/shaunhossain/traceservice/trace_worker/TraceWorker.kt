package com.shaunhossain.traceservice.trace_worker

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.shaunhossain.traceservice.service.LocationService
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

@HiltWorker
class TraceWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted workerParameters: WorkerParameters
) :
    CoroutineWorker(context, workerParameters) {
    override suspend fun doWork(): Result {

        Log.d("TraceWorkerLog", "worker is working")
        Intent(applicationContext, LocationService::class.java).apply {
            action = LocationService.ACTION_START
            applicationContext.startService(this)
        }
        return Result.success()
    }
}