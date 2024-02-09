package com.example.course.unit3

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.course.R

@Preview(showBackground = true)
@Composable
private fun Preview() {
    CourseGrid().Screen(Modifier.fillMaxSize())
}

class CourseGrid {

    companion object {
        private val topics = listOf(
            Topic("Architecture", 58, R.drawable.architecture),
            Topic("Crafts", 121, R.drawable.crafts),
            Topic("Business", 78, R.drawable.business),
            Topic("Culinary", 118,R.drawable.culinary),
            Topic("Design", 423, R.drawable.design),
            Topic("Fashion", 92, R.drawable.fashion),
            Topic("Film", 165, R.drawable.film),
            Topic("Gaming", 164, R.drawable.gaming),
            Topic("Lifestyle", 326, R.drawable.lifestyle),
            Topic("Music", 305, R.drawable.music),
            Topic("Painting", 212, R.drawable.painting),
            Topic("Photography", 321, R.drawable.photography),
            Topic("Tech", 118, R.drawable.tech),
        )
    }

    @Composable
    fun Screen(modifier: Modifier = Modifier) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = modifier
        ) {
            items(topics) {
                Topic(topic = it)
            }
        }
    }

}

private data class Topic(val title: String, val number: Int, @DrawableRes val image: Int)

@Composable
private fun Topic(topic: Topic) {
    Card(
        modifier = Modifier
            .height(68.dp)
    ) {
        Row {
            Image(
                painter = painterResource(topic.image),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.width(68.dp)
            )
            Column(
                modifier = Modifier.padding(
                    top = 16.dp,
                    end = 16.dp,
                    start = 16.dp
                )
            ) {
                Text(
                    text = topic.title,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_grain),
                        contentDescription = null,
                        modifier = Modifier.padding(end = 8.dp)
                    )
                    Text(
                        text = topic.number.toString(),
                        style = MaterialTheme.typography.labelMedium,
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun TopicPreview() {
    Topic(topic = Topic("Photography", 321, R.drawable.photography))
}