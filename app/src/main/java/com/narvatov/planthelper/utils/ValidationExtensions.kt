package com.narvatov.planthelper.utils

import android.util.Patterns


fun String.isInvalidEmail() = isNullOrEmpty() || Patterns.EMAIL_ADDRESS.matcher(this).matches().not()

fun String.isInvalidPhone() = isNullOrEmpty() || Patterns.PHONE.matcher(this).matches().not()
fun String.isInvalidUrl() = isNullOrEmpty() || Patterns.WEB_URL.matcher(this).matches().not()

fun String.isInvalidPassword() = this.length < 8