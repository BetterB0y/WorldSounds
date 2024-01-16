package pl.polsl.worldsounds.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun RoundIndicator(
    currentRoundIndex: Int,
    maxRounds: Int,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        for (index in 0 until maxRounds) {
            Box(
                modifier = Modifier
                    .padding(10.dp)
                    .size(18.dp)
                    .clip(CircleShape)
                    .background(if (index <= currentRoundIndex) MaterialTheme.colorScheme.tertiary else Color.Gray)
            )
        }
    }

}