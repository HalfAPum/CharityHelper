package com.narvatov.planthelper.models.remote.proposal

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
import com.narvatov.planthelper.models.remote.Comment
import com.narvatov.planthelper.ui.WeightedSpacer
import com.narvatov.planthelper.ui.navigation.UiNavigationEventPropagator
import com.narvatov.planthelper.ui.theme.Shapes

@Composable
fun CommentItem(comment: Comment) {
    Card(
        modifier = Modifier.padding(vertical = 5.dp).fillMaxWidth()
            .shadow(elevation = 5.dp, shape = Shapes.small).clip(Shapes.small)
    ) { with(comment) {
        Column (modifier = Modifier.padding(10.dp)) {
            Row {
                AsyncImage(
                    model = profileImageURL,
                    contentDescription = null,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.size(48.dp)
                )

                Text(
                    text = username,
                    style = MaterialTheme.typography.body2,
                    modifier = Modifier.padding(start = 8.dp).align(Alignment.CenterVertically)
                )
            }

            Text(
                text = text,
                style = MaterialTheme.typography.body2,
                modifier = Modifier.padding(top = 8.dp),
            )

            Row {
                WeightedSpacer()

                Text(
                    text = stringResource(R.string.crt_Datdd) + " " + creationDate.substringBefore('T'),
                    style = MaterialTheme.typography.caption
                )
            }
        }
    } }
}