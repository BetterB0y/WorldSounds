package pl.polsl.worldsounds.base

import android.content.Context
import androidx.annotation.StringRes
import androidx.navigation.NavOptionsBuilder
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.spec.Direction
import pl.polsl.worldsounds.screen.destinations.MainMenuScreenDestination

open class Event {
    open class Message(@StringRes private val textId: Int) : Event() {
        fun text(context: Context): String = context.getString(textId)
        override fun equals(other: Any?): Boolean {
            return if (other is Message) {
                textId == other.textId
            } else {
                super.equals(other)
            }
        }

        override fun hashCode(): Int {
            return textId
        }
    }

    open class Navigation(private val _direction: Direction?) : Event() {
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
                popUpTo(_direction.route) {
                    inclusive = true
                }
            }
        }

        object NavigateBack : Navigation(null) {
            override fun navigate(navigator: DestinationsNavigator) {
                navigator.popBackStack()
            }
        }

        object NavigateToMainMenu : Navigation(MainMenuScreenDestination) {
            override fun navigate(navigator: DestinationsNavigator) {
                pushReplacement(navigator)
            }
        }
    }
}