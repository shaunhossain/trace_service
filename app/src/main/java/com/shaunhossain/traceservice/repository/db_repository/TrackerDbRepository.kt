package com.shaunhossain.traceservice.repository.db_repository

import androidx.annotation.WorkerThread
import com.shaunhossain.traceservice.room_db.tracker_db.TrackerLocationDao
import com.shaunhossain.traceservice.room_db.tracker_db.TrackerModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TrackerDbRepository @Inject constructor(
    private val dao: TrackerLocationDao,
) {
    var allTrackLocation: Flow<List<TrackerModel>> = dao.getAllLocations()

    @WorkerThread
    suspend fun insertTrackLocation(trackerModel: TrackerModel) {
        dao.insertTrackLocation(trackerModel)
    }

    @WorkerThread
    suspend fun updateTrackLocation(
        latitude: Double,
        longitude: Double,
        gpx_time: String?,
        count: Int
    ) {
        dao.updateSync(latitude, longitude, gpx_time, count)
    }

    @WorkerThread
    suspend fun deleteTaskImage(trackerModel: TrackerModel) {
        dao.deleteSingleLocation(trackerModel)
    }

    @WorkerThread
    suspend fun deleteAllTaskImage() {
        dao.deleteAll()
    }
}