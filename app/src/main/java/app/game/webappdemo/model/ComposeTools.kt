package app.game.webappdemo.model

import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp


@Composable
fun WebConnectionButton(
    isConnected:Boolean,
    function: ()->Unit
) {
    var connected by remember {
        mutableStateOf(isConnected)
    }
    var webConnectionMsg by remember {
        mutableStateOf("StartWebConnection")
    }
    Button(
        modifier = Modifier.wrapContentSize(),
        onClick = {
            connected = !connected
            webConnectionMsg = if (connected) "StopWebConnection" else "StartWebConnection"
            function()
        }) {
        Text(text = webConnectionMsg)
    }
}

@Composable
fun ConnectionMessage(
    message: String
) {
    Text(
        text = message,
        fontFamily = FontFamily.Serif,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp
    )
}