package com.narvatov.planthelper.ui.screen.notification

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.narvatov.planthelper.R
import com.narvatov.planthelper.data.utils.LoginStateHolder
import com.narvatov.planthelper.ui.ListSpacer
import com.narvatov.planthelper.ui.WeightedSpacer
import com.narvatov.planthelper.ui.navigation.BottomNavigation
import com.narvatov.planthelper.ui.navigation.UiNavigationEventPropagator.navigate
import com.narvatov.planthelper.ui.navigation.UiNavigationEventPropagator.popBack
import com.narvatov.planthelper.ui.theme.Shapes
import com.narvatov.planthelper.ui.viewmodel.NotificationViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun NotificationList(
    viewModel: NotificationViewModel = getViewModel()
) {

    val context = LocalContext.current
    fun String.readableEventType() = when(this) {
        "help" -> context.getString(R.string.help_event)
        "proposal-event" -> context.getString(R.string.proposal_event)
        else -> this
    }
    val notifications = LoginStateHolder.notificationList

    if (notifications.isNullOrEmpty()) {
        Text(
            text = stringResource(R.string.no_notfications),
            style = MaterialTheme.typography.h4,
            modifier = Modifier.padding(horizontal = 40.dp).padding(top = 200.dp)
        )

        return
    }

    LazyColumn(verticalArrangement = Arrangement.spacedBy(20.dp)) {
        ListSpacer()

        items(notifications) { notification ->
            Card(
                modifier = Modifier.padding(horizontal = 20.dp).fillMaxWidth()
                    .shadow(elevation = 10.dp, shape = Shapes.medium).clip(Shapes.medium)
                    .then(Modifier.clickable {
                        viewModel.checkNotification(notification.id)
                        val newList = notifications.map {
                            if (it.id == notification.id) it.apply { isRead = true }
                            else it
                        }

                        LoginStateHolder.notificationList = newList
                        popBack()
                        navigate(BottomNavigation.Notifications)
                    })
            ) {
                Column (modifier = Modifier.then(if (notification.isRead) Modifier
                    else Modifier.background(Color(0x33FFA500)).clip(Shapes.medium)).padding(10.dp)) {
                    Text(
                        text = notification.eventTitle,
                        style = MaterialTheme.typography.h4,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )

                    Text(
                        text = notification.text,
                        style = MaterialTheme.typography.h6,
                        modifier = Modifier.padding(top = 10.dp),
                    )

                    Row(modifier = Modifier.padding(top = 6.dp)) {
                        WeightedSpacer()

                        Text(
                            text = notification.eventType.readableEventType(),
                            style = MaterialTheme.typography.subtitle2
                        )
                    }
                }
            }
        }

        ListSpacer()
    }

}