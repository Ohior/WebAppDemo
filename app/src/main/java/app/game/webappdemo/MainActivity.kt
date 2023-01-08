package app.game.webappdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import app.game.webappdemo.model.ConnectionMessage
import app.game.webappdemo.model.WebConnectionButton
import app.game.webappdemo.ui.theme.WebAppDemoTheme
import app.game.webappdemo.utils.Const
import app.game.webappdemo.webplugins.configureRouting
import app.game.webappdemo.webplugins.configureTemplating
import io.ktor.server.engine.*
import io.ktor.server.netty.*

class MainActivity : ComponentActivity() {
    private lateinit var nettyEngine: NettyApplicationEngine
    private var isNettyEngineActive: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WebAppDemoTheme {
                HomePage()
            }
        }
    }


    @Composable
    private fun HomePage() {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            var isConnected by remember {
                mutableStateOf(isNettyEngineActive)
            }
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                ConnectionMessage(
                    message = if (isConnected) "You are connected to port ${Const.PORT} and address ${Const.ADDRESS}"
                    else "You are not connected to any network"
                )
                WebConnectionButton(isConnected) {
                    if (isNettyEngineActive) {
                        isNettyEngineActive = false
                        nettyEngine.stop()
                    } else {
                        nettyEngine = setWebConnection()
                        nettyEngine.start()
                    }
                    isConnected = isNettyEngineActive
                }
            }
        }
    }

    private fun setWebConnection(): NettyApplicationEngine {
        isNettyEngineActive = true
        return embeddedServer(Netty, port = Const.PORT) {
            configureRouting(this@MainActivity)
            configureTemplating()
        }
    }
}