package com.shaunhossain.traceservice.repository.trace_repository

import android.location.Location
import com.shaunhossain.traceservice.network.ApiService
import com.shaunhossain.traceservice.utils.formatDate
import okhttp3.MultipartBody
import javax.inject.Inject

class TraceRepository @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun sendTraceDataRequest(location: Location) {
        val latitude = MultipartBody.Part.createFormData("latitude", location.latitude.toString())
        val longitude =
            MultipartBody.Part.createFormData("longitude", location.longitude.toString())
        val speed = MultipartBody.Part.createFormData("speed", location.speed.toString())
        val bearing = MultipartBody.Part.createFormData("bearing", location.bearing.toString())
        val gpxTime = MultipartBody.Part.createFormData("gpx_time", formatDate(location.time)!!)
        val altitude = MultipartBody.Part.createFormData("altitude", location.altitude.toString())
        val userId = MultipartBody.Part.createFormData("user_id", "84312")
        val accuracy = MultipartBody.Part.createFormData("accuracy", location.accuracy.toString())
        val companyId = MultipartBody.Part.createFormData("company_id", "4312")
        val service = MultipartBody.Part.createFormData("service", "TRACE_SERVICE_DEMO")
        val isOfflineData = MultipartBody.Part.createFormData("is_offline_data", "0")
        try {
            apiService.sendTraceData(
                latitude,
                longitude,
                speed,
                bearing,
                gpxTime,
                altitude,
                userId,
                accuracy,
                companyId,
                service,
                isOfflineData
            )
        } catch (_: Exception) {

        }
    }
}
