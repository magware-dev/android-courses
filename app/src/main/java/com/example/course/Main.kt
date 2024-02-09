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
import com.example.course.course2.DiceApp
import com.example.course.course2.GalleryApp
import com.example.course.course2.LemonadeApp
import com.example.course.course2.TipApp
import com.example.course.ui.theme.CourseTheme

class Main : ComponentActivity() {

    enum class Route {
        Home,
        Course1First,
        Course1Second,
        Course1BusinessCard,
        Course1Grid,
        Course1TasksCompleted,
        Course2DiceApp,
        Course2LemonadeApp,
        Course2TipApp,
        Course2GalleryApp,
    }

    private data class App(
        val title: String,
        val composable: @Composable () -> Unit
    )

    companion object {
        private val APPS = mapOf(
            Route.Course1First to App("Course 1 / first") { First("Android") },
            Route.Course1Second to App("Course 1 / second") { Second() },
            Route.Course1BusinessCard to App("Course 1 / business-card") { BusinessCard() },
            Route.Course1Grid to App("Course 1 / grid") { Grid() },
            Route.Course1TasksCompleted to App("Course 1 / tasks-completed") { TasksCompleted() },

            Route.Course2LemonadeApp to App("Course 2 / Lemonade") { LemonadeApp().Lemonade() },
            Route.Course2DiceApp to App("Course 2 / Dice") { DiceApp().DiceWithButtonAndImage() },
            Route.Course2TipApp to App("Course 2 / Tip") { TipApp().Tip() },
            Route.Course2GalleryApp to App("Course 2 / Gallery") { GalleryApp().Gallery() },
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CourseTheme {
                View()
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun ViewPreview() {
        CourseTheme {
            View()
        }
    }

    @Composable
    private fun View() {
        var currentApp: Route by remember { mutableStateOf(Route.Home) }
        if (currentApp == Route.Home) {
            Home { currentApp = it }
        } else {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
            ) {
                Button(
                    onClick = { currentApp = Route.Home },
                    modifier = Modifier.padding(8.dp)
                ) {
                    Text("return to app selector")
                }
                Surface(Modifier.fillMaxSize()) {
                    APPS[currentApp]!!.composable()
                }

            }
        }
    }

    @Composable
    private fun Home(navigate: (Route) -> Unit) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            LazyColumn {
                items(APPS.entries.toList()) {
                    Button(onClick = { navigate(it.key) }) {
                        Text(text = it.value.title)
                    }
                }
            }
        }
    }

}
