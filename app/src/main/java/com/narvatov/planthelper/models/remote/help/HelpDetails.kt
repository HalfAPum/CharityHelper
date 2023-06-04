package com.narvatov.planthelper.models.remote.help

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.narvatov.planthelper.R
import com.narvatov.planthelper.data.utils.LoginStateHolder
import com.narvatov.planthelper.models.remote.RequestStatus
import com.narvatov.planthelper.models.remote.proposal.CommentItem
import com.narvatov.planthelper.models.remote.proposal.OutlinedTextFieldDisEnabled
import com.narvatov.planthelper.ui.ListSpacer
import com.narvatov.planthelper.ui.WeightedSpacer
import com.narvatov.planthelper.ui.navigation.EditHelp
import com.narvatov.planthelper.ui.navigation.EditProposal
import com.narvatov.planthelper.ui.navigation.HelpTransactions
import com.narvatov.planthelper.ui.navigation.Transactions
import com.narvatov.planthelper.ui.navigation.UiNavigationEventPropagator
import com.narvatov.planthelper.ui.screen.proposal.CheckChoise
import com.narvatov.planthelper.ui.viewmodel.HelpEventDetailsViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun HelpDetails(
    viewModel: HelpEventDetailsViewModel = getViewModel()
) = with(viewModel) {
    val help = helpFlow.collectAsState(null)

    if (help.value == null) return@with

    Box {
        LazyColumn(modifier = Modifier.fillMaxSize().padding(horizontal = 20.dp)) {
            with(help.value!!) {
                item {
                    AsyncImage(
                        model = imageURL,
                        contentDescription = null,
                        contentScale = androidx.compose.ui.layout.ContentScale.FillWidth,
                        modifier = androidx.compose.ui.Modifier.fillMaxWidth().clip(com.narvatov.planthelper.ui.theme.Shapes.medium).padding(top = 20.dp)
                    )

                    Text(
                        text = title,
                        style = androidx.compose.material.MaterialTheme.typography.h4,
                        textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                        modifier = androidx.compose.ui.Modifier.fillMaxWidth().padding(top = 16.dp)
                    )

                    Text(
                        text = description,
                        fontSize = 18.sp,
                        style = androidx.compose.material.MaterialTheme.typography.body1,
                        modifier = androidx.compose.ui.Modifier.padding(top = 16.dp),
                    )

                    Row(modifier = androidx.compose.ui.Modifier.padding(top = 16.dp)) {
                        Column() {
                            Text(
                                text = stringResource(com.narvatov.planthelper.R.string.authir) + " " + author.username,
                                style = androidx.compose.material.MaterialTheme.typography.body2
                            )

                            Text(
                                text = stringResource(com.narvatov.planthelper.R.string.contact_phone) + " " + author.phone,
                                style = androidx.compose.material.MaterialTheme.typography.body2,
                                modifier = androidx.compose.ui.Modifier.padding(top = 8.dp)
                            )
                        }

                        WeightedSpacer()

                        Column {
                            Text(
                                text = stringResource(com.narvatov.planthelper.R.string.status) + " " + status,
                                style = androidx.compose.material.MaterialTheme.typography.body2
                            )

                            //TODO
//                            Text(
//                                text = stringResource(com.narvatov.planthelper.R.string.busy) + " " + busyRequestText,
//                                style = androidx.compose.material.MaterialTheme.typography.body2,
//                                modifier = androidx.compose.ui.Modifier.padding(top = 8.dp)
//                            )
                        }
                    }
                    Row(modifier = androidx.compose.ui.Modifier.padding(top = 10.dp)) {
                        Text(
                            text = stringResource(com.narvatov.planthelper.R.string.crt_Datdd) + " " + creationDate.substringBefore(
                                'T'
                            ),
                            style = androidx.compose.material.MaterialTheme.typography.caption
                        )

                        WeightedSpacer()

                        Text(
                            text = stringResource(com.narvatov.planthelper.R.string.cmptdd) + " " + competitionDate?.substringBefore("T"),
                            style = androidx.compose.material.MaterialTheme.typography.caption,
                        )
                    }

                    if (com.narvatov.planthelper.data.utils.LoginStateHolder.isLoggedIn.not() && commentList.isEmpty()) return@item

                    Text(
                        text = stringResource(com.narvatov.planthelper.R.string.comments),
                        style = androidx.compose.material.MaterialTheme.typography.h6,
                        textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                        modifier = androidx.compose.ui.Modifier.fillMaxWidth().padding(vertical = 16.dp)
                    )
                }

                item {
                    if (com.narvatov.planthelper.data.utils.LoginStateHolder.isLoggedIn.not() && commentList.isEmpty()) return@item

                    Column(
                        modifier = androidx.compose.ui.Modifier.background(
                            color = androidx.compose.ui.graphics.Color.LightGray,
                            shape = com.narvatov.planthelper.ui.theme.Shapes.medium
                        )
                            .fillMaxWidth().padding(vertical = 5.dp, horizontal = 10.dp)
                    ) {
                        for (comment in commentList) {
                            CommentItem(comment)
                        }

                        if (com.narvatov.planthelper.data.utils.LoginStateHolder.isLoggedIn) {
                            var commentText by rememberSaveable { mutableStateOf("") }

                            OutlinedTextField(
                                value = commentText,
                                onValueChange = {
                                    commentText = it
                                },
                                label = {
                                    Text(text = stringResource(com.narvatov.planthelper.R.string.add_comment))
                                },
                                modifier = androidx.compose.ui.Modifier.padding(top = if (commentList.isNotEmpty()) 10.dp else 0.dp)
                                    .fillMaxWidth(),
                                colors = androidx.compose.material.TextFieldDefaults.textFieldColors(backgroundColor = androidx.compose.ui.graphics.Color.White)
                            )

                            Button(
                                onClick = {
                                    viewModel.addComment(id, commentText)
                                },
                                modifier = androidx.compose.ui.Modifier.padding(top = 5.dp)
                            ) {
                                Text(
                                    text = stringResource(com.narvatov.planthelper.R.string.sendcom),
                                    modifier = androidx.compose.ui.Modifier.fillMaxWidth()
                                )
                            }
                        }
                    }
                }

                ListSpacer(modifier = Modifier.padding(top = 20.dp))

                item {
                    Text(
                        text = stringResource(R.string.needs_list),
                        fontSize = 24.sp,
                        modifier = Modifier.padding(bottom = 10.dp)
                    )
                }

                items(this.needs) { putNeed ->
                    Column {
                        Row(
                            Modifier.padding(top = 5.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = stringResource(R.string.help_de) + " " + putNeed.title,
                                fontSize = 20.sp
                            )

                            WeightedSpacer()

                            Text(
                                text = putNeed.unit,
                                fontSize = 18.sp,
                                modifier = Modifier
                            )

                            Text(
                                text = putNeed.receivedComparement,
                                fontSize = 18.sp,
                                modifier = Modifier.padding(start = 10.dp)
                            )
                        }

                        Spacer(
                            modifier = Modifier.padding(top = 4.dp).height(1.dp).fillMaxWidth()
                                .background(color = Color.Gray)
                        )
                    }
                }

                item {
                    Column {
                        //Tags start
                        Text(
                            text = stringResource(com.narvatov.planthelper.R.string.tags),
                            fontSize = 20.sp,
                            modifier = androidx.compose.ui.Modifier.padding(top = 40.dp)
                                .align(androidx.compose.ui.Alignment.CenterHorizontally)
                        )

                        Text(
                            text = stringResource(com.narvatov.planthelper.R.string.location),
                            fontSize = 20.sp,
                            modifier = androidx.compose.ui.Modifier.padding(top = 40.dp)
                                .align(androidx.compose.ui.Alignment.CenterHorizontally)
                        )

                        val locationTag = tags.firstOrNull {
                            it.title == com.narvatov.planthelper.models.remote.TagTitle.Location.title
                        }?.values
                        var location1 by rememberSaveable { mutableStateOf(locationTag?.getOrNull(0) ?:"") }

                        OutlinedTextFieldDisEnabled(
                            value = location1,
                            onValueChange = {
                                location1 = it
                            },
                            label = {
                                Text(text = stringResource(R.string.oblast))
                            },
                            enabled = false,
                            singleLine = true,
                            modifier = androidx.compose.ui.Modifier.padding(top = 20.dp).fillMaxWidth()
                        )

                        var location2 by rememberSaveable { mutableStateOf(locationTag?.getOrNull(1) ?:"") }

                        OutlinedTextFieldDisEnabled(
                            value = location2,
                            onValueChange = {
                                location2 = it
                            },
                            label = {
                                Text(text = stringResource(R.string.city))
                            },
                            enabled = false,
                            singleLine = true,
                            modifier = androidx.compose.ui.Modifier.padding(top = 10.dp).fillMaxWidth()
                        )

                        var location3 by rememberSaveable { mutableStateOf(locationTag?.getOrNull(2) ?:"") }

                        OutlinedTextFieldDisEnabled(
                            value = location3,
                            onValueChange = {
                                location3 = it
                            },
                            label = {
                                Text(text = stringResource(R.string.region))
                            },
                            enabled = false,
                            singleLine = true,
                            modifier = androidx.compose.ui.Modifier.padding(top = 20.dp).fillMaxWidth()
                        )

                        var location4 by rememberSaveable { mutableStateOf(locationTag?.getOrNull(3) ?:"") }

                        OutlinedTextFieldDisEnabled(
                            value = location4,
                            onValueChange = {
                                location4 = it
                            },
                            label = {
                                Text(text = stringResource(R.string.street))
                            },
                            enabled = false,
                            singleLine = true,
                            modifier = androidx.compose.ui.Modifier.padding(top = 10.dp).fillMaxWidth()
                        )

                        val ageGroupTag = tags.firstOrNull {
                            it.title == com.narvatov.planthelper.models.remote.TagTitle.AgeGroup.title
                        }?.values ?: emptyList()
                        Text(
                            text = stringResource(com.narvatov.planthelper.R.string.age_group),
                            fontSize = 20.sp,
                            modifier = androidx.compose.ui.Modifier.padding(top = 40.dp)
                                .align(androidx.compose.ui.Alignment.CenterHorizontally)
                        )

                        val childrenString = stringResource(com.narvatov.planthelper.R.string.children)
                        val childrenChecked = remember { mutableStateOf(ageGroupTag.contains(childrenString)) }

                        CheckChoise(
                            checked = childrenChecked.value,
                            onCheckedChange = { childrenChecked.value = it },
                            label = childrenString,
                            enabled = false,
                            modifier = androidx.compose.ui.Modifier.padding(top = 20.dp)
                        )
                        val scholarsString = stringResource(com.narvatov.planthelper.R.string.scholars)

                        val scholarsChecked = remember { mutableStateOf(ageGroupTag.contains(scholarsString)) }

                        CheckChoise(
                            checked = scholarsChecked.value,
                            onCheckedChange = { scholarsChecked.value = it },
                            enabled = false,
                            label = scholarsString
                        )
                        val studentsString = stringResource(com.narvatov.planthelper.R.string.students)

                        val studentsChecked = remember { mutableStateOf(ageGroupTag.contains(studentsString)) }

                        CheckChoise(
                            checked = studentsChecked.value,
                            onCheckedChange = { studentsChecked.value = it },
                            enabled = false,
                            label = studentsString
                        )

                        val middleAgedString = stringResource(com.narvatov.planthelper.R.string.middle_aged)
                        val middleAgedChecked = remember { mutableStateOf(ageGroupTag.contains(middleAgedString)) }

                        CheckChoise(
                            checked = middleAgedChecked.value,
                            onCheckedChange = { middleAgedChecked.value = it },
                            enabled = false,
                            label = middleAgedString
                        )

                        val pensionersString = stringResource(com.narvatov.planthelper.R.string.pensioners)
                        val pensionersChecked = remember { mutableStateOf(ageGroupTag.contains(pensionersString)) }

                        CheckChoise(
                            checked = pensionersChecked.value,
                            onCheckedChange = { pensionersChecked.value = it },
                            enabled = false,
                            label = pensionersString
                        )

                        val topicTag = tags.firstOrNull {
                            it.title == com.narvatov.planthelper.models.remote.TagTitle.Topic.title
                        }?.values ?: emptyList()
                        Text(
                            text = stringResource(com.narvatov.planthelper.R.string.topic),
                            fontSize = 20.sp,
                            modifier = androidx.compose.ui.Modifier.padding(top = 40.dp)
                                .align(androidx.compose.ui.Alignment.CenterHorizontally)
                        )

                        val foodString = stringResource(com.narvatov.planthelper.R.string.food)
                        val foodChecked = remember { mutableStateOf(topicTag.contains(foodString)) }

                        CheckChoise(
                            checked = foodChecked.value,
                            onCheckedChange = { foodChecked.value = it },
                            label = foodString,
                            enabled = false,
                            modifier = androidx.compose.ui.Modifier.padding(top = 20.dp)
                        )

                        val clothString = stringResource(com.narvatov.planthelper.R.string.cloth)
                        val clothChecked = remember { mutableStateOf(topicTag.contains(clothString)) }

                        CheckChoise(
                            checked = clothChecked.value,
                            onCheckedChange = { clothChecked.value = it },
                            label = clothString,
                            enabled = false,
                            modifier = androidx.compose.ui.Modifier.padding(top = 20.dp)
                        )

                        val peopleTendString = stringResource(com.narvatov.planthelper.R.string.people_tend)
                        val peopleTendChecked = remember { mutableStateOf(topicTag.contains(peopleTendString)) }

                        CheckChoise(
                            checked = peopleTendChecked.value,
                            onCheckedChange = { peopleTendChecked.value = it },
                            label = peopleTendString,
                            enabled = false,
                            modifier = androidx.compose.ui.Modifier.padding(top = 20.dp)
                        )

                        val housingRegistrationString = stringResource(com.narvatov.planthelper.R.string.housing_registration)
                        val housingRegistrationChecked = remember { mutableStateOf(topicTag.contains(housingRegistrationString)) }

                        CheckChoise(
                            checked = housingRegistrationChecked.value,
                            onCheckedChange = { housingRegistrationChecked.value = it },
                            label = housingRegistrationString,
                            enabled = false,
                            modifier = androidx.compose.ui.Modifier.padding(top = 20.dp)
                        )

                        val placeToLiveString = stringResource(com.narvatov.planthelper.R.string.place_tolive)
                        val placeToLiveChecked = remember { mutableStateOf(topicTag.contains(placeToLiveString)) }

                        CheckChoise(
                            checked = placeToLiveChecked.value,
                            onCheckedChange = { placeToLiveChecked.value = it },
                            label = placeToLiveString,
                            enabled = false,
                            modifier = androidx.compose.ui.Modifier.padding(top = 20.dp)
                        )

                        val jobString = stringResource(com.narvatov.planthelper.R.string.job)
                        val jobChecked = remember { mutableStateOf(topicTag.contains(jobString)) }

                        CheckChoise(
                            checked = jobChecked.value,
                            onCheckedChange = { jobChecked.value = it },
                            label = jobString,
                            enabled = false,
                            modifier = androidx.compose.ui.Modifier.padding(top = 20.dp)
                        )
                    }
                }

                ListSpacer(modifier = androidx.compose.ui.Modifier.height(40.dp))
            }
        }

        if (LoginStateHolder.isLoggedIn) {
            FloatingActionButton(
                onClick = { UiNavigationEventPropagator.navigate(HelpTransactions) },
                modifier = Modifier.align(Alignment.TopEnd).padding(20.dp)
            ) {
                Text(text = stringResource(R.string.transactions), fontWeight = FontWeight.Bold, modifier = Modifier.padding(horizontal = 20.dp))
            }
        }

        if (LoginStateHolder.isLoggedIn && help.value?.author?.id == LoginStateHolder.signInState.signInData?.id) {
            FloatingActionButton(
                onClick = { UiNavigationEventPropagator.navigate(EditHelp) },
                modifier = Modifier.align(Alignment.TopStart).padding(20.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.Edit,
                    contentDescription = null
                )
            }
        }
    }
}