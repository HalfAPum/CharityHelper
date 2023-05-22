package com.narvatov.planthelper.ui.screen.accont

import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.narvatov.planthelper.R
import com.narvatov.planthelper.data.utils.LoginStateHolder
import com.narvatov.planthelper.data.utils.SignInState
import com.narvatov.planthelper.ui.navigation.BottomNavigation
import com.narvatov.planthelper.ui.navigation.UiNavigationEventPropagator.navigate
import com.narvatov.planthelper.ui.navigation.UiNavigationEventPropagator.popBack

@Composable
fun Account() = with(LoginStateHolder.signInState.signInData!!) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier.padding(horizontal = 20.dp).fillMaxSize().verticalScroll(scrollState),
    ) {
        Text(
            text = "$firstName $secondName",
            fontSize = 20.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally).padding(top = 40.dp)
        )

        AsyncImage(
            model = profileImageUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxWidth(0.6F).padding(top = 20.dp).align(Alignment.CenterHorizontally)
        )

        Text(
            text = stringResource(R.string.contact_info),
            fontSize = 20.sp,
            modifier = Modifier.padding(top = 40.dp)
        )

        Text(
            text = stringResource(R.string.represents) + " " + companyName,
            modifier = Modifier.padding(top = 10.dp)
        )

        Text(
            text = stringResource(R.string.contact_phone) + " " + telephone,
            modifier = Modifier.padding(top = 10.dp)
        )

        Text(
            text = stringResource(R.string.contact_email) + " " + email,
            modifier = Modifier.padding(top = 10.dp)
        )

        if (address != null) {
            Text(
                text = stringResource(R.string.address),
                fontSize = 20.sp,
                modifier = Modifier.padding(top = 40.dp)
            )

            Text(
                text = stringResource(R.string.citydd) + " " + address.city,
                modifier = Modifier.padding(top = 10.dp)
            )

            Text(
                text = stringResource(R.string.districtdd) + " " + address.district,
                modifier = Modifier.padding(top = 10.dp)
            )

            Text(
                text = stringResource(R.string.home_locationdd) + " " + address.homeLocation,
                modifier = Modifier.padding(top = 10.dp)
            )

            Text(
                text = stringResource(R.string.regiondd) + " " + address.region,
                modifier = Modifier.padding(top = 10.dp)
            )
        }

        Button(
            onClick = {
                LoginStateHolder.signInState = SignInState.None

                popBack()

                navigate(BottomNavigation.Account)
            },
            modifier = Modifier.padding(top = 20.dp).align(Alignment.CenterHorizontally)
        ) {
            Text(
                text = stringResource(R.string.log_out),
                fontSize = 20.sp,
                modifier = Modifier.padding(horizontal = 32.dp)
            )
        }

        Spacer(Modifier.height(20.dp))

    }
}