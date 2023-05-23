package com.narvatov.planthelper.ui.screen.proposal

import android.app.Activity
import android.content.Intent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Checkbox
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.narvatov.planthelper.R
import com.narvatov.planthelper.models.remote.TagTitle
import com.narvatov.planthelper.ui.main.PICK_PHOTO_CODE
import com.narvatov.planthelper.ui.viewmodel.CreateProposalViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun CreateProposal(
    viewModel: CreateProposalViewModel = getViewModel()
) {
    Column(modifier = Modifier.fillMaxSize().padding(horizontal = 40.dp).verticalScroll(
        rememberScrollState())) {
        var title by rememberSaveable { mutableStateOf("") }

        OutlinedTextField(
            value = title,
            onValueChange = {
                title = it
                viewModel.onFieldValueChanged()
            },
            label = {
                Text(text = stringResource(R.string.title))
            },
            singleLine = true,
            modifier = Modifier.padding(top = 40.dp).fillMaxWidth()
        )

        var description by rememberSaveable { mutableStateOf("") }

        OutlinedTextField(
            value = description,
            onValueChange = {
                description = it
                viewModel.onFieldValueChanged()
            },
            label = {
                Text(text = stringResource(R.string.description))
            },
            modifier = Modifier.padding(top = 10.dp).fillMaxWidth()
        )

        var maxConcurrentRequests by rememberSaveable { mutableStateOf(1L) }

        OutlinedTextField(
            value = maxConcurrentRequests.toString(),
            onValueChange = {
                maxConcurrentRequests = it.toLongOrNull() ?: 1L
                viewModel.onFieldValueChanged()
            },
            label = {
                Text(text = stringResource(R.string.maxconccrue))
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.padding(top = 10.dp).fillMaxWidth()
        )

        //Tags start
        Text(
            text = stringResource(R.string.tags),
            fontSize = 20.sp,
            modifier = Modifier.padding(top = 40.dp).align(Alignment.CenterHorizontally)
        )

        Text(
            text = stringResource(R.string.location),
            fontSize = 20.sp,
            modifier = Modifier.padding(top = 40.dp).align(Alignment.CenterHorizontally)
        )

        var location1 by rememberSaveable { mutableStateOf("") }

        OutlinedTextField(
            value = location1,
            onValueChange = {
                location1 = it
                viewModel.onFieldValueChanged()
            },
            label = {
                Text(text = stringResource(R.string.oblast))
            },
            singleLine = true,
            modifier = Modifier.padding(top = 20.dp).fillMaxWidth()
        )

        var location2 by rememberSaveable { mutableStateOf("") }

        OutlinedTextField(
            value = location2,
            onValueChange = {
                location2 = it
                viewModel.onFieldValueChanged()
            },
            label = {
                Text(text = stringResource(R.string.city))
            },
            singleLine = true,
            modifier = Modifier.padding(top = 10.dp).fillMaxWidth()
        )

        var location3 by rememberSaveable { mutableStateOf("") }

        OutlinedTextField(
            value = location3,
            onValueChange = {
                location3 = it
                viewModel.onFieldValueChanged()
            },
            label = {
                Text(text = stringResource(R.string.region))
            },
            singleLine = true,
            modifier = Modifier.padding(top = 20.dp).fillMaxWidth()
        )

        var location4 by rememberSaveable { mutableStateOf("") }

        OutlinedTextField(
            value = location4,
            onValueChange = {
                location4 = it
                viewModel.onFieldValueChanged()
            },
            label = {
                Text(text = stringResource(R.string.street))
            },
            singleLine = true,
            modifier = Modifier.padding(top = 10.dp).fillMaxWidth()
        )


        Text(
            text = stringResource(R.string.age_group),
            fontSize = 20.sp,
            modifier = Modifier.padding(top = 40.dp).align(Alignment.CenterHorizontally)
        )

        val childrenChecked = remember { mutableStateOf(false) }
        val childrenString = stringResource(R.string.children)

        CheckChoise(
            checked = childrenChecked.value,
            onCheckedChange = { childrenChecked.value = it },
            label = childrenString,
            modifier = Modifier.padding(top = 20.dp)
        )

        val scholarsChecked = remember { mutableStateOf(false) }
        val scholarsString = stringResource(R.string.scholars)

        CheckChoise(
            checked = scholarsChecked.value,
            onCheckedChange = { scholarsChecked.value = it },
            label = scholarsString
        )

        val studentsChecked = remember { mutableStateOf(false) }
        val studentsString = stringResource(R.string.students)

        CheckChoise(
            checked = studentsChecked.value,
            onCheckedChange = { studentsChecked.value = it },
            label = studentsString
        )

        val middleAgedChecked = remember { mutableStateOf(false) }
        val middleAgedString = stringResource(R.string.middle_aged)

        CheckChoise(
            checked = middleAgedChecked.value,
            onCheckedChange = { middleAgedChecked.value = it },
            label = middleAgedString
        )

        val pensionersChecked = remember { mutableStateOf(false) }
        val pensionersString = stringResource(R.string.pensioners)

        CheckChoise(
            checked = pensionersChecked.value,
            onCheckedChange = { pensionersChecked.value = it },
            label = pensionersString
        )

        Text(
            text = stringResource(R.string.topic),
            fontSize = 20.sp,
            modifier = Modifier.padding(top = 40.dp).align(Alignment.CenterHorizontally)
        )

        val foodChecked = remember { mutableStateOf(false) }
        val foodString = stringResource(R.string.food)

        CheckChoise(
            checked = foodChecked.value,
            onCheckedChange = { foodChecked.value = it },
            label = foodString,
            modifier = Modifier.padding(top = 20.dp)
        )

        val clothChecked = remember { mutableStateOf(false) }
        val clothString = stringResource(R.string.cloth)

        CheckChoise(
            checked = clothChecked.value,
            onCheckedChange = { clothChecked.value = it },
            label = clothString,
            modifier = Modifier.padding(top = 20.dp)
        )

        val peopleTendChecked = remember { mutableStateOf(false) }
        val peopleTendString = stringResource(R.string.people_tend)

        CheckChoise(
            checked = peopleTendChecked.value,
            onCheckedChange = { peopleTendChecked.value = it },
            label = peopleTendString,
            modifier = Modifier.padding(top = 20.dp)
        )

        val housingRegistrationChecked = remember { mutableStateOf(false) }
        val housingRegistrationString = stringResource(R.string.housing_registration)

        CheckChoise(
            checked = housingRegistrationChecked.value,
            onCheckedChange = { housingRegistrationChecked.value = it },
            label = housingRegistrationString,
            modifier = Modifier.padding(top = 20.dp)
        )

        val placeToLiveChecked = remember { mutableStateOf(false) }
        val placeToLiveString = stringResource(R.string.place_tolive)

        CheckChoise(
            checked = placeToLiveChecked.value,
            onCheckedChange = { placeToLiveChecked.value = it },
            label = placeToLiveString,
            modifier = Modifier.padding(top = 20.dp)
        )

        val jobChecked = remember { mutableStateOf(false) }
        val jobString = stringResource(R.string.job)

        CheckChoise(
            checked = jobChecked.value,
            onCheckedChange = { jobChecked.value = it },
            label = jobString,
            modifier = Modifier.padding(top = 20.dp)
        )
        //Tags end

        val createError = viewModel.errorSharedFlow.collectAsState(null)

        if (createError.value != null) {
            Text(
                text = createError.value!!,
                color = Color.Red,
                fontSize = 20.sp,
                modifier = Modifier.padding(top = 20.dp).align(Alignment.CenterHorizontally)
            )
        }

        val activity = LocalContext.current as Activity
        Button(
            onClick = {
                val intent = Intent(Intent.ACTION_GET_CONTENT)
                intent.type = "image/*"
                activity.startActivityForResult(intent, PICK_PHOTO_CODE)
            },
            modifier = Modifier.padding(top = 10.dp).align(Alignment.CenterHorizontally)
        ) {
            Text(
                text = stringResource(R.string.choose_ev_ph),
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }

        Button(
            onClick = {
                val locationPair = Pair(
                    TagTitle.Location,
                    listOf(location1,location2,location3,location4)
                )

                val ageGroupPair = Pair(
                    TagTitle.AgeGroup,
                    listOf(
                        if (childrenChecked.value) childrenString else "",
                        if (scholarsChecked.value) scholarsString else "",
                        if (studentsChecked.value) studentsString else "",
                        if (middleAgedChecked.value) middleAgedString else "",
                        if (pensionersChecked.value) pensionersString else "",
                    )
                )

                val topics = Pair(
                    TagTitle.Topic,
                    listOf(
                        if (foodChecked.value) foodString else "",
                        if (clothChecked.value) clothString else "",
                        if (peopleTendChecked.value) peopleTendString else "",
                        if (housingRegistrationChecked.value) housingRegistrationString else "",
                        if (placeToLiveChecked.value) placeToLiveString else "",
                        if (jobChecked.value) jobString else "",
                    )
                )

                val tags = listOf(locationPair, ageGroupPair, topics)

                viewModel.createProposal(title, description, maxConcurrentRequests, tags)
            },
            modifier = Modifier.padding(top = 10.dp).align(Alignment.CenterHorizontally)
        ) {
            Text(
                text = stringResource(R.string.create),
                fontSize = 20.sp,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }

        Spacer(Modifier.height(250.dp))
    }
}