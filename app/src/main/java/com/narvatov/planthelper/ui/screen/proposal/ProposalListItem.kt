package com.narvatov.planthelper.ui.screen.proposal

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.narvatov.planthelper.R
import com.narvatov.planthelper.data.utils.NavigationParams
import com.narvatov.planthelper.models.remote.proposal.ProposalEvent
import com.narvatov.planthelper.ui.WeightedSpacer
import com.narvatov.planthelper.ui.navigation.ProposalDetails
import com.narvatov.planthelper.ui.navigation.UiNavigationEventPropagator.navigate
import com.narvatov.planthelper.ui.theme.Shapes

@Composable
fun ProposalListItem(proposal: ProposalEvent) = with(proposal) {
    Card(
        modifier = Modifier.padding(horizontal = 20.dp).fillMaxWidth()
            .shadow(elevation = 10.dp, shape = Shapes.medium).clip(Shapes.medium)
            .then(Modifier.clickable {
                NavigationParams.proposalDetailsItemId = proposal.id
                NavigationParams.proposalDetailsIdentifier = proposal.title + proposal.description
                navigate(ProposalDetails)
            })
    ) {
        Column (modifier = Modifier.padding(10.dp)) {
            AsyncImage(
                model = imageURL,
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier.fillMaxWidth().clip(Shapes.small)
                    .heightIn(max = 150.dp).align(Alignment.CenterHorizontally)
            )

            Text(
                text = title,
                style = MaterialTheme.typography.h5,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth().padding(top = 16.dp)
            )

            Text(
                text = description,
                style = MaterialTheme.typography.body1,
                modifier = Modifier.padding(top = 8.dp),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
            )

            Row(modifier = Modifier.padding(top = 12.dp)) {
                Text(
                    text = stringResource(R.string.status) + " " + status,
                    style = MaterialTheme.typography.caption
                )

                WeightedSpacer()

                Text(
                    text = stringResource(R.string.busy) + " " + busyRequestText,
                    style = MaterialTheme.typography.caption
                )
            }
        }
    }
}