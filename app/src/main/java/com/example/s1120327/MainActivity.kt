package com.example.s1120327

import android.app.Activity
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectDragGesturesAfterLongPress
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.s1120327.R
import com.example.s1120327.ui.theme.S1120327Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            S1120327Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    val colors = listOf(
        Color(0xff95fe95),
        Color(0xfffdca0f),
        Color(0xfffea4a4),
        Color(0xffa5dfed)
    )

    var currentColorIndex by remember { mutableStateOf(0) }
    val backgroundColor by animateColorAsState(
        targetValue = colors[currentColorIndex],
        animationSpec = tween(durationMillis = 1000)
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .pointerInput(Unit) {
                detectHorizontalDragGestures { _, dragAmount ->
                    if (dragAmount > 0) {
                        currentColorIndex = (currentColorIndex + 1) % colors.size
                    } else if (dragAmount < 0) {
                        currentColorIndex = if (currentColorIndex - 1 < 0) {
                            colors.size - 1
                        } else {
                            currentColorIndex - 1
                        }
                    }
                }
            }
    )
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                "2024期末上機考(資管二B陳琬昀)",
                fontSize = 20.sp
            )
            Image(
                painter = painterResource(id = R.drawable.class_b),
                contentDescription = "B班"
            )
            Text(
                "遊戲持續時間 0 秒",
                fontSize = 20.sp
            )
            Text(
                "您的成績 0 分",
                fontSize = 20.sp
            )
            val activity = (LocalContext.current as? Activity)
            Button(
                onClick = {
                    activity?.finish()
                }
            ) {
                Text("結束App")
            }
        }
    }
