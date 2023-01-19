package com.shaunhossain.traceservice.room_db.tracker_db;

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.shaunhossain.traceservice.utils.Constants.TRACKER_LOCATION_TABLE

@Entity(tableName = TRACKER_LOCATION_TABLE)
data class TrackerModel(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    @ColumnInfo(name = "Latitude") var latitude: Double = 0.0,
    @ColumnInfo(name = "Longitude") var longitude: Double = 0.0,
    @ColumnInfo(name = "Gpx_Time") var gpx_time: Long = 0,
    @ColumnInfo(name = "Count") var count: Int = 0
)
