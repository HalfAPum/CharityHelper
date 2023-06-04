package com.narvatov.planthelper.ui.screen.help

import android.app.Activity
import android.content.Intent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.narvatov.planthelper.R
import com.narvatov.planthelper.data.utils.NavigationParams
import com.narvatov.planthelper.ui.main.PICK_PHOTO_CODE
import com.narvatov.planthelper.ui.navigation.HelpTransactions
import com.narvatov.planthelper.ui.navigation.Transactions
import com.narvatov.planthelper.ui.navigation.UiNavigationEventPropagator.navigate
import com.narvatov.planthelper.ui.navigation.UiNavigationEventPropagator.popBack
import com.narvatov.planthelper.ui.viewmodel.CreateHelpTransactionViewModel
import com.narvatov.planthelper.ui.viewmodel.CreateTransactionViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun CreateHelpTransactionScreen(
    viewModel: CreateHelpTransactionViewModel = getViewModel()
) {
    Column(modifier = Modifier.padding(20.dp).padding(top = 200.dp).fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        var comment by rememberSaveable { mutableStateOf("") }

        OutlinedTextField(
            value = comment,
            onValueChange = {
                comment = it
            },
            label = {
                Text(text = stringResource(R.string.comment))
            },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                viewModel.addTransaction(NavigationParams.helpDetailsItemId, comment)
            },
            modifier = Modifier.padding(top = 20.dp)
        ) {
            Text(
                text = stringResource(R.string.creatertaareques),
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
    }
}