package pl.polsl.worldsounds.base

import android.content.Context
import androidx.annotation.StringRes
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.spec.Direction

open class Event {
    data class Message(@StringRes private val textId: Int) : Event() {
        fun text(context: Context): String = context.getString(textId)
    }

    open class Navigation(private val _direction: Direction?) : Event() {
        open fun navigate(navigator: DestinationsNavigator) {
            navigator.navigate(_direction!!)
        }

        object NavigateBack : Navigation(null) {
            override fun navigate(navigator: DestinationsNavigator) {
                navigator.popBackStack()
            }
        }
    }
}