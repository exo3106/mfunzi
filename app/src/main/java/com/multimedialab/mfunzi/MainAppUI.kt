package com.multimedialab.mfunzi


import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.multimedialab.mfunzi.ui.theme.*

@Composable
fun HomeView(navigateToFeature: (Feature) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxSize(),

        ){
        Column {
            GreetingSection()
            ChipSection(chips = listOf("MZUMBE", "VLIRUOS", "MUSO","ABCD","MFUNZI"))
            VideoSection()
            FeatureSection(
                features = listOf(
                    Feature(
                        title = "CAMPUS LIFE",
                        R.drawable.ic_collage,
                    ),
                    Feature(
                        title = "MFUNZI FORUM",
                        R.drawable.ic_forum,

                        ),
                    Feature(
                        title = "ABCD LIBRARY",
                        R.drawable.ic_library,

                        ),
                    Feature(
                        title = "VLIR-UOS",
                        R.drawable.ic_vlir,

                        ),
                    Feature(
                        title = "SPORTS",
                        R.drawable.ic_sports,
                    ),
                    Feature(
                        title = "RESOURCES",
                        R.drawable.ic_resource,
                    )
                )
                , navigateToFeature = navigateToFeature)
        }
        BottomMenu(items = listOf(
            BottomMenuContent("Home", R.drawable.ic_home),
            BottomMenuContent("Forum", R.drawable.ic_forum),
            BottomMenuContent("Chat", R.drawable.ic_chat),
            BottomMenuContent("Profile", R.drawable.ic_profile),
        ), modifier = Modifier.align(Alignment.BottomCenter))
    }

}

@Composable
fun GreetingSection(
    name: String = "Exaud"
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Good morning, $name",
                style = MaterialTheme.typography.h6.copy(
                    color = ButtonBlue,
                    fontWeight = FontWeight.Black,
                )
            )
            Text(
                text = "We wish you have a good day!",
                style = MaterialTheme.typography.body2
            )
        }
        Icon(
            painter = painterResource(id = R.drawable.ic_search),
            contentDescription = "Search",
            tint = Color.Black,
            modifier = Modifier.size(24.dp)
        )
    }
}
@Composable
fun ChipSection(
    chips: List<String>,

    ) {
    val selectedChipIndex = remember {
        mutableStateOf(0)
    }
    Column{
        LazyRow {
            items(chips.size) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .padding(start = 15.dp, top = 15.dp, bottom = 15.dp)
                        .background(
                            color = ButtonBlue
                        )
                        .clickable {
                            selectedChipIndex
                        }
                        .padding(15.dp)

                ) {
                    Text(
                        text = chips[it],
                        color = Color.White)
                }
            }
        }
    }
}
@Composable
fun VideoSection(
    color: Color = ButtonBlue
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .clip(RoundedCornerShape(10.dp))
            .padding(horizontal = 15.dp, vertical = 20.dp)
            .fillMaxWidth()
            .border(width = 3.dp, color = color)
    ) {
        Column(
            modifier = Modifier
                .padding(20.dp)
        ) {
            Text(
                text = "GET TO KNOW MFUNZI",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Black,
                    color = ButtonBlue
                )
            )
            Text(
                text ="Welcome to Mfunzi",
                color = DeepRed
            )
        }
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .padding(20.dp)
                .size(40.dp)
                .clip(CircleShape)
                .background(ButtonBlue)

        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_play),
                contentDescription = "Play",
                tint = Color.White,
                modifier = Modifier.size(32.dp)
            )
        }
    }
}
@Composable
fun BottomMenu(
    items: List<BottomMenuContent>,
    modifier: Modifier = Modifier,
    activeHighlightColor: Color = ButtonBlue,
    activeTextColor: Color = Color.White,
    inactiveTextColor: Color = AquaBlue,
    initialSelectedItemIndex: Int = 0
) {
    val selectedItemIndex = remember {
        mutableStateOf(initialSelectedItemIndex)
    }
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .background(ButtonBlue)
            .padding(15.dp)
    ) {
        items.forEachIndexed { index, item ->
            BottomMenuItem(
                item = item,
                activeHighlightColor = activeHighlightColor,
                activeTextColor = activeTextColor,
                inactiveTextColor = inactiveTextColor
            ) {
                selectedItemIndex
            }
        }
    }
}

@Composable
fun BottomMenuItem(
    item: BottomMenuContent,
    isSelected: Boolean = false,
    activeHighlightColor: Color = ButtonBlue,
    activeTextColor: Color = Color.White,
    inactiveTextColor: Color = AquaBlue,
    onItemClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.clickable {
            onItemClick()
        }
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .background(if (isSelected) activeHighlightColor else Color.Transparent)
                .padding(5.dp)
        ) {
            Icon(
                painter = painterResource(id = item.iconId),
                contentDescription = item.title,
                tint = if (isSelected) Teal200 else inactiveTextColor,
                modifier = Modifier.size(20.dp)
            )
        }
    }
}
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FeatureSection(features: List<Feature>,navigateToFeature: (Feature) -> Unit) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "Features",
            style = TextStyle(
                color = ButtonBlue,
                fontSize = 15.sp,
            ),
            modifier = Modifier.padding(10.dp)
        )
        LazyVerticalGrid(
            cells = GridCells.Fixed(3),
            modifier = Modifier.fillMaxHeight()
        ) {
            items(features.size) {
                FeatureItem(feature = features[it],navigateToFeature = navigateToFeature)
            }
        }
    }
}
@Composable
fun FeatureItem(
    feature: Feature,
    navigateToFeature: (Feature) -> Unit
) {
    BoxWithConstraints(
        modifier = Modifier
            .padding(7.5.dp)
            .aspectRatio(1f)
            .clip(RoundedCornerShape(10.dp))
            .clickable {
                navigateToFeature(feature)
            }
    ) {

        Icon(
            painter = painterResource(id = feature.iconId),
            contentDescription = feature.title,
            tint = DeepRedIcon,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .size(60.dp)
        )
        Text(
            text = feature.title,
            style = TextStyle(
                fontSize = 15.sp,
                fontWeight = FontWeight.Black,
                color = ButtonBlue
            ),
            lineHeight = 30.sp,
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }

}