package com.example.course.course2

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.course.R
import kotlinx.coroutines.launch


class GalleryApp {

    @Preview(showBackground = true)
    @Composable
    fun PreviewGallery() {
        Gallery(Modifier.fillMaxSize())
    }

    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    fun Gallery(modifier: Modifier = Modifier) {
        val pagerState = rememberPagerState(pageCount = { images.size })
        val coroutineScope = rememberCoroutineScope()

        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = modifier
        ) {
            HorizontalPager(state = pagerState) { page ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(fraction = 0.9f)
                ) {
                    Card(
                        elevation = CardDefaults.cardElevation(16.dp),
                        modifier = Modifier.padding((4 * 4).dp),
                    ) {
                        BoxWithConstraints {
                            Image(
                                painter = painterResource(images[page]),
                                contentDescription = null,
                                modifier = Modifier
                                    .heightIn(max = this.maxHeight.minus(100.dp))
                                    .padding((4 * 4).dp)
                            )
                        }

                    }
                    Spacer(Modifier.height(20.dp))
                    Column {
                        Text("Title")
                        Text("Desc")
                    }
                }
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxSize() // TODO broken in landscape mode
            ) {
                Button(
                    onClick = { coroutineScope.launch {pagerState.animateScrollToPage(pagerState.currentPage-1)}},
                    enabled = pagerState.currentPage-1 >= 0
                ) {
                    Text("< Previous")
                }
                Text("${pagerState.currentPage+1}/${pagerState.pageCount}")
                Button(
                    onClick = { coroutineScope.launch { pagerState.animateScrollToPage(pagerState.currentPage+1) } },
                    enabled = pagerState.currentPage+1 < pagerState.pageCount
                ) {
                    Text("Next >")
                }
            }
        }
    }

    companion object {
        private val images = R.drawable::class.java.declaredFields.map { it.getInt(null) }
    }

}