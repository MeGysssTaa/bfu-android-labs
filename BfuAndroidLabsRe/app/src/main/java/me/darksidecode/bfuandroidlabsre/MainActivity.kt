package me.darksidecode.bfuandroidlabsre

import android.os.Bundle
import android.widget.ImageView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import me.darksidecode.bfuandroidlabsre.ui.theme.BfuAndroidLabsReTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BfuAndroidLabsReTheme {
                ClickerApp()
            }
        }
    }
}

@Composable
fun ClickerApp() {
    var points by remember { mutableStateOf(0) }
    var imageToggle by remember { mutableStateOf(false) }

    val pointsPerClick = when {
        points >= 50 -> 4
        points >= 20 -> 3
        points >= 10 -> 2
        else -> 1
    }

    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "Points: $points",
            color = MaterialTheme.colorScheme.onBackground
        )
        Text(
            text = "Combo: $pointsPerClick",
            color = MaterialTheme.colorScheme.onBackground
        )
        Button(onClick = {
            points += pointsPerClick
            imageToggle = !imageToggle
        }) {
            Text("--> CLICK HERE (+$10000) <--")
        }

        AndroidView(
            factory = { context ->
                ImageView(context).apply {
                    adjustViewBounds = true
                    scaleType = ImageView.ScaleType.CENTER_CROP
                }
            },
            update = { imageView ->
                val imageResource = if (imageToggle) R.drawable.image2 else R.drawable.image1
                imageView.setImageResource(imageResource)
            },
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ClickerAppPreview() {
    BfuAndroidLabsReTheme {
        ClickerApp()
    }
}
