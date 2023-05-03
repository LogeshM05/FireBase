package com.example.firebase.common

import android.app.Application
import io.mob.resu.reandroidsdk.AppConstants
import io.mob.resu.reandroidsdk.ReAndroidSDK

class FirebaseBQ : Application(){

    override fun onCreate() {
        super.onCreate()

        ReAndroidSDK.getInstance(this)
        AppConstants.LogFlag = true

    }
}