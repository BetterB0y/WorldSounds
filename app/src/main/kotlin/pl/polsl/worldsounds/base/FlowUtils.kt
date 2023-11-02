package pl.polsl.worldsounds.base

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.launch

fun <T> Flow<T>.distinctForDuration(durationMillis: Long): Flow<T> = channelFlow {
    val distinctSet = mutableSetOf<T>()

    launch {
        collect { value ->
            if (!distinctSet.contains(value)) {
                distinctSet.add(value)
                send(value)
                launch {
                    delay(durationMillis)
                    distinctSet.remove(value)
                }
            }
        }
    }
}