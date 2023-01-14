package com.narvatov.planthelper.ui.theme

import androidx.compose.ui.graphics.Color

val Purple200 = Color(0xFFBB86FC)
val Purple500 = Color(0xFF6200EE)
val Purple700 = Color(0xFF3700B3)
val Teal200 = Color(0xFF03DAC5)
val LightGreyBackground = Color(0xFFF0F0F0)

val LightRed = Color(0xFFFF5252)

val PrimaryColor = Color(0xFF00AC00)
val SecondaryColor = Color(0xFFFFC529)

val RegularBlack = Color(0xFF0F0F0F)
val RegularGrey = Color(0xFF535353)
val AnotherGrey = Color(0xFF161616)
val SoftGrey = Color(0xFFCDCDCD)
val SuperSoftGrey = Color(0xFF8C8C8C)


val healthGood = Color(0xFF006911)
val healthAverage = Color(0xFFF2AE00)
val healthBad = Color(0xFFD7481B)

val healthColorMap = hashMapOf(
    0.0..0.3 to healthBad,
    0.3..0.7 to healthAverage,
    0.7..1.0 to healthGood,
)

val healthTextColorMap = hashMapOf(
    0.0..0.3 to RegularBlack,
    0.3..0.7 to RegularBlack,
    0.7..1.0 to Color.White,
)

val plantWhiteVerticalGradientColors = listOf(
    Color(0xAAFFFFFF),
    Color(0xFFFFFFFF),
)

val plantLightGreyVerticalGradientColors = listOf(
    Color(0xAAA6A6A6),
    Color(0xFFA6A6A6),
)

val plantBlackVerticalGradientColors = listOf(
    Color(0xAAEEEEEE),
    Color(0xFFEEEEEE),
)