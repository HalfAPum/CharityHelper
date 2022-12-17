package com.narvatov.planthelper.ui.main.delegate.firebase

import androidx.activity.ComponentActivity
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase
import kotlin.properties.Delegates

object FirebaseDelegate: IFirebaseDelegate {

    override var firebaseAnalytics: FirebaseAnalytics by Delegates.notNull()

    override fun ComponentActivity.initFirebase() {
        firebaseAnalytics = Firebase.analytics
    }

}