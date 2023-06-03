package com.narvatov.planthelper.ui.screen.help

import android.app.Activity
import android.content.Intent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Cancel
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.narvatov.planthelper.R
import com.narvatov.planthelper.models.remote.TagTitle
import com.narvatov.planthelper.models.remote.help.Need
import com.narvatov.planthelper.ui.main.PICK_PHOTO_CODE
import com.narvatov.planthelper.ui.screen.DateFormater
import com.narvatov.planthelper.ui.screen.DatePickerview
import com.narvatov.planthelper.ui.screen.proposal.CheckChoise
import com.narvatov.planthelper.ui.viewmodel.CreateHelpViewModel
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CreateHelp(
    viewModel: CreateHelpViewModel = getViewModel()
) {
    val needsList = remember { mutableStateListOf<Need>() }

    var title by rememberSaveable { mutableStateOf("") }

    var description by rememberSaveable { mutableStateOf("") }

    var datePicked : String? by remember {
        mutableStateOf(null)
    }
    var datePickedTime : Long? by remember {
        mutableStateOf(null)
    }

    LazyColumn (modifier = Modifier.fillMaxSize().padding(horizontal = 40.dp)) {
        item {
            Column {
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

                Box(modifier = Modifier.padding(vertical = 10.dp)) {
                    val updatedDate = { date : Long? ->
                        datePicked = DateFormater(date)
                        datePickedTime = date
                    }

                    DatePickerview( datePicked, updatedDate )
                }

                //Needs
                Row(Modifier.padding(top = 40.dp, bottom = 10.dp).fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = stringResource(R.string.needs),
                        fontSize = 20.sp,
                        modifier = Modifier.padding(end = 20.dp)
                    )

                    Icon(
                        imageVector = Icons.Rounded.Add,
                        contentDescription = null,
                        modifier = Modifier.size(40.dp).clickable {
                            needsList.add(Need(1,"",""))
                        }
                    )
                }

            }
        }



        items(needsList) { need ->
            Column {
                Row(Modifier.padding(top = 10.dp), verticalAlignment = Alignment.CenterVertically) {
                    var needTitle by rememberSaveable { mutableStateOf(need.title) }

                    OutlinedTextField(
                        value = needTitle,
                        onValueChange = {
                            needTitle = it
                            need.title = it
                            viewModel.onFieldValueChanged()
                        },
                        label = {
                            Text(text = stringResource(R.string.title))
                        },
                        singleLine = true,
                        modifier = Modifier.width(170.dp)
                    )
                    var amount by rememberSaveable { mutableStateOf(need.amount) }

                    OutlinedTextField(
                        value = amount.toString(),
                        onValueChange = {
                            amount = it.toIntOrNull() ?: 0
                            need.amount = amount
                            viewModel.onFieldValueChanged()
                        },
                        label = {
                            Text(text = stringResource(R.string.amount))
                        },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        singleLine = true,
                        modifier = Modifier.width(100.dp).padding(start = 6.dp)
                    )


                    Icon(
                        imageVector = Icons.Rounded.Cancel,
                        contentDescription = null,
                        modifier = Modifier.padding(start = 6.dp).size(40.dp).clickable {
                            needsList.remove(need)
                        }
                    )
                }

                val parentOptions = listOf("Kilogram", "Liter", "Item", "Work")
                var expandedState by remember { mutableStateOf(false) }
                var selectedOption by remember { mutableStateOf(parentOptions.first()) }

                Row(Modifier.padding(top = 8.dp)) {

                    Text(
                        text = stringResource(R.string.unit),
                        fontSize = 20.sp,
                        modifier = Modifier.align(Alignment.CenterVertically).padding(end = 10.dp)
                    )

                    ExposedDropdownMenuBox(
                        expanded = expandedState,
                        onExpandedChange = { expandedState = !expandedState }) {
                        TextField(
                            value = selectedOption,
                            onValueChange = {},
                            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedState) },
                            readOnly = true,
                            textStyle = TextStyle.Default.copy(fontSize = 16.sp)
                        )

                        ExposedDropdownMenu(
                            expanded = expandedState, onDismissRequest = { expandedState = false },

                            ) {
                            parentOptions.forEach { eachoption ->
                                DropdownMenuItem(
                                    onClick = {
                                        selectedOption = eachoption
                                        expandedState = false
                                        need.unit = selectedOption.toLowerCase()
                                    }
                                ) {
                                    Text(text = eachoption, fontSize = 16.sp)
                                }
                            }

                        }
                    }
                }

            }
        }

        item {
            Column {
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
                            listOf(location1, location2, location3, location4)
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

                        viewModel.createHelp(title, description, datePicked, datePickedTime, needsList.toList(), tags)
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
    }
}