package com.narvatov.planthelper.models.remote.proposal

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardActions
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
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.narvatov.planthelper.R
import com.narvatov.planthelper.data.utils.LoginStateHolder
import com.narvatov.planthelper.models.remote.TagTitle
import com.narvatov.planthelper.ui.ListSpacer
import com.narvatov.planthelper.ui.WeightedSpacer
import com.narvatov.planthelper.ui.navigation.EditProposal
import com.narvatov.planthelper.ui.navigation.Transactions
import com.narvatov.planthelper.ui.navigation.UiNavigationEventPropagator.navigate
import com.narvatov.planthelper.ui.screen.proposal.CheckChoise
import com.narvatov.planthelper.ui.theme.Shapes
import com.narvatov.planthelper.ui.viewmodel.ProposalEventDetailsViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun ProposalDetails(
    viewModel: ProposalEventDetailsViewModel = getViewModel()
) = with(viewModel) {
    val proposal = proposalFlow.collectAsState(null)

    if (proposal.value == null) return@with

    Box {
        LazyColumn(modifier = Modifier.fillMaxSize().padding(horizontal = 20.dp)) {
            with(proposal.value!!) {
                item {
                    AsyncImage(
                        model = imageURL,
                        contentDescription = null,
                        contentScale = ContentScale.FillWidth,
                        modifier = Modifier.fillMaxWidth().clip(Shapes.medium).padding(top = 20.dp)
                    )

                    Text(
                        text = title,
                        style = MaterialTheme.typography.h4,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth().padding(top = 16.dp)
                    )

                    Text(
                        text = description,
                        fontSize = 18.sp,
                        style = MaterialTheme.typography.body1,
                        modifier = Modifier.padding(top = 16.dp),
                    )

                    Row(modifier = Modifier.padding(top = 16.dp)) {
                        Column() {
                            Text(
                                text = stringResource(R.string.authir) + " " + author.username,
                                style = MaterialTheme.typography.body2
                            )

                            Text(
                                text = stringResource(R.string.contact_phone) + " " + author.phone,
                                style = MaterialTheme.typography.body2,
                                modifier = Modifier.padding(top = 8.dp)
                            )
                        }

                        WeightedSpacer()

                        Column {
                            Text(
                                text = stringResource(R.string.status) + " " + status,
                                style = MaterialTheme.typography.body2
                            )

                            Text(
                                text = stringResource(R.string.busy) + " " + busyRequestText,
                                style = MaterialTheme.typography.body2,
                                modifier = Modifier.padding(top = 8.dp)
                            )
                        }
                    }
                    Row(modifier = Modifier.padding(top = 10.dp)) {
                        Text(
                            text = stringResource(R.string.crt_Datdd) + " " + creationDate.substringBefore(
                                ' '
                            ),
                            style = MaterialTheme.typography.caption
                        )

                        WeightedSpacer()

                        Text(
                            text = stringResource(R.string.cmptdd) + " " + competitionDate.substringBefore(
                                ' '
                            ),
                            style = MaterialTheme.typography.caption,
                        )
                    }

                    if (LoginStateHolder.isLoggedIn.not() && commentList.isEmpty()) return@item

                    Text(
                        text = stringResource(R.string.comments),
                        style = MaterialTheme.typography.h6,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp)
                    )
                }

                item {
                    if (LoginStateHolder.isLoggedIn.not() && commentList.isEmpty()) return@item

                    Column(
                        modifier = Modifier.background(
                            color = Color.LightGray,
                            shape = Shapes.medium
                        )
                            .fillMaxWidth().padding(vertical = 5.dp, horizontal = 10.dp)
                    ) {
                        for (comment in commentList) {
                            CommentItem(comment)
                        }

                        if (LoginStateHolder.isLoggedIn) {
                            var commentText by rememberSaveable { mutableStateOf("") }

                            OutlinedTextField(
                                value = commentText,
                                onValueChange = {
                                    commentText = it
                                },
                                label = {
                                    Text(text = stringResource(R.string.add_comment))
                                },
                                modifier = Modifier.padding(top = if (commentList.isNotEmpty()) 10.dp else 0.dp)
                                    .fillMaxWidth(),
                                colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.White)
                            )

                            Button(
                                onClick = {
                                    viewModel.addComment(id, commentText)
                                },
                                modifier = Modifier.padding(top = 5.dp)
                            ) {
                                Text(
                                    text = stringResource(R.string.sendcom),
                                    modifier = Modifier.fillMaxWidth()
                                )
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
                            modifier = Modifier.padding(top = 40.dp)
                                .align(Alignment.CenterHorizontally)
                        )

                        Text(
                            text = stringResource(R.string.location),
                            fontSize = 20.sp,
                            modifier = Modifier.padding(top = 40.dp)
                                .align(Alignment.CenterHorizontally)
                        )

                        val locationTag = tags.firstOrNull {
                            it.title == TagTitle.Location.title
                        }?.values
                        var location1 by rememberSaveable { mutableStateOf(locationTag?.getOrNull(0) ?:"") }

                        OutlinedTextFieldDisEnabled(
                            value = location1,
                            onValueChange = {
                                location1 = it
                            },
                            label = {
                                Text(text = "location1")
                            },
                            enabled = false,
                            singleLine = true,
                            modifier = Modifier.padding(top = 20.dp).fillMaxWidth()
                        )

                        var location2 by rememberSaveable { mutableStateOf(locationTag?.getOrNull(1) ?:"") }

                        OutlinedTextFieldDisEnabled(
                            value = location2,
                            onValueChange = {
                                location2 = it
                            },
                            label = {
                                Text(text = "location2")
                            },
                            enabled = false,
                            singleLine = true,
                            modifier = Modifier.padding(top = 10.dp).fillMaxWidth()
                        )

                        var location3 by rememberSaveable { mutableStateOf(locationTag?.getOrNull(2) ?:"") }

                        OutlinedTextFieldDisEnabled(
                            value = location3,
                            onValueChange = {
                                location3 = it
                            },
                            label = {
                                Text(text = "location3")
                            },
                            enabled = false,
                            singleLine = true,
                            modifier = Modifier.padding(top = 20.dp).fillMaxWidth()
                        )

                        var location4 by rememberSaveable { mutableStateOf(locationTag?.getOrNull(3) ?:"") }

                        OutlinedTextFieldDisEnabled(
                            value = location4,
                            onValueChange = {
                                location4 = it
                            },
                            label = {
                                Text(text = "location4")
                            },
                            enabled = false,
                            singleLine = true,
                            modifier = Modifier.padding(top = 10.dp).fillMaxWidth()
                        )

                        val ageGroupTag = tags.firstOrNull {
                            it.title == TagTitle.AgeGroup.title
                        }?.values ?: emptyList()
                        Text(
                            text = stringResource(R.string.age_group),
                            fontSize = 20.sp,
                            modifier = Modifier.padding(top = 40.dp)
                                .align(Alignment.CenterHorizontally)
                        )

                        val childrenString = stringResource(R.string.children)
                        val childrenChecked = remember { mutableStateOf(ageGroupTag.contains(childrenString)) }

                        CheckChoise(
                            checked = childrenChecked.value,
                            onCheckedChange = { childrenChecked.value = it },
                            label = childrenString,
                            enabled = false,
                            modifier = Modifier.padding(top = 20.dp)
                        )
                        val scholarsString = stringResource(R.string.scholars)

                        val scholarsChecked = remember { mutableStateOf(ageGroupTag.contains(scholarsString)) }

                        CheckChoise(
                            checked = scholarsChecked.value,
                            onCheckedChange = { scholarsChecked.value = it },
                            enabled = false,
                            label = scholarsString
                        )
                        val studentsString = stringResource(R.string.students)

                        val studentsChecked = remember { mutableStateOf(ageGroupTag.contains(studentsString)) }

                        CheckChoise(
                            checked = studentsChecked.value,
                            onCheckedChange = { studentsChecked.value = it },
                            enabled = false,
                            label = studentsString
                        )

                        val middleAgedString = stringResource(R.string.middle_aged)
                        val middleAgedChecked = remember { mutableStateOf(ageGroupTag.contains(middleAgedString)) }

                        CheckChoise(
                            checked = middleAgedChecked.value,
                            onCheckedChange = { middleAgedChecked.value = it },
                            enabled = false,
                            label = middleAgedString
                        )

                        val pensionersString = stringResource(R.string.pensioners)
                        val pensionersChecked = remember { mutableStateOf(ageGroupTag.contains(pensionersString)) }

                        CheckChoise(
                            checked = pensionersChecked.value,
                            onCheckedChange = { pensionersChecked.value = it },
                            enabled = false,
                            label = pensionersString
                        )

                        val topicTag = tags.firstOrNull {
                            it.title == TagTitle.Topic.title
                        }?.values ?: emptyList()
                        Text(
                            text = stringResource(R.string.topic),
                            fontSize = 20.sp,
                            modifier = Modifier.padding(top = 40.dp)
                                .align(Alignment.CenterHorizontally)
                        )

                        val foodString = stringResource(R.string.food)
                        val foodChecked = remember { mutableStateOf(topicTag.contains(foodString)) }

                        CheckChoise(
                            checked = foodChecked.value,
                            onCheckedChange = { foodChecked.value = it },
                            label = foodString,
                            enabled = false,
                            modifier = Modifier.padding(top = 20.dp)
                        )

                        val clothString = stringResource(R.string.cloth)
                        val clothChecked = remember { mutableStateOf(topicTag.contains(clothString)) }

                        CheckChoise(
                            checked = clothChecked.value,
                            onCheckedChange = { clothChecked.value = it },
                            label = clothString,
                            enabled = false,
                            modifier = Modifier.padding(top = 20.dp)
                        )

                        val peopleTendString = stringResource(R.string.people_tend)
                        val peopleTendChecked = remember { mutableStateOf(topicTag.contains(peopleTendString)) }

                        CheckChoise(
                            checked = peopleTendChecked.value,
                            onCheckedChange = { peopleTendChecked.value = it },
                            label = peopleTendString,
                            enabled = false,
                            modifier = Modifier.padding(top = 20.dp)
                        )

                        val housingRegistrationString = stringResource(R.string.housing_registration)
                        val housingRegistrationChecked = remember { mutableStateOf(topicTag.contains(housingRegistrationString)) }

                        CheckChoise(
                            checked = housingRegistrationChecked.value,
                            onCheckedChange = { housingRegistrationChecked.value = it },
                            label = housingRegistrationString,
                            enabled = false,
                            modifier = Modifier.padding(top = 20.dp)
                        )

                        val placeToLiveString = stringResource(R.string.place_tolive)
                        val placeToLiveChecked = remember { mutableStateOf(topicTag.contains(placeToLiveString)) }

                        CheckChoise(
                            checked = placeToLiveChecked.value,
                            onCheckedChange = { placeToLiveChecked.value = it },
                            label = placeToLiveString,
                            enabled = false,
                            modifier = Modifier.padding(top = 20.dp)
                        )

                        val jobString = stringResource(R.string.job)
                        val jobChecked = remember { mutableStateOf(topicTag.contains(jobString)) }

                        CheckChoise(
                            checked = jobChecked.value,
                            onCheckedChange = { jobChecked.value = it },
                            label = jobString,
                            enabled = false,
                            modifier = Modifier.padding(top = 20.dp)
                        )
                    }
                }

                ListSpacer(modifier = Modifier.height(40.dp))
            }
        }

        if (LoginStateHolder.isLoggedIn) {
            FloatingActionButton(
                onClick = { navigate(Transactions) },
                modifier = Modifier.align(Alignment.TopEnd).padding(20.dp)
            ) {
                Text(text = stringResource(R.string.transactions), fontWeight = FontWeight.Bold, modifier = Modifier.padding(horizontal = 20.dp))
            }
        }

        if (LoginStateHolder.isLoggedIn && proposal.value?.author?.id == LoginStateHolder.signInState.signInData?.id) {
            FloatingActionButton(
                onClick = { navigate(EditProposal) },
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

@Composable
fun OutlinedTextFieldDisEnabled(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    textStyle: TextStyle = LocalTextStyle.current,
    label: @Composable (() -> Unit)? = null,
    placeholder: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    isError: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = false,
    maxLines: Int = Int.MAX_VALUE,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    shape: Shape = MaterialTheme.shapes.small,
    colors: TextFieldColors = TextFieldDefaults.outlinedTextFieldColors()
) {
    OutlinedTextField(value, if (enabled) onValueChange else {s ->}, modifier, true, readOnly, textStyle, label, placeholder, leadingIcon, trailingIcon, isError, visualTransformation, keyboardOptions, keyboardActions, singleLine, maxLines, interactionSource, shape, colors)
}