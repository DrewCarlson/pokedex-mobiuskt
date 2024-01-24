package pokedex.ui.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.drewcarlson.`shared-ui`.generated.resources.Res
import org.jetbrains.compose.resources.painterResource
import pokedex.ui.home.state.CategoryState

@Composable
internal fun CategoryButton(
    onClick: () -> Unit,
    categoryState: CategoryState,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .clip(MaterialTheme.shapes.medium)
            .background(categoryState.startColor)
            .height(70.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .clickable(onClick = onClick)
                .fillMaxSize()
        ) {
            Text(
                text = categoryState.title,
                color = Color.White,
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold,
                ),
                modifier = Modifier.padding(16.dp)
            )

            Spacer(Modifier.weight(1f))
            /*Box(
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier
                        .scale(1f, .5f)
                        .offset(y = 40.dp)
                        .size(40.dp)
                        .background(
                            Brush.radialGradient(
                                listOf(
                                    Black.copy(alpha = .3f),
                                    Color.Transparent
                                ),
                            )
                        )
                )
            }*/
        }

        Image(
            painter = painterResource(Res.drawable.pokeballbackground),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(120.dp)
                .offset(x = 40.dp)
                .align(Alignment.CenterEnd)
        )
    }
}