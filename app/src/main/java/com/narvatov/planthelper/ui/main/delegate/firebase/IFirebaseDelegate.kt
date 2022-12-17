package com.narvatov.planthelper.ui.main.delegate.firebase

import androidx.activity.ComponentActivity
import com.google.firebase.analytics.FirebaseAnalytics

interface IFirebaseDelegate {

    var firebaseAnalytics: FirebaseAnalytics

    fun ComponentActivity.initFirebase()

}