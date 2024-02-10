package com.example.course.unit3

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.course.R
import com.example.course.ui.theme.AppTheme

class WoofApp {

    @Composable
    fun Screen(modifier: Modifier = Modifier) {
        LazyColumn(modifier) {
            items(dogs) {
                DogItem(
                    dog = it,
                    modifier = Modifier.padding(dimensionResource(R.dimen.padding_small))
                )
            }
        }
    }

}

data class Dog(
    @DrawableRes val imageResourceId: Int,
    @StringRes val name: Int,
    val age: Int,
    @StringRes val hobbies: Int
)

val dogs = listOf(
    Dog(R.drawable.koda, R.string.dog_name_1, 2, R.string.dog_description_1),
    Dog(R.drawable.lola, R.string.dog_name_2, 16, R.string.dog_description_2),
    Dog(R.drawable.frankie, R.string.dog_name_3, 2, R.string.dog_description_3),
    Dog(R.drawable.nox, R.string.dog_name_4, 8, R.string.dog_description_4),
    Dog(R.drawable.faye, R.string.dog_name_5, 8, R.string.dog_description_5),
    Dog(R.drawable.bella, R.string.dog_name_6, 14, R.string.dog_description_6),
    Dog(R.drawable.moana, R.string.dog_name_7, 2, R.string.dog_description_7),
    Dog(R.drawable.tzeitel, R.string.dog_name_8, 7, R.string.dog_description_8),
    Dog(R.drawable.leroy, R.string.dog_name_9, 4, R.string.dog_description_9)
)

@Composable
fun DogItem(
    dog: Dog,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(R.dimen.padding_small))
        ) {
            DogIcon(dog.imageResourceId)
            DogInformation(dog.name, dog.age)
        }
    }
}

@Composable
fun DogIcon(
    @DrawableRes dogIcon: Int,
    modifier: Modifier = Modifier
) {
    Image(
        modifier = modifier
            .size(dimensionResource(R.dimen.image_size))
            .padding(dimensionResource(R.dimen.padding_small))
            .clip(MaterialTheme.shapes.small),
        contentScale = ContentScale.Crop,
        painter = painterResource(dogIcon),

        // Content Description is not needed here - image is decorative, and setting a null content
        // description allows accessibility services to skip this element during navigation.

        contentDescription = null
    )
}

@Composable
fun DogInformation(
    @StringRes dogName: Int,
    dogAge: Int,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = stringResource(dogName),
            style = MaterialTheme.typography.displayMedium,
            modifier = Modifier.padding(top = dimensionResource(R.dimen.padding_small))
        )
        Text(
            text = stringResource(R.string.years_old, dogAge),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Preview
@Composable
fun WoofPreview() {
    AppTheme(useDarkTheme = false) {
        WoofApp().Screen()
    }
}

@Preview
@Composable
fun WoofDarkThemePreview() {
    AppTheme(useDarkTheme = true) {
        WoofApp().Screen()
    }
}

