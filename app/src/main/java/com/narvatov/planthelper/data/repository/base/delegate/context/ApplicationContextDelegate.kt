package com.narvatov.planthelper.data.repository.base.delegate.context

import android.content.Context
import com.narvatov.planthelper.utils.inject

object ApplicationContextDelegate : IApplicationContextDelegate {

    override val applicationContext: Context by inject()

}