package com.example.planthelper.ui.screen.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.planthelper.ui.Scaffold
import com.example.planthelper.ui.navigation.BottomBar
import com.example.planthelper.ui.navigation.NavHostContent
import com.example.planthelper.ui.viewmodel.SettingsViewModel
import com.example.planthelper.utils.UnitCallback
import org.koin.androidx.compose.getViewModel

@Composable
fun SettingsScreen(
    onPurchaseClicked: UnitCallback,
    viewModel: SettingsViewModel = getViewModel()
) {
    Column {
        Text("Setting 1", modifier = Modifier.padding(top = 8.dp))
        Text("Setting 2", modifier = Modifier.padding(top = 8.dp))
        Text("Setting 3", modifier = Modifier.padding(top = 8.dp))
        Text("Setting 4", modifier = Modifier.padding(top = 8.dp))
        Text("Setting 5", modifier = Modifier.padding(top = 8.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun SettingScreenPreview() {
    SettingsScreen({})
}