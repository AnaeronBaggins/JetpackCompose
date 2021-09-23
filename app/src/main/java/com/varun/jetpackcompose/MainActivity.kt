package com.varun.jetpackcompose

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.varun.jetpackcompose.ui.theme.JetpackComposeTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeTheme {
                MainView()
            }
        }
    }
}



data class Message(val author: String, val body: String)

@Preview
@Preview(
    uiMode = UI_MODE_NIGHT_YES,
    showBackground = true,
    name = "Dark Mode"
)
@Composable
fun PreviewConversation() {
    JetpackComposeTheme {
        ConversationHomeScreen(messages = SampleData.conversationSample)

    }
}