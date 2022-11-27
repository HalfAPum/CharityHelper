package com.example.planthelper.ui.screen.plant.create

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.dt.composedatepicker.SelectDateListener
import com.example.planthelper.ui.ComposeCalendar
import com.example.planthelper.ui.viewmodel.plant.create.CreatePlantViewModel
import com.example.planthelper.utils.UnitCallback
import org.koin.androidx.compose.getViewModel
import java.util.*

@Composable
fun Calendar(
    onDismiss: UnitCallback,
    viewModel: CreatePlantViewModel = getViewModel()
) {
    Box(modifier = Modifier.fillMaxSize()) {
        ComposeCalendar(listener = object : SelectDateListener {
            override fun onCanceled() {
                onDismiss()
            }

            override fun onDateSelected(date: Date) {
                viewModel.updatePlantBirthDay(date)
                onDismiss()
            }
        })
    }
}