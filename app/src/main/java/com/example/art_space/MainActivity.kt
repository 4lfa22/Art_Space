package com.example.art_space

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.art_space.ui.theme.Art_SpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Art_SpaceTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    cambiarImagen()
                }
            }
        }
    }
}

@Composable
fun cambiarImagen(modifier: Modifier = Modifier) {
    val firstArtwork = R.drawable.flower
    val secondArtwork = R.drawable.great
    val thirdArtwork = R.drawable.mona
    val fourthArtwork = R.drawable.starry

    var title by remember {
        mutableStateOf(R.string.flower)
    }

    var year by remember {
        mutableStateOf(R.string.flower_year)
    }

    var currentArtwork by remember {
        mutableStateOf(firstArtwork)
    }

    var imageResource by remember {
        mutableStateOf(currentArtwork)
    }

    Column(
        modifier = modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ArtworkDisplay(currentArtwork = currentArtwork)
        Spacer(modifier = modifier.size(16.dp))
        ArtworkTitle(title = title, year = year)
        Spacer(modifier = modifier.size(25.dp))
        Row(
            modifier = modifier.padding(horizontal = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally)
        ) {
            // Previous Button
            Button(
                onClick = {
                    when (currentArtwork) {
                        firstArtwork -> {
                            currentArtwork = fourthArtwork
                            title = R.string.starry_night
                            year = R.string.starry_night_year
                        }
                        secondArtwork -> {
                            currentArtwork = firstArtwork
                            title = R.string.flower
                            year = R.string.flower_year
                        }
                        thirdArtwork -> {
                            currentArtwork = secondArtwork
                            title = R.string.great_wave
                            year = R.string.great_wave_year
                        }
                        else -> {
                            currentArtwork = thirdArtwork
                            title = R.string.mona_lisa
                            year = R.string.mona_lisa_year
                        }
                    }
                },

            ) {
                Text(
                    text = "Anterior",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = colorResource(id = R.color.purple_700)
                )
            }

            // Next Button
            Button(
                onClick = {
                    when (currentArtwork) {
                        firstArtwork -> {
                            currentArtwork = secondArtwork
                            title = R.string.great_wave
                            year = R.string.great_wave_year
                        }
                        secondArtwork -> {
                            currentArtwork = thirdArtwork
                            title = R.string.mona_lisa
                            year = R.string.mona_lisa_year
                        }
                        thirdArtwork -> {
                            currentArtwork = fourthArtwork
                            title = R.string.starry_night
                            year = R.string.starry_night_year
                        }
                        else -> {
                            currentArtwork = firstArtwork
                            title = R.string.flower
                            year = R.string.flower_year
                        }
                    }
                },
            ) {
                Text(
                    text = "Siguiente",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = colorResource(id = R.color.purple_700)
                )
            }
        }
    }
}

@Composable
fun ArtworkDisplay(
    modifier: Modifier = Modifier,
    @DrawableRes currentArtwork: Int
) {
    Image(
        painter = painterResource(currentArtwork),
        contentDescription = stringResource(id = R.string.flower),
        modifier = modifier.fillMaxWidth(),
        contentScale = ContentScale.FillWidth
    )
}

@Composable
fun ArtworkTitle(
    @StringRes title: Int,
    @StringRes year: Int
) {
    Column (
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Artwork title
        Text(
            text = stringResource(id = title),
            fontWeight = FontWeight.Bold,
            color = colorResource(id = R.color.purple_200),
            fontSize = 32.sp
        )

        // Artwork year
        Text(
            text = "Year: ${stringResource(id = year)}",
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = colorResource(id = R.color.purple_500)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Art_SpaceTheme {
        cambiarImagen()
    }
}

