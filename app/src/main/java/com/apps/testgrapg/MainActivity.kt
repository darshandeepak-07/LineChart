package com.apps.testgrapg

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.apps.testgrapg.ui.theme.TestGrapgTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val list = listOf(2f,1f,5f,4f)
        var sum = 0
        list.forEach { value->
            sum+= value.toInt()
        }
        enableEdgeToEdge()
        setContent {
            TestGrapgTheme {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp),
                    verticalArrangement = Arrangement.Center
                ) {

                    Text(text = "You have earned Rs ${sum}")
                    LineGraph(xLabels = listOf("Dec 2023","Jan 2024","Feb 2024","Mar 2024","Apr 2024"), yData = listOf(2f,1f,5f,4f))
                }
            }
        }
    }
}
