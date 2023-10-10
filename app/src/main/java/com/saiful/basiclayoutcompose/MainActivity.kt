package com.saiful.basiclayoutcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.saiful.basiclayoutcompose.components.AlignBody
import com.saiful.basiclayoutcompose.components.FavoriteCollection
import com.saiful.basiclayoutcompose.components.HomeSection
import com.saiful.basiclayoutcompose.components.SearchBar
import com.saiful.basiclayoutcompose.ui.theme.BasicLayoutComposeTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BasicLayoutComposeTheme {
                val windowSizeClass = calculateWindowSizeClass(this)
                when (windowSizeClass.widthSizeClass) {
                    WindowWidthSizeClass.Compact -> {
                        Scaffold(
                            bottomBar = { SootheBottomNavigation() }
                        ) { padding ->
                            HomeScreen(Modifier.padding(padding))
                        }
                    }

                    WindowWidthSizeClass.Medium -> {
                        Scaffold(
                            bottomBar = { SootheBottomNavigation() }
                        ) { padding ->
                            HomeScreen(Modifier.padding(padding))
                        }
                    }

                    WindowWidthSizeClass.Expanded -> {
                        Row {
                            SootheNavigationRail()
                            HomeScreen()
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Column(modifier.verticalScroll(rememberScrollState())) {
        Spacer(modifier = Modifier.height(16.dp))
        SearchBar(modifier.padding(horizontal = 16.dp))
        HomeSection(title = "Align your Body") {
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(horizontal = 16.dp)
            ) {
                items(alignBody.size) {
                    AlignBody(alignBody[it])
                }
            }
        }
        HomeSection(title = "Favorite Collections") {
            LazyHorizontalGrid(
                rows = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.height(168.dp)
            ) {
                items(alignBody.size) {
                    FavoriteCollection(model = alignBody[it])
                }
            }
        }
        Spacer(Modifier.height(16.dp))
    }
}