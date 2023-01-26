package com.shaunhossain.traceservice.model.trace_model

data class TraceResponse(
    val accuracy: Int,
    val altitude: Int,
    val bearing: Int,
    val company_id: Int,
    val created_at: Long,
    val gpx_time: String,
    val is_offline_data: Int,
    val latitude: Double,
    val longitude: Double,
    val service: String,
    val speed: Int,
    val updated_at: Long,
    val user_id: Int
)