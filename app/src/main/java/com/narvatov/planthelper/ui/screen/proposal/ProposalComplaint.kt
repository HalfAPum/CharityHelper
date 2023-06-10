package com.narvatov.planthelper.ui.screen.proposal

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.narvatov.planthelper.R
import com.narvatov.planthelper.data.utils.NavigationParams
import com.narvatov.planthelper.ui.viewmodel.ProposalComplaintViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun ProposalComplaint(
    viewModel: ProposalComplaintViewModel = getViewModel()
) {
    Column(modifier = Modifier.padding(20.dp).padding(top = 200.dp).fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        var comment by rememberSaveable { mutableStateOf("") }

        OutlinedTextField(
            value = comment,
            onValueChange = {
                comment = it
            },
            label = {
                Text(text = stringResource(R.string.complaintt))
            },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                viewModel.sendComplaint(comment)
            },
            modifier = Modifier.padding(top = 20.dp)
        ) {
            Text(
                text = stringResource(R.string.add_complaint),
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
    }
}