package pl.polsl.worldsounds

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.ramcosta.composedestinations.DestinationsNavHost
import dagger.hilt.android.AndroidEntryPoint
import pl.polsl.worldsounds.screen.NavGraphs
import pl.polsl.worldsounds.screen.appCurrentDestinationAsState
import pl.polsl.worldsounds.screen.destinations.Destination
import pl.polsl.worldsounds.screen.startAppDestination
import pl.polsl.worldsounds.ui.resources.WorldSoundsTheme

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WorldSoundsTheme {
                val navController = rememberNavController()
                val currentDestination: Destination =
                    navController.appCurrentDestinationAsState().value
                        ?: NavGraphs.root.startAppDestination

                Scaffold { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)) {
                        DestinationsNavHost(
                            navGraph = NavGraphs.root,
                            navController = navController,
                        )
                    }
                }
            }
        }
    }
}
