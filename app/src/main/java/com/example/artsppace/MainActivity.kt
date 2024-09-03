package com.example.artsppace

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.window.core.layout.WindowSizeClass
import androidx.window.core.layout.WindowWidthSizeClass
import com.example.artsppace.ui.theme.ArtSppaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArtSppaceTheme {
                CatShowroomApp()
            }
        }
    }
}

class Artwork(val image: Int, val text: String, val name: String)

@Composable
fun CatShowroomApp(
    windowSizeClass: WindowSizeClass = currentWindowAdaptiveInfo().windowSizeClass,
    modifier: Modifier = Modifier
) {
    Log.i("windowWidthSizeClass", windowSizeClass.windowWidthSizeClass.toString())
    Log.i("windowHeightSizeClass", windowSizeClass.windowHeightSizeClass.toString())

    val expandedWidthScreen = windowSizeClass.windowWidthSizeClass == WindowWidthSizeClass.EXPANDED

    var currentArtworkIndex by remember { mutableIntStateOf(0) }

    val artworks = listOf(
        Artwork(
            image = R.drawable.cat1,
            text = stringResource(R.string.catFlowerTitle),
            name = stringResource(
                R.string.theGOAT
            )
        ),
        Artwork(
            image = R.drawable.cat2,
            text = stringResource(R.string.aggressiveCat),
            name = stringResource(
                R.string.enrique3DSolis
            )
        ),
        Artwork(
            image = R.drawable.cat3,
            text = stringResource(R.string.thinkingKillerCatTitle),
            name = stringResource(
                R.string.theScrum
            )
        ),
    )

    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        ImageSection(
            image = artworks[currentArtworkIndex].image,
            modifier = modifier
                .weight(3f)
                .fillMaxSize(fraction = if(expandedWidthScreen) 0.8f else 1f)
                .align(alignment = Alignment.CenterHorizontally)
        )
        ImageInfoSection(
            text = artworks[currentArtworkIndex].text,
            name = artworks[currentArtworkIndex].name,
            modifier = modifier
                .weight(1f)
                .fillMaxSize(fraction = if(expandedWidthScreen) 0.5f else 1f)
                .align(alignment = Alignment.CenterHorizontally)
        )
        ButtonsSection(
            onNext = {
                currentArtworkIndex =
                    if (currentArtworkIndex == artworks.size - 1) 0 else currentArtworkIndex + 1
            },
            onPrevious = {
                currentArtworkIndex =
                    if (currentArtworkIndex == 0) artworks.size - 1 else currentArtworkIndex - 1
            },
            modifier = modifier
                .weight(1f)
                .padding(bottom = if(expandedWidthScreen) 16.dp else 0.dp)
        )
    }
}

@Composable
fun ImageSection(modifier: Modifier = Modifier, image: Int) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Surface(
            shadowElevation = 32.dp,
            modifier = modifier.padding(
                horizontal = 32.dp,
                vertical = 64.dp
            )
        ) {
            Image(
                painter = painterResource(id = image),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = modifier
                    .padding(24.dp)
            )
        }
    }
}

@Composable
fun ImageInfoSection(modifier: Modifier = Modifier, text: String, name: String) {
    val subtitleText = buildAnnotatedString {
        pushStyle(SpanStyle(fontWeight = FontWeight.Bold, fontSize = 16.sp))
        append(name)
        append(" ")
        pushStyle(SpanStyle(fontWeight = FontWeight.Thin))
        append("(circa 2023)")
        toAnnotatedString()
    }

    Surface(
        color = Color.LightGray,
        modifier = modifier.padding(horizontal = 32.dp),
    ) {
        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center,
            modifier = modifier.padding(start = 16.dp)
        ) {
            Text(
                text = text,
                style = TextStyle(
                    fontWeight = FontWeight.Normal,
                    fontSize = 24.sp,
                ),
                overflow = TextOverflow.Ellipsis,
                maxLines = 2,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = subtitleText)
        }
    }
}

@Composable
fun ButtonsSection(modifier: Modifier = Modifier, onNext: () -> Unit, onPrevious: () -> Unit) {
    Row(
        modifier = modifier
            .wrapContentSize(align = Alignment.Center)
    ) {
        Button(
            onClick = onPrevious,
            modifier = modifier.wrapContentSize(align = Alignment.Center)
        ) {
            Text(text = "Previous")
        }
        Button(
            onClick = onNext,
            modifier = modifier.wrapContentSize(align = Alignment.Center)
        ) {
            Text(text = "Next")
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    ArtSppaceTheme {
        CatShowroomApp()
    }
}