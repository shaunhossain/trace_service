package com.shaunhossain.traceservice.di

import android.content.Context
import androidx.room.Room
import com.shaunhossain.traceservice.room_db.database.AppDatabase
import com.shaunhossain.traceservice.room_db.tracker_db.TrackerLocationDao
import com.shaunhossain.traceservice.room_db.tracker_db.TrackerModel
import com.shaunhossain.traceservice.utils.Constants.TRACKER_LOCATION_DATABASE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DbModule {
    @Provides
    @Singleton
    fun provide(@ApplicationContext context: Context) = Room.databaseBuilder(
        context, AppDatabase::class.java, TRACKER_LOCATION_DATABASE)
        .allowMainThreadQueries()
        .fallbackToDestructiveMigration()
        .build()

    @Provides
    fun provideTrackerLocationDao(appDatabase: AppDatabase): TrackerLocationDao {
        return appDatabase.trackerLocationDao()
    }

    @Provides
    fun provideEntity() = TrackerModel()
}