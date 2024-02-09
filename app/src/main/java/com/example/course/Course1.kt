package com.example.course

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Phone
import androidx.compose.material.icons.rounded.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


private val businessCardTextColor = Color(0xFF174D0B)

@Composable
fun BusinessCard(modifier: Modifier = Modifier) {
    Surface(color = Color(0xFFA4A2A8)) {
        Column(
            modifier
                .padding(vertical = (4*12).dp)
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                Image(
                    painter = painterResource(R.drawable.android_logo),
                    contentDescription = null,
                    alpha = 0.7f,
                    modifier = Modifier
                        .size(width = 100.dp, height = 100.dp)
                        .background(Color(0xFF4D10AF))
                )
                Text(
                    text = "Jennifer Doe",
                    fontSize = 35.sp,
                    modifier = Modifier
                        .padding(8.dp)
                )
                Text(
                    text = "Android Developer Extraordinaire",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.SansSerif,
                    color = businessCardTextColor
                )
            }
            Column(
                //verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column() {
                    Row(contactRowModifier) {
                        ContactIcon(Icons.Rounded.Phone)
                        Text(text = "+11 (123) 444 555 666")
                    }
                    Row(contactRowModifier) {
                        ContactIcon(Icons.Rounded.Share)
                        Text(text = "@AndroidDev")
                    }
                    Row(contactRowModifier) {
                        ContactIcon(Icons.Rounded.Email)
                        Text(text = "email@domain.tld")
                    }
                }
            }
        }
    }
}

private val contactRowModifier = Modifier.padding(vertical = 8.dp);

@Composable
private fun ContactIcon(icon: ImageVector) {
    Icon(
        imageVector = icon,
        tint = businessCardTextColor,
        contentDescription = null,
        modifier = Modifier.padding(end = 16.dp)
    )
}


@Composable
fun Grid() {
    Column(Modifier.fillMaxWidth()) {
        Row (Modifier.weight(1f)) {
            Quadrant(
                title = stringResource(id = R.string.compose_text_title),
                desc = stringResource(id = R.string.compose_text_desc),
                Color(0xFFEADDFF),
                modifier = Modifier.weight(1f)
            )
            Quadrant(
                title = stringResource(id = R.string.compose_image_title),
                desc = stringResource(id = R.string.compose_image_desc),
                Color(0xFFD0BCFF),
                modifier = Modifier.weight(1f)
            )
        }
        Row (Modifier.weight(1f)) {
            Quadrant(
                title = stringResource(id = R.string.compose_row_title),
                desc = stringResource(id = R.string.compose_row_desc),
                Color(0xFFB69DF8),
                modifier = Modifier.weight(1f)
            )
            Quadrant(
                title = stringResource(id = R.string.compose_column_title),
                desc = stringResource(id = R.string.compose_column_desc),
                Color(0xFFF6EDFF),
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
private fun Quadrant(
    title: String,
    desc: String,
    bgColor: Color,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxSize()
            .background(bgColor)
            .padding(16.dp)
    ) {
        Text(text = title, fontWeight = FontWeight.Bold, modifier = Modifier.padding(bottom = 16.dp))
        Text(text = desc, textAlign = TextAlign.Justify)
    }
}

@Composable
fun TasksCompleted(modifier: Modifier = Modifier) {
    val image = painterResource(id = R.drawable.ic_task_completed)
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Image(painter = image, contentDescription = null)
        Text(text = stringResource(id = R.string.task_complete), fontWeight = FontWeight.Bold, modifier = Modifier.padding(top = 24.dp, bottom = 8.dp))
        Text(text = stringResource(id = R.string.task_sub), fontSize = 16.sp)
    }
}

@Composable
fun Second(modifier: Modifier = Modifier) {
    val image = painterResource(id = R.drawable.bg_compose_background)
    Column(modifier = modifier) {
        Image(
            painter = image,
            contentDescription = null,

            modifier = Modifier.fillMaxWidth()
        )
        DefaultText(res = R.string.title, fontSize = 24.sp, textAlign = null)
        DefaultText(res = R.string.short_desc)
        DefaultText(res = R.string.long_desc)
    }
}

@Composable
private fun DefaultText(
    @StringRes res: Int,
    modifier: Modifier = Modifier,
    textAlign: TextAlign? = TextAlign.Justify,
    fontSize: TextUnit = TextUnit.Unspecified,
) = Text(
    text = stringResource(id = res),
    fontSize = fontSize,
    textAlign = textAlign,
    modifier = modifier.padding(16.dp)
)

@Composable
fun First(name: String, modifier: Modifier = Modifier) {
    val image = painterResource(id = R.drawable.androidparty)
    Box(modifier) {
        Image(
            painter = image,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            alpha = 0.5F
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .fillMaxWidth()
                .align(Alignment.Center)
        ) {
            Surface(color = Color.Cyan) {
                Text(
                    text = stringResource(R.string.greeting_text, name),
                    textAlign = TextAlign.Center,
                    modifier = modifier
                        .padding(24.dp)
                )
            }
            Surface(color = Color.Gray) {
                Text(text = "meh".repeat(5), fontSize = 20.sp, modifier = modifier.padding(8.dp))
            }
        }
    }
}