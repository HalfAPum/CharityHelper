package com.narvatov.planthelper.ui.main.delegate.exception

import android.widget.Toast
import androidx.activity.ComponentActivity
import com.google.firebase.crashlytics.ktx.crashlytics
import com.google.firebase.ktx.Firebase
import com.halfapum.general.coroutines.exception.collectLatestException

object CoroutineExceptionCollector: ICoroutineExceptionCollector {

    context(ComponentActivity)
    override fun initCoroutineExceptionCollector() {
        collectLatestException {
            Firebase.crashlytics.recordException(it)

            it.printStackTrace()

            Toast.makeText(this@ComponentActivity, "$it", Toast.LENGTH_SHORT).show()
            true
        }
    }
}