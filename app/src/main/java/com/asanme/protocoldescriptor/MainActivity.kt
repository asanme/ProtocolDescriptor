package com.asanme.protocoldescriptor

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.asanme.protocoldescriptor.model.enum.Routes
import com.asanme.protocoldescriptor.ui.theme.ProtocolDescriptorTheme
import com.asanme.protocoldescriptor.view.AddProtocolView
import com.asanme.protocoldescriptor.view.TopicsView
import com.asanme.protocoldescriptor.viewmodel.ProtocolViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProtocolDescriptorTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    App()
                }
            }
        }
    }
}

@Composable
fun App() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Routes.TopicView.route) {
        composable(Routes.TopicView.route) {
            TopicsView(navController)
        }

        composable(Routes.AddProtocolView.route) {
            AddProtocolView(ProtocolViewModel())
        }
    }
}