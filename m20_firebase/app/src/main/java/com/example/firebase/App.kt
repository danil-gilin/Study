package com.example.firebase

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import com.example.firebase.ui.main.MainFragment
import com.google.firebase.crashlytics.FirebaseCrashlytics
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App:Application() {
    override fun onCreate() {
        super.onCreate()

        FirebaseCrashlytics.getInstance().setCrashlyticsCollectionEnabled(!BuildConfig.DEBUG)

        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.O){
            creatNotificationChannel()
        }
    }


    fun creatNotificationChannel(){
        val name="Test notification channel"
        val discriptionTest="Test notification Discription"
        val importance= NotificationManager.IMPORTANCE_DEFAULT

        val channel= NotificationChannel(NOTIFICATION_CHANNEL_ID,name,importance).apply {
            description=discriptionTest
        }

        val notificationManager= getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }


    companion object{
        const val NOTIFICATION_CHANNEL_ID="test_channel_id"
    }
}