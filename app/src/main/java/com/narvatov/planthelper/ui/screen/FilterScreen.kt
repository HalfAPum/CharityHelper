package com.narvatov.planthelper.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.narvatov.planthelper.R
import com.narvatov.planthelper.models.remote.SortBy
import com.narvatov.planthelper.models.remote.SortOrder
import com.narvatov.planthelper.models.remote.Status
import com.narvatov.planthelper.models.remote.TagTitle
import com.narvatov.planthelper.ui.navigation.UiNavigationEventPropagator.popBack
import com.narvatov.planthelper.ui.screen.proposal.CheckChoise
import com.narvatov.planthelper.ui.viewmodel.HelpListViewModel
import com.narvatov.planthelper.ui.viewmodel.ProposalListViewModel

@Composable
fun FilterScreen(
    viewModel: ProposalListViewModel
) = with(viewModel) {
    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        Spacer(Modifier.padding(top = 20.dp))

        SearchView(viewModel)

        Column(Modifier.padding(horizontal = 40.dp)) {
            Text(
                text = stringResource(R.string.sort_field),
                fontSize = 20.sp,
                modifier = Modifier.padding(top = 40.dp).align(Alignment.CenterHorizontally)
            )

            val kindsSF = listOf(stringResource(R.string.title), stringResource(R.string.creation_date))
            val (selectedSF, setSelectedSF) = remember { mutableStateOf("") }
            KindRadioGroup(
                mItems = kindsSF,
                selectedSF, setSelectedSF
            )

            Text(
                text = stringResource(R.string.sort_order),
                fontSize = 20.sp,
                modifier = Modifier.padding(top = 40.dp).align(Alignment.CenterHorizontally)
            )

            val kindsS = listOf(stringResource(R.string.asc), stringResource(R.string.desc))
            val (selectedS, setSelectedS) = remember { mutableStateOf("") }
            KindRadioGroup(
                mItems = kindsS,
                selectedS, setSelectedS
            )

            Text(
                text = stringResource(R.string.status_filter),
                fontSize = 20.sp,
                modifier = Modifier.padding(top = 40.dp).align(Alignment.CenterHorizontally)
            )

            val kindsStat = listOf(stringResource(R.string.active), stringResource(R.string.inactive), stringResource(
                            R.string.done), stringResource(R.string.blocked))
            val (selectedStat, setSelectedStat) = remember { mutableStateOf("") }
            KindRadioGroup(
                mItems = kindsStat,
                selectedStat, setSelectedStat
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
                },
                label = {
                    Text(text = "location1")
                },
                singleLine = true,
                modifier = Modifier.padding(top = 20.dp).fillMaxWidth()
            )

            var location2 by rememberSaveable { mutableStateOf("") }

            OutlinedTextField(
                value = location2,
                onValueChange = {
                    location2 = it
                },
                label = {
                    Text(text = "location2")
                },
                singleLine = true,
                modifier = Modifier.padding(top = 10.dp).fillMaxWidth()
            )

            var location3 by rememberSaveable { mutableStateOf("") }

            OutlinedTextField(
                value = location3,
                onValueChange = {
                    location3 = it
                },
                label = {
                    Text(text = "location3")
                },
                singleLine = true,
                modifier = Modifier.padding(top = 20.dp).fillMaxWidth()
            )

            var location4 by rememberSaveable { mutableStateOf("") }

            OutlinedTextField(
                value = location4,
                onValueChange = {
                    location4 = it
                },
                label = {
                    Text(text = "location4")
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
            val scholarsString = "Scholars"

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
            val placeToLiveString = "Place to live"

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

                    viewModel.search(
                        viewModel.searchSavedStr.value,
                        if (selectedS == "ASC")SortOrder.ASC else SortOrder.DESC,
                        if (selectedSF == "Title") SortBy.TITLE else SortBy.CREATION_DATE,
                        when (selectedStat) {
                            "Active" -> Status.Active
                            "Inactive" -> Status.Inactive
                            "Done" -> Status.Done
                            "Blocked" -> Status.Blocked
                            else -> null
                        },
                        tags = tags
                    )

                    popBack()
                },
                modifier = Modifier.padding(top = 10.dp).align(Alignment.CenterHorizontally)
            ) {
                Text(
                    text = stringResource(R.string.search),
                    fontSize = 20.sp,
                    modifier = Modifier.padding(horizontal = 36.dp)
                )
            }

        }

        Spacer(Modifier.height(250.dp))
    }
}

