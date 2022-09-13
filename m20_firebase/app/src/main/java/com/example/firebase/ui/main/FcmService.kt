package com.example.firebase.ui.main

import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.firebase.App
import com.example.firebase.MainActivity
import com.example.firebase.R
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import java.text.SimpleDateFormat
import java.util.*
import kotlin.random.Random

class FcmService:FirebaseMessagingService() {

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)

        val intent= Intent(this, MainActivity::class.java)
        val pendingIntent=if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
            PendingIntent.getActivity(this,0,intent, PendingIntent.FLAG_IMMUTABLE)
        else
            PendingIntent.getActivity(this,0,intent, PendingIntent.FLAG_UPDATE_CURRENT)



        val notification= NotificationCompat.Builder(this, App.NOTIFICATION_CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_notification)
            .setContentTitle(message.data["nickname"])
            .setContentText(message.data["message"]+creatDataFormat(message.data["timestamp"]))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .build()

        NotificationManagerCompat.from(this).notify(Random.nextInt(),notification)

    }



    override fun onNewToken(token: String) {
        super.onNewToken(token)
    }

    private fun creatDataFormat(data:String?):String{
        data ?: return ""
        val dataFormat=SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
        return dataFormat.format(Date(data.toLong()))
    }
}