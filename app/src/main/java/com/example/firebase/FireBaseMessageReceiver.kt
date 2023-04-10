package com.example.firebase

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import androidx.test.core.app.ApplicationProvider
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage


const val channelId= "notification_channel"
const val channelName="com.example.firebase"


class FireBaseMessageReceiver : FirebaseMessagingService() {

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.i("token", "Refreshed token :: $token")
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        Log.d("Tag1", "${remoteMessage.notification?.title}")
        Log.d("Tag1", "${remoteMessage.notification?.body}")
        Log.d("Tag1", "${remoteMessage.data}")


        val sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE)
        val myEdit = sharedPreferences.edit()
        myEdit.putString("title", remoteMessage.notification!!.title.toString())
        myEdit.putString("body", remoteMessage.notification!!.body.toString())
        myEdit.putString("data", remoteMessage.data.toString())
        myEdit.apply()

        if (remoteMessage.getNotification() != null) {
            showNotification(remoteMessage.notification!!.title!!, remoteMessage.notification!!.body!!)

        }

    }

    fun getRemoteView(title: String, message: String): RemoteViews {
        val remoteView = RemoteViews(
            "com.example.firebase", R.layout.notification
        )

        remoteView.setTextViewText(R.id.title, title)
        remoteView.setTextViewText(R.id.message, message)
        remoteView.setImageViewResource(R.id.app_logo, R.drawable.gpaylogo)
        return remoteView
    }


    fun showNotification(title: String, message: String) {
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT)
        var builder: NotificationCompat.Builder = NotificationCompat.Builder(
            applicationContext,
            channelId
        )
            .setSmallIcon(R.drawable.gpaylogo)
            .setAutoCancel(true)
            .setVibrate(longArrayOf(1000, 1000, 1000, 1000))
            .setOnlyAlertOnce(true)
            .setContentIntent(pendingIntent)

        builder = builder.setContent(getRemoteView(title, message))

        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel =
                NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_HIGH)
            notificationManager.createNotificationChannel(notificationChannel)
        }
        notificationManager.notify(0, builder.build())
    }
}




