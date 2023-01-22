package com.narvatov.planthelper.ui

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import androidx.navigation.NavHostController
import com.dt.composedatepicker.*
import com.narvatov.planthelper.R
import com.narvatov.planthelper.ui.navigation.Purchase
import com.narvatov.planthelper.ui.navigation.UiNavigationEventPropagator
import com.narvatov.planthelper.ui.theme.PrimaryColor
import com.narvatov.planthelper.ui.theme.Shapes
import com.narvatov.planthelper.ui.theme.healthAverage
import com.narvatov.planthelper.utils.UnitCallback
import java.text.DateFormatSymbols
import java.util.*
import kotlin.math.roundToInt
import kotlin.random.Random
import kotlin.random.nextInt

@Composable
fun Scaffold(
    navController: NavHostController,
    bottomBar: @Composable (NavHostController) -> Unit = {},
    content: @Composable (NavHostController, PaddingValues) -> Unit
) {
    Scaffold(
        bottomBar = { bottomBar(navController) },
        content = { paddingValues ->  content(navController, paddingValues) },
    )
}

/**
 * [Spacer] for lazy lists.
 *
 * By default applies [Arrangement] from your [LazyListScope].
 */
fun LazyListScope.ListSpacer(modifier: Modifier = Modifier) {
    item { Spacer(modifier = modifier) }
}

fun LazyGridScope.GridSpacer(columns: Int, modifier: Modifier = Modifier) {
    items(columns) { Spacer(modifier = modifier)}
}

@Composable
fun Shimmer(
    shimmerColor: Color = Color.Gray,
    modifier: Modifier = Modifier,
) {
    Spacer(modifier = modifier
        .clip(Shapes.small)
        .background(shimmerColor)
    )
}


@Composable
fun BoxScope.ComposeCalendar(
    minDate: Date? = null,
    maxDate: Date? = null,
    locale: Locale = Locale.getDefault(),
    title: String = "",
    listener: SelectDateListener,
    showOnlyMonth: Boolean = false,
    showOnlyYear: Boolean = false,
    themeColor:Color = Color(0xFF614FF0),
    negativeButtonTitle:String = "CANCEL",
    positiveButtonTitle:String = "OK"
) {
    if (showOnlyMonth && showOnlyYear) {
        throw IllegalStateException("'showOnlyMonth' and 'showOnlyYear' states cannot be true at the same time")
    } else {

        var minYear = 1970
        var minMonth = 0
        var maxYear = 2100
        var maxMonth = 11
        minDate?.let {
            val calendarMin = Calendar.getInstance()
            calendarMin.time = minDate
            minMonth = calendarMin.get(Calendar.MONTH)
            minYear = calendarMin.get(Calendar.YEAR)
        }
        maxDate?.let {
            val calendarMax = Calendar.getInstance()
            calendarMax.time = maxDate
            maxMonth = calendarMax.get(Calendar.MONTH)
            maxYear = calendarMax.get(Calendar.YEAR)
        }

        val (height, setHeight) = remember {
            mutableStateOf(0)
        }

        val calendar = Calendar.getInstance(locale)
        val currentMonth = calendar.get(Calendar.MONTH)
        var currentYear = calendar.get(Calendar.YEAR)

        if (minYear>currentYear){
            currentYear = minYear
        }
        if (maxYear<currentYear){
            currentYear = maxYear
        }

        val months = (DateFormatSymbols(locale).shortMonths).toList()
        val monthList = months.mapIndexed { index, name ->
            MonthData(name = name, index = index)
        }
        val (selectedMonth, setMonth) = remember {
            mutableStateOf(
                MonthData(name = DateFormatSymbols(locale).shortMonths[currentMonth],
                    index = currentMonth)
            )
        }
        val (selectedYear, setYear) = remember {
            mutableStateOf(currentYear)
        }
        val (showMonths, setShowMonths) = remember {
            mutableStateOf(!showOnlyYear)
        }

        val calendarDate = Calendar.getInstance()
        var selectedDate by remember {
            mutableStateOf(calendarDate.time)
        }

        LaunchedEffect(key1 = selectedYear, key2 = selectedMonth) {
            calendarDate.set(Calendar.YEAR, selectedYear)
            calendarDate.set(Calendar.MONTH, selectedMonth.index)
            selectedDate = calendarDate.time
        }
        LaunchedEffect(key1 = selectedYear) {
            if (selectedYear == minYear) {
                if (selectedMonth.index < minMonth) {
                    setMonth(monthList[minMonth])
                }
            }
            if (selectedYear == maxYear) {
                if (selectedMonth.index > maxMonth) {
                    setMonth(monthList[maxMonth])
                }
            }
        }

        Card(
            shape = Shapes.large,
            elevation = 10.dp,
            modifier = Modifier
                .fillMaxWidth(0.92f)
                .align(Alignment.Center),
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                CalendarHeader(selectedMonth = selectedMonth.name,
                    selectedYear = selectedYear,
                    showMonths = showMonths,
                    setShowMonths = setShowMonths,
                    title = title,
                    showOnlyMonth = showOnlyMonth,
                    showOnlyYear = showOnlyYear,
                    themeColor=themeColor)
                Crossfade(targetState = showMonths) {
                    when (it) {
                        true -> CalendarMonthView(selectedMonth = selectedMonth,
                            setMonth = setMonth,
                            minMonth = minMonth,
                            maxMonth = maxMonth,
                            setShowMonths = setShowMonths,
                            minYear = minYear,
                            maxYear = maxYear,
                            selectedYear = selectedYear,
                            monthList = monthList,
                            setHeight = setHeight,
                            showOnlyMonth = showOnlyMonth,
                            themeColor=themeColor)
                        false -> CalendarYearView(selectedYear = selectedYear,
                            setYear = setYear,
                            minYear = minYear,
                            maxYear = maxYear,
                            height = height,
                            themeColor=themeColor)
                    }
                }
                CalendarBottom(onPositiveClick = { listener.onDateSelected(selectedDate) },
                    onCancelClick = { listener.onCanceled() },
                    themeColor=themeColor,
                    negativeButtonTitle=negativeButtonTitle,
                    positiveButtonTitle=positiveButtonTitle)
            }
        }
    }
}

