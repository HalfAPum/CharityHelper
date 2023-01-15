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
    val stroke = Stroke(width = 10F,
        pathEffect = PathEffect.dashPathEffect(floatArrayOf(5f, 5f), 0f)
    )

    Box(
        modifier = modifier
            .height(220.dp)
            .aspectRatio(0.66F, true),
        contentAlignment = Alignment.Center
    ) {
        val isPhotoTaken = createPlantUiState.imageBitmap != null
                || createPlantUiState.defaultImageUrl.isNotBlank()

        if (!isPhotoTaken) {
            Canvas(modifier = Modifier.fillMaxSize()) {
                drawRoundRect(
                    color = PrimaryColor,
                    style = stroke,
                    cornerRadius = CornerRadius(20.dp.toPx())
                )
            }
        }

        val takePhotoAction = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.TakePicturePreview(),
            onResult = { onPhotoPicked(it) }
        )

        when {
            isPhotoTaken -> {
                AsyncImage(
                    model = createPlantUiState.imageBitmap ?: createPlantUiState.defaultImageUrl,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxHeight()
                        .clip(Shapes.large)
                        .clickable { takePhotoAction.launch() },
                )
            }
            else -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(Shapes.large)
                        .background(Color(0xFFD0FFD0))
                        .clickable { takePhotoAction.launch() },
                ) {
                    Image(
                        painter = painterResource(R.drawable.ic_add_photo),
                        contentDescription = null,
                        modifier = Modifier
                            .padding(top = 40.dp)
                            .align(Alignment.CenterHorizontally)
                    )

                    Text(
                        text = "Take a\nphoto",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal,
                        color = RegularBlack,
                        lineHeight = 24.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(top = 12.dp)
                            .align(Alignment.CenterHorizontally)
                    )
                }
            }
        }
    }
}