package com.narvatov.planthelper.models.ui

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.narvatov.planthelper.R
import com.narvatov.planthelper.data.utils.LoginStateHolder

enum class Tab(@StringRes val title: Int, @DrawableRes val icon: Int) {
    Search(R.string.search, R.drawable.ic_search),
    Public(R.string.part, R.drawable.ic_many),
    Own(R.string.own, R.drawable.ic_person),
}

val tabs: Array<Tab>
    get() = if (LoginStateHolder.isLoggedIn) Tab.values() else arrayOf(Tab.Search)