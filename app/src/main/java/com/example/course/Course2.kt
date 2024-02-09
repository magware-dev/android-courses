package com.example.course

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.Modifier
import com.example.course.course2.DiceApp
import com.example.course.course2.GalleryApp
import com.example.course.course2.LemonadeApp
import com.example.course.course2.TipApp
import com.example.course.ui.theme.CourseTheme

private val lemonadeApp = LemonadeApp()
private val diceApp = DiceApp()
private val tipApp = TipApp()

class Course2 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CourseTheme {
//                LemonadeApp().Lemonade(Modifier.fillMaxSize())
//                DiceApp().DiceWithButtonAndImage(Modifier.fillMaxSize())
//                TipApp().Tip(Modifier.fillMaxSize())
//                GalleryApp().Gallery(Modifier.fillMaxSize())
            }
        }
    }
}
