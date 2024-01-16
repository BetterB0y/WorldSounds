package pl.polsl.worldsounds.models

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import pl.polsl.worldsounds.R

private const val POLISH_isO3Language = "pol"
private const val ENGLISH_isO3Language = "eng"

enum class Language(
    @DrawableRes val icon: Int,
    @StringRes val iconDescription: Int,
    val locale: String
) {
    ENGLISH(R.drawable.english, R.string.languageEnglish, "en"),
    POLISH(R.drawable.polish, R.string.languagePolish, "pl");
}

fun String.toLocale(): Language {
    return when (this) {
        POLISH_isO3Language -> Language.POLISH
        ENGLISH_isO3Language -> Language.ENGLISH
        else -> throw Exception("Unknown language")
    }
}