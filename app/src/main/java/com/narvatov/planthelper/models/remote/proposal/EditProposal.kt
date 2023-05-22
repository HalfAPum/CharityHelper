package com.narvatov.planthelper.models.remote.proposal

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.narvatov.planthelper.R
import com.narvatov.planthelper.models.remote.TagTitle
import com.narvatov.planthelper.ui.screen.proposal.CheckChoise
import com.narvatov.planthelper.ui.viewmodel.EditHelpViewModel
import com.narvatov.planthelper.ui.viewmodel.EditProposalViewModel
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun EditProposal(
    viewModel: EditProposalViewModel = getViewModel()
) {
    val proposalVal = viewModel.proposalFlow.collectAsState(null)

    if (proposalVal.value == null) return

    val proposal = proposalVal.value!!

    var title by rememberSaveable { mutableStateOf(proposal.title) }

    var description by rememberSaveable { mutableStateOf(proposal.description) }

    val parentOptions = listOf("Active", "Inactive", "Done", "Blocked")
    var expandedState by remember { mutableStateOf(false) }
    var selectedOption by remember { mutableStateOf(parentOptions.first { it.equals(proposal.status, ignoreCase = true) }) }


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

                Row(modifier = Modifier.padding(top = 10.dp).fillMaxWidth()) {
                    Text(
                        text = stringResource(R.string.status_de) + " ",
                        modifier = Modifier.padding(end = 10.dp).align(Alignment.CenterVertically),
                        fontSize = 18.sp
                    )

                    ExposedDropdownMenuBox(
                        expanded = expandedState, onExpandedChange = {expandedState = !expandedState}){
                        TextField(
                            value = selectedOption,
                            onValueChange = {},
                            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedState)},
                            readOnly = true,
                            textStyle = TextStyle.Default.copy(fontSize = 16.sp)
                        )

                        ExposedDropdownMenu(expanded = expandedState, onDismissRequest = {expandedState = false},

                            ) { parentOptions.forEach { eachoption ->
                            DropdownMenuItem(
                                onClick = {
                                    selectedOption = eachoption
                                    expandedState = false
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

                val locationTags = proposal.tags.firstOrNull { it.title == TagTitle.Location.title }?.values ?: emptyList()
                var location1 by rememberSaveable { mutableStateOf(locationTags.firstOrNull() ?: "") }

                OutlinedTextField(
                    value = location1,
                    onValueChange = {
                        location1 = it
                        viewModel.onFieldValueChanged()
                    },
                    label = {
                        Text(text = "location1")
                    },
                    singleLine = true,
                    modifier = Modifier.padding(top = 20.dp).fillMaxWidth()
                )

                var location2 by rememberSaveable { mutableStateOf(locationTags.getOrNull(1) ?: "") }

                OutlinedTextField(
                    value = location2,
                    onValueChange = {
                        location2 = it
                        viewModel.onFieldValueChanged()
                    },
                    label = {
                        Text(text = "location2")
                    },
                    singleLine = true,
                    modifier = Modifier.padding(top = 10.dp).fillMaxWidth()
                )

                var location3 by rememberSaveable { mutableStateOf(locationTags.getOrNull(2) ?: "") }

                OutlinedTextField(
                    value = location3,
                    onValueChange = {
                        location3 = it
                        viewModel.onFieldValueChanged()
                    },
                    label = {
                        Text(text = "location3")
                    },
                    singleLine = true,
                    modifier = Modifier.padding(top = 20.dp).fillMaxWidth()
                )

                var location4 by rememberSaveable { mutableStateOf(locationTags.getOrNull(3) ?: "") }

                OutlinedTextField(
                    value = location4,
                    onValueChange = {
                        location4 = it
                        viewModel.onFieldValueChanged()
                    },
                    label = {
                        Text(text = "location4")
                    },
                    singleLine = true,
                    modifier = Modifier.padding(top = 10.dp).fillMaxWidth()
                )


                val ageGroupTags = proposal.tags.firstOrNull { it.title == TagTitle.AgeGroup.title }?.values ?: emptyList()

                Text(
                    text = stringResource(R.string.age_group),
                    fontSize = 20.sp,
                    modifier = Modifier.padding(top = 40.dp).align(Alignment.CenterHorizontally)
                )
                val childrenString = stringResource(R.string.children)

                val childrenChecked = remember { mutableStateOf(ageGroupTags.contains(childrenString)) }

                CheckChoise(
                    checked = childrenChecked.value,
                    onCheckedChange = { childrenChecked.value = it },
                    label = childrenString,
                    modifier = Modifier.padding(top = 20.dp)
                )
                val scholarsString = "Scholars"

                val scholarsChecked = remember { mutableStateOf(ageGroupTags.contains(scholarsString)) }

                CheckChoise(
                    checked = scholarsChecked.value,
                    onCheckedChange = { scholarsChecked.value = it },
                    label = scholarsString
                )
                val studentsString = stringResource(R.string.students)

                val studentsChecked = remember { mutableStateOf(ageGroupTags.contains(studentsString)) }

                CheckChoise(
                    checked = studentsChecked.value,
                    onCheckedChange = { studentsChecked.value = it },
                    label = studentsString
                )

                val middleAgedString = stringResource(R.string.middle_aged)
                val middleAgedChecked = remember { mutableStateOf(ageGroupTags.contains(middleAgedString)) }

                CheckChoise(
                    checked = middleAgedChecked.value,
                    onCheckedChange = { middleAgedChecked.value = it },
                    label = middleAgedString
                )
                val pensionersString = stringResource(R.string.pensioners)

                val pensionersChecked = remember { mutableStateOf(ageGroupTags.contains(pensionersString)) }

                CheckChoise(
                    checked = pensionersChecked.value,
                    onCheckedChange = { pensionersChecked.value = it },
                    label = pensionersString
                )

                val topicTags = proposal.tags.firstOrNull { it.title == TagTitle.Topic.title }?.values ?: emptyList()

                Text(
                    text = stringResource(R.string.topic),
                    fontSize = 20.sp,
                    modifier = Modifier.padding(top = 40.dp).align(Alignment.CenterHorizontally)
                )

                val foodString = stringResource(R.string.food)
                val foodChecked = remember { mutableStateOf(topicTags.contains(foodString)) }

                CheckChoise(
                    checked = foodChecked.value,
                    onCheckedChange = { foodChecked.value = it },
                    label = foodString,
                    modifier = Modifier.padding(top = 20.dp)
                )

                val clothString = stringResource(R.string.cloth)
                val clothChecked = remember { mutableStateOf(topicTags.contains(clothString)) }

                CheckChoise(
                    checked = clothChecked.value,
                    onCheckedChange = { clothChecked.value = it },
                    label = clothString,
                    modifier = Modifier.padding(top = 20.dp)
                )

                val peopleTendString = stringResource(R.string.people_tend)
                val peopleTendChecked = remember { mutableStateOf(topicTags.contains(peopleTendString)) }

                CheckChoise(
                    checked = peopleTendChecked.value,
                    onCheckedChange = { peopleTendChecked.value = it },
                    label = peopleTendString,
                    modifier = Modifier.padding(top = 20.dp)
                )

                val housingRegistrationString = stringResource(R.string.housing_registration)
                val housingRegistrationChecked = remember { mutableStateOf(topicTags.contains(housingRegistrationString)) }

                CheckChoise(
                    checked = housingRegistrationChecked.value,
                    onCheckedChange = { housingRegistrationChecked.value = it },
                    label = housingRegistrationString,
                    modifier = Modifier.padding(top = 20.dp)
                )

                val placeToLiveString = "Place to live"
                val placeToLiveChecked = remember { mutableStateOf(topicTags.contains(placeToLiveString)) }

                CheckChoise(
                    checked = placeToLiveChecked.value,
                    onCheckedChange = { placeToLiveChecked.value = it },
                    label = placeToLiveString,
                    modifier = Modifier.padding(top = 20.dp)
                )

                val jobString = stringResource(R.string.job)
                val jobChecked = remember { mutableStateOf(topicTags.contains(jobString)) }

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

                        viewModel.editHelp(title, description, selectedOption.toLowerCase(), tags)
                    },
                    modifier = Modifier.padding(top = 10.dp).align(Alignment.CenterHorizontally)
                ) {
                    Text(
                        text = stringResource(R.string.Edit),
                        fontSize = 20.sp,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                }

                Spacer(Modifier.height(250.dp))
            }
        }
    }
}