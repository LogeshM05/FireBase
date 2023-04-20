package com.example.firebase

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.accessibility.AccessibilityNodeInfo
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.ViewCompat.setAccessibilityDelegate
import java.security.AccessController.getContext


class MainActivity : AppCompatActivity() {

    @SuppressLint("RemoteViewLayout", "MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


// To get data from system tray
        if (intent.extras != null) {
            for (key in intent.extras!!.keySet()) {
                val value = intent.extras!!.getString(key)
                Log.d("Tag1", "Key: $key Value: $value")
            }
        }


        val title = findViewById<TextView>(R.id.title1)
        val body = findViewById<TextView>(R.id.body)
        val data=findViewById<TextView>(R.id.data)

        val sp = getSharedPreferences("MySharedPref", MODE_PRIVATE)
        val nTitle= sp.getString("title", "")
        val nBody = sp.getString("body", "")
        val nData= sp.getString("data","")
        title.text = nTitle
        body.text = nBody
        data.text = nData

        val access = findViewById<View>(R.id.view)

        access.accessibilityDelegate = object : View.AccessibilityDelegate() {
            override fun onInitializeAccessibilityNodeInfo(
                host: View,
                info: AccessibilityNodeInfo
            ) {
                super.onInitializeAccessibilityNodeInfo(host, info)
                info.isClickable=true
                info.addAction(AccessibilityNodeInfo.AccessibilityAction.ACTION_COPY)
                Log.d("actionCopied","Success")

            }
            override fun performAccessibilityAction(
                host: View,
                action: Int,
                args: Bundle?
            ): Boolean {
                if (action == AccessibilityNodeInfo.ACTION_CLICK) {
                    // Perform your custom action here
                    Toast.makeText(this@MainActivity, "View clicked!", Toast.LENGTH_SHORT).show()
                    return true
                }
                Log.d("action","action done")

                return super.performAccessibilityAction(host, action, args)
            }


        }

    }
}
//        val notification = intent.getStringExtra("message_key")
//
//        title.text = notification
//        body.text=notification

//        val extras = intent.extras
//        if (extras != null) {
//            val value = extras.getString("message_key")
//            title.text = value
//        }

//        var ref = FireBaseMessageReceiver()
//        println(ref.lo)
//   }

//}

