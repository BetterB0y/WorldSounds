package pl.polsl.worldsounds.ui.resources

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.unit.dp

object D {

    object Padding {
        val paddingSmall = 5.dp
        val paddingMedium = 8.dp
        val paddingBig = 10.dp
        val paddingDoubleBig = 20.dp
        val permissionPadding = 16.dp

        object Dialog {
            val fromScreenEdges = PaddingValues(horizontal = 100.dp)
            val content = PaddingValues(
                horizontal = 16.dp,
                vertical = 20.dp
            )
            val description = PaddingValues(vertical = 8.dp)
            val buttons = 16.dp
            val buttonsSpacer = 10.dp
        }
    }

    object Radius {
        val large = 10.dp
        val dialog = 22.dp
    }

    object Elevation {
        val default = 12.dp
    }
}