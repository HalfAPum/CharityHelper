package com.narvatov.planthelper.ui.main.delegate.firebase

import android.content.Context
import com.google.firebase.analytics.FirebaseAnalytics

interface IFirebaseDelegate {

    var firebaseAnalytics: FirebaseAnalytics

    context(Context)
    fun initFirebase()

}