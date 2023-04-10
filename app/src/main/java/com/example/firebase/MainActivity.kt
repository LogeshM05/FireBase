package com.example.firebase

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


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
        title.setText(nTitle)
        body.setText(nBody)
        data.setText(nData)

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

