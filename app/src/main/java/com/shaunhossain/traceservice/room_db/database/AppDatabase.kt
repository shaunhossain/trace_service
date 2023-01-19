package com.shaunhossain.traceservice.room_db.database;

import androidx.room.Database
import androidx.room.RoomDatabase
import com.shaunhossain.traceservice.room_db.tracker_db.TrackerLocationDao
import com.shaunhossain.traceservice.room_db.tracker_db.TrackerModel


@Database(version = 1, entities = [TrackerModel::class], exportSchema = false)
abstract class AppDatabase() : RoomDatabase() {
    abstract fun trackerLocationDao(): TrackerLocationDao
}