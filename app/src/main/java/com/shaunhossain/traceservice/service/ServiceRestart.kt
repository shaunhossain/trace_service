package com.shaunhossain.traceservice.service

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log

class ServiceRestart : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        Log.d("receiveBroadcast", "receive broadcast")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            context!!.startForegroundService(Intent(context, LocationService::class.java))
        } else {
            context!!.startService(Intent(context, LocationService::class.java))
        }
    }
}