package com.example.course.course2

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.course.R

class LemonadeApp {
    enum class Phase(val text: String, @DrawableRes val image: Int) {
        TREE("Tap the lemon tree to select a lemon", R.drawable.lemon_tree),
        SQUEEZE("Keep tapping the lemon to squeeze it", R.drawable.lemon_squeeze),
        DRINK("Tap the lemonade to drink it", R.drawable.lemon_drink),
        RESTART("Tap the empty glass to start again", R.drawable.lemon_restart);

        fun next() = Phase.entries[(this.ordinal+1)%4]
    }

    @Preview(showBackground = true)
    @Composable
    fun Preview() {
        Lemonade(Modifier.fillMaxSize())
    }

    private val rnd: Int
        get() = (2..4).random()

    @Composable
    fun Lemonade(modifier: Modifier = Modifier) {
        Column (modifier) {
            Text(
                text = "Lemonade",
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFD3B624))
                    .padding(vertical = (4 * 4).dp)
            )

            var phase: Phase by remember { mutableStateOf(Phase.TREE) }
            var squeezeClicks: Int by remember { mutableIntStateOf(rnd) }
            TabArea(
                phase = phase,
                onClick = { phase = when {
                    phase == Phase.SQUEEZE && (squeezeClicks == 1) -> {
                        squeezeClicks = rnd
                        phase.next()
                    }
                    phase == Phase.SQUEEZE -> {
                        squeezeClicks--
                        phase
                    }
                    else -> phase.next()
                } },
                modifier = Modifier
                    .fillMaxSize()
            )
        }
    }

    @Composable
    private fun TabArea(
        phase: Phase,
        onClick: () -> Unit,
        modifier: Modifier = Modifier
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = modifier
        ) {
            Button(
                onClick = onClick,
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFB1E6E6))
            ) {
                Image(
                    painter = painterResource(phase.image),
                    contentDescription = null,
                    modifier = Modifier.padding(20.dp)
                )
            }
            Spacer(Modifier.height(16.dp))
            Text(text = phase.text, fontSize = 18.sp)
        }
    }

}