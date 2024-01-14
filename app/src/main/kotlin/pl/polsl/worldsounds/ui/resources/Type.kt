package pl.polsl.worldsounds.ui.resources

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import pl.polsl.worldsounds.R

@Suppress("SpellCheckingInspection")
val Signika = FontFamily(
    Font(R.font.signika_regular),
    Font(
        R.font.signika_semibold,
        FontWeight.SemiBold
    ),
    Font(
        R.font.signika_bold,
        FontWeight.Bold
    ),
    Font(
        R.font.signika_light,
        FontWeight.Light
    ),
    Font(
        R.font.signika_medium,
        FontWeight.Medium
    )
)

val AlphaEcho = FontFamily(
    Font(R.font.alpha_echo),
)

val Typography = Typography(
    titleLarge = TextStyle(
        fontFamily = AlphaEcho,
        fontSize = 55.sp,
        color = LightColorScheme.tertiary
    ),
    titleMedium = TextStyle(
        fontFamily = AlphaEcho,
        fontSize = 40.sp,
        color = LightColorScheme.tertiary
    ),
    bodyLarge = TextStyle(
        fontFamily = Signika,
        fontSize = 18.sp,
        lineHeight = 24.sp,
    ),
    bodyMedium = TextStyle(
        fontFamily = Signika,
        fontSize = 16.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.25.sp
    ),
)