@Composable
fun FilterHelpScreen(
    viewModel: HelpListViewModel
) = with(viewModel) {
    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        Spacer(Modifier.padding(top = 20.dp))

        SearchView(viewModel)

        Column(Modifier.padding(horizontal = 40.dp)) {
            Text(
                text = stringResource(R.string.sort_field),
                fontSize = 20.sp,
                modifier = Modifier.padding(top = 40.dp).align(Alignment.CenterHorizontally)
            )

            val kindsSF = listOf(stringResource(R.string.title), stringResource(R.string.creation_date))
            val (selectedSF, setSelectedSF) = remember { mutableStateOf("") }
            KindRadioGroup(
                mItems = kindsSF,
                selectedSF, setSelectedSF
            )

            Text(
                text = stringResource(R.string.sort_order),
                fontSize = 20.sp,
                modifier = Modifier.padding(top = 40.dp).align(Alignment.CenterHorizontally)
            )

            val kindsS = listOf(stringResource(R.string.asc), stringResource(R.string.desc))
            val (selectedS, setSelectedS) = remember { mutableStateOf("") }
            KindRadioGroup(
                mItems = kindsS,
                selectedS, setSelectedS
            )

            Text(
                text = stringResource(R.string.status_filter),
                fontSize = 20.sp,
                modifier = Modifier.padding(top = 40.dp).align(Alignment.CenterHorizontally)
            )

            val kindsStat = listOf(stringResource(R.string.active), stringResource(R.string.inactive), stringResource(
                            R.string.done), stringResource(R.string.blocked))
            val (selectedStat, setSelectedStat) = remember { mutableStateOf("") }
            KindRadioGroup(
                mItems = kindsStat,
                selectedStat, setSelectedStat
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
                },
                label = {
                    Text(text = "location1")
                },
                singleLine = true,
                modifier = Modifier.padding(top = 20.dp).fillMaxWidth()
            )

            var location2 by rememberSaveable { mutableStateOf("") }

            OutlinedTextField(
                value = location2,
                onValueChange = {
                    location2 = it
                },
                label = {
                    Text(text = "location2")
                },
                singleLine = true,
                modifier = Modifier.padding(top = 10.dp).fillMaxWidth()
            )

            var location3 by rememberSaveable { mutableStateOf("") }

            OutlinedTextField(
                value = location3,
                onValueChange = {
                    location3 = it
                },
                label = {
                    Text(text = "location3")
                },
                singleLine = true,
                modifier = Modifier.padding(top = 20.dp).fillMaxWidth()
            )

            var location4 by rememberSaveable { mutableStateOf("") }

            OutlinedTextField(
                value = location4,
                onValueChange = {
                    location4 = it
                },
                label = {
                    Text(text = "location4")
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
            val scholarsString = "Scholars"

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
            val placeToLiveString = "Place to live"

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

                    viewModel.search(
                        viewModel.searchSavedStr.value,
                        if (selectedS == "ASC")SortOrder.ASC else SortOrder.DESC,
                        if (selectedSF == "Title") SortBy.TITLE else SortBy.CREATION_DATE,
                        when (selectedStat) {
                            "Active" -> Status.Active
                            "Inactive" -> Status.Inactive
                            "Done" -> Status.Done
                            "Blocked" -> Status.Blocked
                            else -> null
                        },
                        tags = tags
                    )

                    popBack()
                },
                modifier = Modifier.padding(top = 10.dp).align(Alignment.CenterHorizontally)
            ) {
                Text(
                    text = stringResource(R.string.search),
                    fontSize = 20.sp,
                    modifier = Modifier.padding(horizontal = 36.dp)
                )
            }

        }

        Spacer(Modifier.height(250.dp))
    }
}