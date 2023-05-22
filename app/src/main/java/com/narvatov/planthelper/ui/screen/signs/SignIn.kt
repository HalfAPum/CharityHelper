package com.narvatov.planthelper.ui.screen.signs

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.narvatov.planthelper.R
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.sp
import com.narvatov.planthelper.ui.navigation.SignUp
import com.narvatov.planthelper.ui.navigation.UiNavigationEventPropagator.navigate
import com.narvatov.planthelper.ui.viewmodel.SignInViewModel
import kotlinx.coroutines.Job
import org.koin.androidx.compose.getViewModel

@Composable
fun SignIn(
    viewModel: SignInViewModel = getViewModel()
) {
      Column(
            modifier = Modifier.padding(top = 120.dp).fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(R.drawable.applogo),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxWidth(0.7F).padding(bottom = 80.dp)
            )

            val signUpError = viewModel.errorSharedFlow.collectAsState(null)

            if (signUpError.value != null) {
                Text(
                    text = signUpError.value!!,
                    color = Color.Red,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(horizontal = 40.dp)
                )
            }

            var email by rememberSaveable { mutableStateOf("test@test.com") }

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

            var password by rememberSaveable { mutableStateOf("123123123") }

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

            Button(
                onClick = { viewModel.signIn(email, password) },
                modifier = Modifier.padding(top = 10.dp)
            ) {
                Text(
                    text = stringResource(R.string.signin),
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }

            Text(
                text = stringResource(R.string.signuptext),
                color = Color.Gray,
                modifier = Modifier.padding(top = 15.dp).clickable {
                    navigate(SignUp)
                }
            )
        }
}