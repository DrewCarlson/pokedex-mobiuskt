package pokedex.ui.home.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Composable
private fun AnimatedPlaceholderTextField() {
    val words = listOf("Pokémon", "Items", "Moves", "Characters")
    val wordIndex = remember { mutableStateOf(0) }

    LaunchedEffect(Unit) {
        while (true) {
            delay(2000)
            wordIndex.value = (wordIndex.value + 1) % words.size
        }
    }
    val containerColor = MaterialTheme.colorScheme.surface.copy(alpha = .2f)

    TextField(
        value = "",
        onValueChange = {},
        placeholder = {
            Row(modifier = Modifier.height(IntrinsicSize.Min)) {
                Text(text = "Search ")
                AnimatedContent(
                    wordIndex.value,
                    transitionSpec = {
                        slideInVertically { 40 } togetherWith slideOutVertically { -40 }
                    },
                    modifier = Modifier.width(IntrinsicSize.Max)
                ) { index ->
                    Text(text = words[index])
                }
            }
        },
        singleLine = true,
        leadingIcon = {
            IconButton(
                onClick = {}
            ) {
                Icon(Icons.Rounded.Search, contentDescription = "Search Pokemon")
            }
        },
        colors = TextFieldDefaults.colors(
            focusedContainerColor = containerColor,
            unfocusedContainerColor = containerColor,
            disabledContainerColor = containerColor,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            focusedLeadingIconColor = MaterialTheme.colorScheme.surface,
            unfocusedLeadingIconColor = MaterialTheme.colorScheme.surface,
            focusedPlaceholderColor = MaterialTheme.colorScheme.surface,
            unfocusedPlaceholderColor = MaterialTheme.colorScheme.surface,
        ),
        shape = MaterialTheme.shapes.extraLarge,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 20.dp),
    )
}
