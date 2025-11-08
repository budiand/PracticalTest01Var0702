package ro.pub.cs.systems.eim.firstapp.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Text
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.http.*

class MainActivity : ComponentActivity() {

    private val serverIp = "10.41.59.187"  // IP-ul telefonului

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WearApp {
                sendMessageToPhone()
            }
        }
    }

    private fun sendMessageToPhone() {
        val client = HttpClient(CIO)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                client.post("http://$serverIp:8080/message") {
                    contentType(ContentType.Text.Plain)
                    setBody("Salut de pe ceas!")
                }
                Log.d("WearApp", "Mesaj trimis la server!")
            } catch (e: Exception) {
                Log.e("WearApp", "Eroare la trimiterea mesajului: ${e.message}")
            }
        }
    }
}

@Composable
fun WearApp(onSendMessage: () -> Unit) {
    MaterialTheme {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Button(onClick = onSendMessage) {
                Text("Trimite la server")
            }
        }
    }
}

@Preview
@Composable
fun PreviewWearApp() {
    WearApp(onSendMessage = {})
}
