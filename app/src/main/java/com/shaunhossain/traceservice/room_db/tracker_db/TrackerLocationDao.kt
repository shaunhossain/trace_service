package com.shaunhossain.traceservice.room_db.tracker_db

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface TrackerLocationDao {
    @Query("SELECT * FROM TrackerLocation")
    fun getAllLocations(): Flow<List<TrackerModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTrackLocation(trackerModel: TrackerModel)

    @Query("UPDATE TrackerLocation SET Latitude=:latitude, Longitude=:longitude, Gpx_Time=:gpx_time WHERE count =:count")
    suspend fun updateSync(latitude: Double, longitude: Double, gpx_time: String?, count: Int)

    @Query("DELETE FROM TrackerLocation WHERE Count = :count")
    suspend fun delete(count: Int)

    @Delete
    suspend fun deleteSingleLocation(trackerModel: TrackerModel)

    @Query("DELETE FROM TrackerLocation")
    suspend fun deleteAll()
}