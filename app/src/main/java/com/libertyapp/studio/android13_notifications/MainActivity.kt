package com.libertyapp.studio.android13_notifications

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Binder
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.libertyapp.studio.android13_notifications.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val channelId = "channelId"
    private lateinit var notificationManager: NotificationManager

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        createNotificationChannel()
        binding.button.setOnClickListener {
            sendNotification()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel() {
        val channel = NotificationChannel(
            channelId,
            "Dummy Channel",
            NotificationManager.IMPORTANCE_HIGH
        ).apply {
            description = "This is dummy description"
        }

        notificationManager.createNotificationChannel(channel)
    }

    private fun sendNotification() {
        val notificationBuilder = NotificationCompat.Builder(
            this, channelId
        )

        notificationBuilder.apply {
            setSmallIcon(R.drawable.ic_baseline_notifications_24)
            setTitle("Android 13!!!")
            setContentText("Android 13 notification")
        }

        NotificationManagerCompat.from(this)
            .notify(1, notificationBuilder.build())
    }
}