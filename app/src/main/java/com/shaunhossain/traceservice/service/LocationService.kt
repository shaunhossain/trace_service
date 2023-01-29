package com.shaunhossain.traceservice.service

import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import com.google.android.gms.location.LocationServices
import com.shaunhossain.traceservice.R
import com.shaunhossain.traceservice.repository.db_repository.TrackerDbRepository
import com.shaunhossain.traceservice.repository.trace_repository.TraceRepository
import com.shaunhossain.traceservice.room_db.tracker_db.TrackerModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@AndroidEntryPoint
class LocationService : Service() {
    private val serviceScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
    private lateinit var locationClient: LocationClient

    @Inject
    lateinit var trackerDbRepository: TrackerDbRepository

    @Inject
    lateinit var traceRepository: TraceRepository

    @Inject
    lateinit var trackerModel: TrackerModel

    companion object {
        const val ACTION_START = "ACTION_START"
        const val ACTION_STOP = "ACTION_STOP"
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        locationClient = DefaultLocationClient(
            applicationContext,
            LocationServices.getFusedLocationProviderClient(applicationContext)
        )
        start()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when (intent?.action) {
            ACTION_START -> start()
            ACTION_STOP -> stop()
        }

        return super.onStartCommand(intent, flags, startId)
    }

    private fun start() {
        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val notification = NotificationCompat.Builder(this, "track_location")
            .setContentTitle("Tracking location ....")
            .setContentText("Location: null")
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setOngoing(true)
        locationClient.getLocationUpdates(30000L)
            .catch { e -> e.printStackTrace() }
            .onEach { location ->
                val lat = location.latitude.toString()
                val lon = location.longitude.toString()
                val updateNotification = notification.setContentText("Location: ($lat,$lon)")
                notificationManager.notify(1, updateNotification.build())
                trackerModel = TrackerModel(latitude = location.latitude, longitude = location.longitude, gpx_time = location.time, count = 0)
                trackerDbRepository.insertTrackLocation(trackerModel)
                traceRepository.sendTraceDataRequest(location)
            }
            .launchIn(serviceScope)

        startForeground(1, notification.build())
    }

    private fun stop() {
        stopForeground(true)
        stopSelf()
    }

    override fun onTaskRemoved(rootIntent: Intent?) {
        super.onTaskRemoved(rootIntent)
        Log.d("serviceWarning", "service removed")
        val filter = IntentFilter()
        filter.addAction("REFRESH_THIS")
    }

    override fun onDestroy() {
        super.onDestroy()
        serviceScope.cancel()
    }
}