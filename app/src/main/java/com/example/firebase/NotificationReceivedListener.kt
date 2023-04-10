package com.example.firebase

import android.os.Bundle

class NotificationReceivedListener {

        var listener: OnNotificationReceivedListener? = null

        interface OnNotificationReceivedListener{
            fun onNotificationReceived(bundle: Bundle?)
        }

        fun setOnNotificationReceivedListener(param: OnNotificationReceivedListener) {
            listener = param
        }

        fun notificationReceived(bundle: Bundle?){

            listener?.let {
                it.onNotificationReceived(bundle)
            }
        }

    }