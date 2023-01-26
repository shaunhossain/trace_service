package com.shaunhossain.traceservice.network


import com.shaunhossain.traceservice.model.trace_model.TraceResponse
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @Multipart
    @POST("/add-gpx")
    suspend fun sendTraceData(
        @Part() latitude: MultipartBody.Part,
        @Part() longitude: MultipartBody.Part,
        @Part() speed: MultipartBody.Part,
        @Part() bearing: MultipartBody.Part,
        @Part() gpx_time: MultipartBody.Part,
        @Part() altitude: MultipartBody.Part,
        @Part() user_id: MultipartBody.Part,
        @Part() accuracy: MultipartBody.Part,
        @Part() company_id: MultipartBody.Part,
        @Part() service: MultipartBody.Part,
        @Part() is_offline_data: MultipartBody.Part
    ): Response<TraceResponse>
}