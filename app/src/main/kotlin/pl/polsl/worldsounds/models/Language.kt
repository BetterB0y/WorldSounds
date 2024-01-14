package pl.polsl.worldsounds.models

import androidx.annotation.DrawableRes
import pl.polsl.worldsounds.R

enum class Language(
    @DrawableRes val icon: Int,
    val iconDescription: String,
    val locale: String
) {
    ENGLISH(R.drawable.english, "English", "en"),
    POLISH(R.drawable.polish, "Polish", "pl"),
}