fun Arrangement.spaceBetween(space: Dp): Arrangement.HorizontalOrVertical {
    fun IntArray.forEachIndexed(reversed: Boolean, action: (Int, Int) -> Unit) {
        if (!reversed) {
            forEachIndexed(action)
        } else {
            for (i in (size - 1) downTo 0) {
                action(i, get(i))
            }
        }
    }

    fun placeSpaceBetween(
        totalSize: Int,
        size: IntArray,
        outPosition: IntArray,
        reverseInput: Boolean
    ) {
        val consumedSize = size.fold(0) { a, b -> a + b }
        val gapSize = if (size.size > 1) {
            (totalSize - consumedSize).toFloat() / (size.size - 1)
        } else {
            0f
        }
        var current = 0f
        size.forEachIndexed(reverseInput) { index, it ->
            outPosition[index] = current.roundToInt()
            current += it.toFloat() + gapSize
        }
    }

    return object : Arrangement.HorizontalOrVertical {
        override val spacing = space

        override fun Density.arrange(
            totalSize: Int,
            sizes: IntArray,
            layoutDirection: LayoutDirection,
            outPositions: IntArray
        ) = if (layoutDirection == LayoutDirection.Ltr) {
            placeSpaceBetween(totalSize, sizes, outPositions, reverseInput = false)
        } else {
            placeSpaceBetween(totalSize, sizes, outPositions, reverseInput = true)
        }

        override fun Density.arrange(
            totalSize: Int,
            sizes: IntArray,
            outPositions: IntArray
        ) = placeSpaceBetween(totalSize, sizes, outPositions, reverseInput = false)

        override fun toString() = "Arrangement#SpaceBetween"
    }
}

fun HashMap<ClosedFloatingPointRange<Double>, Color>.getColor(
    health: Double,
) = getOrDefault(keys.first { healthRange ->
    health in healthRange
}, healthAverage)

@Composable
fun RowScope.WeightedSpacer(modifier: Modifier = Modifier) {
    Spacer(modifier = modifier.weight(1F))
}

@Composable
fun ColumnScope.WeightedSpacer(modifier: Modifier = Modifier) {
    Spacer(modifier = modifier.weight(1F))
}

fun generateWidth(intRange: IntRange): Float = Random.nextInt(intRange).toFloat() / 10

@Composable
fun LargePrimaryButton(
    text: String,
    onClick: UnitCallback,
    modifier: Modifier = Modifier,
) {
    Card(
        backgroundColor = PrimaryColor,
        modifier = modifier
            .fillMaxWidth()
            .height(40.dp)
            .clip(shape = Shapes.large)
            .clickable { onClick() }
    ) {
        Box {
            Text(
                text = text,
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}