package com.narvatov.planthelper.ui.screen.signs

import android.app.Activity
import android.content.Intent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
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
import androidx.core.app.ActivityCompat.startActivityForResult
import com.narvatov.planthelper.R
import com.narvatov.planthelper.models.remote.sign.Address
import com.narvatov.planthelper.ui.main.PICK_PHOTO_CODE
import com.narvatov.planthelper.ui.viewmodel.SignUpViewModel
import org.koin.androidx.compose.getViewModel


@Composable
fun SignUpScreen(
    viewModel: SignUpViewModel = getViewModel(),
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier.fillMaxSize().verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = stringResource(R.string.account_data),
            fontSize = 20.sp,
            modifier = Modifier.padding(top = 20.dp)
        )

        var email by rememberSaveable { mutableStateOf("") }

        OutlinedTextField(
            value = email,
            onValueChange = {
                email = it
                viewModel.onFieldValueChanged()
            },
            label = {
                Text(text = stringResource(R.string.email))
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            modifier = Modifier.padding(top = 10.dp)
        )

        var password by rememberSaveable { mutableStateOf("") }

        OutlinedTextField(
            value = password,
            onValueChange = {
                password = it
                viewModel.onFieldValueChanged()
            },
            label = {
                Text(text = stringResource(R.string.password))
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = Modifier.padding(top = 10.dp)
        )

        Text(
            text = stringResource(R.string.personal_data),
            fontSize = 20.sp,
            modifier = Modifier.padding(top = 40.dp)
        )

        var firstName by rememberSaveable { mutableStateOf("") }

        OutlinedTextField(
            value = firstName,
            onValueChange = {
                firstName = it
                viewModel.onFieldValueChanged()
            },
            label = {
                Text(text = stringResource(R.string.first_name))
            },
            singleLine = true,
            modifier = Modifier.padding(top = 10.dp)
        )

        var secondName by rememberSaveable { mutableStateOf("") }

        OutlinedTextField(
            value = secondName,
            onValueChange = {
                secondName = it
                viewModel.onFieldValueChanged()
            },
            label = {
                Text(text = stringResource(R.string.second_name))
            },
            singleLine = true,
            modifier = Modifier.padding(top = 10.dp)
        )

        var telephone by rememberSaveable { mutableStateOf("") }

        OutlinedTextField(
            value = telephone,
            onValueChange = {
                telephone = it
                viewModel.onFieldValueChanged()
            },
            label = {
                Text(text = stringResource(R.string.telephone_number))
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            modifier = Modifier.padding(top = 10.dp)
        )

        var companyName by rememberSaveable { mutableStateOf("") }

        OutlinedTextField(
            value = companyName,
            onValueChange = {
                companyName = it
                viewModel.onFieldValueChanged()
            },
            label = {
                Text(text = stringResource(R.string.company_name))
            },
            singleLine = true,
            modifier = Modifier.padding(top = 10.dp)
        )

        Text(
            text = stringResource(R.string.address),
            fontSize = 20.sp,
            modifier = Modifier.padding(top = 40.dp)
        )

        var city by rememberSaveable { mutableStateOf("") }

        OutlinedTextField(
            value = city,
            onValueChange = {
                city = it
                viewModel.onFieldValueChanged()
            },
            label = {
                Text(text = stringResource(R.string.city))
            },
            singleLine = true,
            modifier = Modifier.padding(top = 10.dp)
        )

        var district by rememberSaveable { mutableStateOf("") }

        OutlinedTextField(
            value = district,
            onValueChange = {
                district = it
                viewModel.onFieldValueChanged()
            },
            label = {
                Text(text = stringResource(R.string.district))
            },
            singleLine = true,
            modifier = Modifier.padding(top = 10.dp)
        )

        var homeLocation by rememberSaveable { mutableStateOf("") }

        OutlinedTextField(
            value = homeLocation,
            onValueChange = {
                homeLocation = it
                viewModel.onFieldValueChanged()
            },
            label = {
                Text(text = stringResource(R.string.home_location))
            },
            singleLine = true,
            modifier = Modifier.padding(top = 10.dp)
        )

        var region by rememberSaveable { mutableStateOf("") }

        OutlinedTextField(
            value = region,
            onValueChange = {
                region = it
                viewModel.onFieldValueChanged()
            },
            label = {
                Text(text = stringResource(R.string.region))
            },
            singleLine = true,
            modifier = Modifier.padding(top = 10.dp)
        )

        val activity = LocalContext.current as Activity
        Button(
            onClick = {
                val intent = Intent(Intent.ACTION_GET_CONTENT)
                intent.type = "image/*"
                activity.startActivityForResult(intent, PICK_PHOTO_CODE)
            },
            modifier = Modifier.padding(top = 10.dp)
        ) {
            Text(
                text = stringResource(R.string.choose_photo),
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }

        val signUpError = viewModel.errorSharedFlow.collectAsState(null)

        if (signUpError.value != null) {
            Text(
                text = signUpError.value!!,
                color = Color.Red,
                fontSize = 20.sp,
                modifier = Modifier.padding(horizontal = 40.dp).padding(top = 20.dp)
            )
        }

        Button(
            onClick = {
                viewModel.signUp(
                    email, password, firstName, secondName, telephone, companyName,
                    Address(city, district, homeLocation, region)
                )
            },
            modifier = Modifier.padding(top = 10.dp)
        ) {
            Text(
                text = stringResource(R.string.signup),
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }

        Spacer(Modifier.height(250.dp))
    }
}