package com.example.artsppace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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

@Composable
fun CatShowroomApp(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        ImageSection(
            modifier = modifier
                .weight(3f)
                .fillMaxSize()
        )
        ImageInfoSection(
            modifier = modifier
                .weight(1f)
                .fillMaxSize()
        )
        ButtonsSection(
            modifier = modifier
                .weight(1f)
        )
    }
}

@Composable
fun ImageSection(modifier: Modifier = Modifier) {
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
                painter = painterResource(id = R.drawable.cat1),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = modifier
                    .padding(24.dp)
            )
        }
    }
}

@Composable
fun ImageInfoSection(modifier: Modifier = Modifier) {
    val subtitleText = buildAnnotatedString {
        pushStyle(SpanStyle(fontWeight = FontWeight.Bold, fontSize = 16.sp))
        append("José Sanchez")
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
                text = "Impressive white car surrounded with flowers asdasd  asdasd asdasd asdasdasd  asdasdasdasdasda asdasd    asdasd",
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
fun ButtonsSection(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .wrapContentSize(align = Alignment.Center)
    ) {
        Button(
            onClick = { /*TODO*/ },
            modifier = modifier.wrapContentSize(align = Alignment.Center)
        ) {
            Text(text = "Previous")
        }
        Button(
            onClick = { /*TODO*/ },
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