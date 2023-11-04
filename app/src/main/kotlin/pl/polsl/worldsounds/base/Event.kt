package pl.polsl.worldsounds.base

import android.content.Context
import androidx.annotation.StringRes
import androidx.navigation.NavOptionsBuilder
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.popUpTo
import pl.polsl.worldsounds.screen.destinations.DirectionDestination

open class Event {
    data class Message(@StringRes private val textId: Int) : Event() {
        fun text(context: Context): String = context.getString(textId)
    }

    open class Navigation(private val _direction: DirectionDestination?) : Event() {
        open fun navigate(navigator: DestinationsNavigator) {
            navigator.navigate(_direction!!)
        }

        fun pushReplacement(
            navigator: DestinationsNavigator,
            onlyIfResumed: Boolean = false,
            builder: NavOptionsBuilder.() -> Unit = {},
        ) {
            navigator.navigate(_direction!!, onlyIfResumed) {
                builder.invoke(this)
                popUpTo(_direction) {
                    inclusive = true
                }
            }
        }

        object NavigateBack : Navigation(null) {
            override fun navigate(navigator: DestinationsNavigator) {
                navigator.popBackStack()
            }
        }
    }
}