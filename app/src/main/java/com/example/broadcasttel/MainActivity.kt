package com.example.broadcasttel

import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.broadcasttel.ui.theme.BroadcastTelTheme

class MainActivity : ComponentActivity() {
    private val REQUEST_CODE = 369
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //GET PERMISSION
        val permissions = arrayOf(
            android.Manifest.permission.READ_PHONE_STATE,
            android.Manifest.permission.RECEIVE_SMS,
            android.Manifest.permission.SEND_SMS,
            android.Manifest.permission.READ_CALL_LOG
        )
        if (!permissions.all { ContextCompat.checkSelfPermission(this, it) == PackageManager.PERMISSION_GRANTED }) {
            ActivityCompat.requestPermissions(this, permissions, REQUEST_CODE)
        }

        setContent {
            BroadcastTelTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }

    @Composable
    fun Greeting(name: String, modifier: Modifier = Modifier) {
        Text(
            text = "Hello $name!",
            modifier = modifier
        )
    }

    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
        BroadcastTelTheme {
            Greeting("Android")
        }
    }
}

