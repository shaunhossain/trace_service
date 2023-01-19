package com.shaunhossain.traceservice.service

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import com.shaunhossain.traceservice.ui.MainActivity





class ServiceAutoBootRestart : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (Intent.ACTION_BOOT_COMPLETED == intent?.action) {
            Log.d("rebootBroadcast", "receive broadcast")

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                context!!.startForegroundService(Intent(context, LocationService::class.java))
            } else {
                context!!.startService(Intent(context, LocationService::class.java))
            }
        }
    }
}