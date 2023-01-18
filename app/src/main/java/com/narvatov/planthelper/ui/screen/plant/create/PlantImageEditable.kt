package com.narvatov.planthelper.ui.screen.plant.create

import android.graphics.Bitmap
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.narvatov.planthelper.R
import com.narvatov.planthelper.models.ui.plant.create.CreatePlantUiState
import com.narvatov.planthelper.ui.WeightedSpacer
import com.narvatov.planthelper.ui.theme.PrimaryColor
import com.narvatov.planthelper.ui.theme.RegularBlack
import com.narvatov.planthelper.ui.theme.Shapes
import com.narvatov.planthelper.utils.isNotBlank

@Composable
fun PlantImageEditable(
    createPlantUiState: CreatePlantUiState,
    modifier: Modifier = Modifier,
    onPhotoPicked: (Bitmap?) -> Unit,
) {
    val isPhotoTaken = createPlantUiState.imageBitmap != null
            || createPlantUiState.defaultImageUrl.isNotBlank()

    val takePhotoAction = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicturePreview(),
        onResult = { onPhotoPicked(it) }
    )

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
    ) {
        when {
            isPhotoTaken -> {
                Box(
                    modifier = Modifier
                        .weight(1F)
                        .fillMaxWidth()
                        .aspectRatio(0.66F),
                ) {
                    AsyncImage(
                        model = createPlantUiState.imageBitmap
                            ?: createPlantUiState.defaultImageUrl,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(Shapes.large),
                    )
                }

                Spacer(modifier = Modifier.width(20.dp))

                ChooseArea(
                    text = "Edit\nthe photo",
                    image = R.drawable.ic_edit_big,
                    onClick = { takePhotoAction.launch() },
                    modifier = Modifier.weight(1F)
                )
            }
            else -> {
                WeightedSpacer()

                Spacer(modifier = Modifier.width(10.dp))

                ChooseArea(
                    text = "Take a\nphoto",
                    image = R.drawable.ic_add_photo,
                    onClick = { takePhotoAction.launch() },
                    modifier = Modifier.weight(2F)
                )

                Spacer(modifier = Modifier.width(10.dp))

                WeightedSpacer()
            }
        }
    }
}