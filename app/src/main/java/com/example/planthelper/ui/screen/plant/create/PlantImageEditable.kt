package com.example.planthelper.ui.screen.plant.create

import android.graphics.Bitmap
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.planthelper.R
import com.example.planthelper.models.ui.plant.create.CreatePlantUiState
import com.example.planthelper.utils.isNotBlank

@Composable
fun PlantImageEditable(
    createPlantUiState: CreatePlantUiState,
    modifier: Modifier = Modifier,
    onPhotoPicked: (Bitmap?) -> Unit,
) {
    Box(modifier = modifier) {
        val takePhotoAction = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.TakePicturePreview(),
            onResult = { onPhotoPicked(it) }
        )

        when {
            createPlantUiState.imageUrl.isNotBlank() -> {
                AsyncImage(
                    model = createPlantUiState.imageUrl,
                    contentDescription = null,
                    modifier = Modifier
                        .size(120.dp)
                        .clip(CircleShape)
                        .clickable { takePhotoAction.launch() },
                )
            }
            createPlantUiState.defaultImageUrl.isNotBlank() -> {
                AsyncImage(
                    //TODO MAKE SHORTER
                    model = ImageRequest.Builder(context = LocalContext.current)
                        .crossfade(true)
                        .data(createPlantUiState.defaultImageUrl)
                        .build(),
                    contentDescription = null,
                    modifier = Modifier
                        .size(120.dp)
                        .clip(CircleShape)
                        .clickable { takePhotoAction.launch() },
                )
            }
            else -> {
                Image(
                    painter = painterResource(R.drawable.ic_camera),
                    contentDescription = null,
                    modifier = Modifier
                        .size(120.dp)
                        .clip(CircleShape)
                        .background(Color(0xFFAAAAAA))
                        .clickable { takePhotoAction.launch() },
                    colorFilter = ColorFilter.tint(Color(0xFF336666)),
                )
            }
        }

        Image(
            painter = painterResource(R.drawable.ic_add_photo),
            contentDescription = null,
            modifier = Modifier
                .clip(CircleShape)
                .align(Alignment.BottomEnd)
                .clickable { takePhotoAction.launch() },
        )
    }
}