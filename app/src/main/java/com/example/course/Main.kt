package com.example.course

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.course.ui.theme.AppTheme
import com.example.course.unit2.DiceApp
import com.example.course.unit2.GalleryApp
import com.example.course.unit2.LemonadeApp
import com.example.course.unit2.TipApp
import com.example.course.unit3.CourseGrid

class Main : ComponentActivity() {

    enum class App(val title: String, val Composable: @Composable () -> Unit) {
        Unit1First("Course 1 / first", { First("Android") }),
        Unit1Second("Course 1 / second", { Second() }),
        Unit1BusinessCard("Course 1 / business-card", { BusinessCard() }),
        Unit1Grid("Course 1 / grid", { Grid() }),
        Unit1TasksCompleted("Course 1 / tasks-completed", { TasksCompleted() }),
        Unit2LemonadeApp("Course 2 / Lemonade", { LemonadeApp().Lemonade() }),
        Unit2DiceApp("Course 2 / Dice", { DiceApp().DiceWithButtonAndImage() }),
        Unit2TipApp("Course 2 / Tip", { TipApp().Tip() }),
        Unit2GalleryApp("Course 2 / Gallery", { GalleryApp().Gallery() }),
        Unit3Grid("Course 3 / Grid", { CourseGrid().Screen() }),
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                View()
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun ViewPreview() {
        AppTheme {
            View()
        }
    }

    @Composable
    private fun View() {
        var currentApp: App? by remember { mutableStateOf(null) }
        if (currentApp == null) {
            Home { currentApp = it }
        } else {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
            ) {
                Button(
                    onClick = { currentApp = null },
                    modifier = Modifier.padding(8.dp)
                ) {
                    Text("return to app selector")
                }
                Surface(Modifier.fillMaxSize()) {
                    currentApp!!.Composable()
                }

            }
        }
    }

    @Composable
    private fun Home(navigate: (App) -> Unit) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            LazyColumn {
                items(App.entries.toList()) {
                    Button(onClick = { navigate(it) }) {
                        Text(text = it.title)
                    }
                }
            }
        }
    }

}
