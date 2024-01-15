package pl.polsl.worldsounds.models

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import pl.polsl.worldsounds.R

enum class Language(
    @DrawableRes val icon: Int,
    @StringRes val iconDescription: Int,
    val locale: String
) {
    ENGLISH(R.drawable.english, R.string.languageEnglish, "en"),
    POLISH(R.drawable.polish, R.string.languagePolish, "pl"),
}