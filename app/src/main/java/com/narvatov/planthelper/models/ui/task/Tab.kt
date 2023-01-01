package com.narvatov.planthelper.models.ui.task

import androidx.annotation.DrawableRes
import com.narvatov.planthelper.R

enum class Tab(val title: String, @DrawableRes val icon: Int) {
    Active("Active", R.drawable.ic_activity),
    History("History", R.drawable.ic_history),
}

val tabs = Tab.values()
