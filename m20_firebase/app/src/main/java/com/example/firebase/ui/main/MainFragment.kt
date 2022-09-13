package com.example.firebase.ui.main

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.fragment.app.viewModels
import com.example.firebase.App.Companion.NOTIFICATION_CHANNEL_ID
import com.example.firebase.MainActivity
import com.example.firebase.R
import com.example.firebase.databinding.FragmentMainBinding
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.google.firebase.messaging.FirebaseMessaging
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlin.Exception

@AndroidEntryPoint
class MainFragment : Fragment() {


    @Inject
   lateinit var mainFactory:MainViewModelFactory


    private val viewModel: MainViewModel by viewModels{mainFactory}
    lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding=FragmentMainBinding.inflate(inflater)

        binding.btnNotification.setOnClickListener {
            createNotification()
        }


        binding.btnCrash.setOnClickListener {
            FirebaseCrashlytics.getInstance().log("This is log message with additonal ")
            try {
                throw  Exception("My Exception")
            }catch (e:Exception){
                FirebaseCrashlytics.getInstance().recordException(e)
            }
        }

        return binding.root
    }



    fun createNotification(){
        val intent=Intent(requireContext(),MainActivity::class.java)
        val pendingIntent=if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
            PendingIntent.getActivity(requireContext(),0,intent,PendingIntent.FLAG_IMMUTABLE)
        else
            PendingIntent.getActivity(requireContext(),0,intent,PendingIntent.FLAG_UPDATE_CURRENT)


        val notification=NotificationCompat.Builder(requireContext(), NOTIFICATION_CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_notification)
            .setContentTitle("My first Notification")
            .setContentText("Рассказ о моем уведомлении")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .build()

        NotificationManagerCompat.from(requireContext()).notify(NOTIFICATION_ID,notification)
    }

    companion object{
        private const val NOTIFICATION_ID=1000
        fun newInstance() = MainFragment()
    }
}