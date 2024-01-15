package pl.polsl.worldsounds.models

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import pl.polsl.worldsounds.R

enum class GameModeData(
    @DrawableRes val icon: Int,
    @StringRes val iconDescription: Int
) {
    OnePicture(R.drawable.music, R.string.iconOnePicture),
    OneSound(R.drawable.paw, R.string.iconOneSound)
}