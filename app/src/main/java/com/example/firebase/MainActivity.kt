package com.example.firebase

import android.annotation.SuppressLint
import android.content.ContentValues
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.accessibility.AccessibilityNodeInfo
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.ViewCompat.setAccessibilityDelegate
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.ktx.messaging
import io.mob.resu.reandroidsdk.ReAndroidSDK
import java.security.AccessController.getContext


class MainActivity : AppCompatActivity() {

    @SuppressLint("RemoteViewLayout", "MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val subscribeButton = findViewById<Button>(R.id.subscribe)



        subscribeButton.setOnClickListener {
            ReAndroidSDK.getInstance(this).onLocationUpdate(13.42,84.22)
            Firebase.messaging.subscribeToTopic("weather")
                .addOnCompleteListener { task ->
                    var msg = "Subscribed"
                    if (!task.isSuccessful) {
                        msg = "Subscribe failed"
                    }
                    Log.d(ContentValues.TAG, msg)
                    Toast.makeText(baseContext, msg, Toast.LENGTH_SHORT).show()
                }

        }

        val unsubscribe = findViewById<Button>(R.id.unSubscribe)
        unsubscribe.setOnClickListener {
            Firebase.messaging.unsubscribeFromTopic("weather")
                .addOnCompleteListener { task ->
                    var msg = "UnSubscribed"
                    if (!task.isSuccessful) {
                        msg = "Unsubscribe failed"
                    }
                    Log.d(ContentValues.TAG, msg)
                    Toast.makeText(baseContext, msg, Toast.LENGTH_SHORT).show()
                }
        }
        Log.d("version", "${Build.VERSION.SDK_INT}")
    }
}
