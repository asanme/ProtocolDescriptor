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
import com.asanme.protocoldescriptor.model.RetrofitAPI
import com.asanme.protocoldescriptor.model.enum.ViewRoutes
import com.asanme.protocoldescriptor.model.helper.RetrofitHelper
import com.asanme.protocoldescriptor.ui.theme.ProtocolDescriptorTheme
import com.asanme.protocoldescriptor.view.AddChecklistView
import com.asanme.protocoldescriptor.view.AddProtocolView
import com.asanme.protocoldescriptor.view.ProtocolView
import com.asanme.protocoldescriptor.view.TopicsView
import com.asanme.protocoldescriptor.viewmodel.ActivityViewModel
import com.asanme.protocoldescriptor.viewmodel.ChecklistViewModel
import com.asanme.protocoldescriptor.viewmodel.TopicViewModel

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
    NavHost(navController = navController, startDestination = ViewRoutes.TopicView.route) {
        composable(ViewRoutes.TopicView.route) {
            TopicsView(
                navController, TopicViewModel(
                    RetrofitHelper.getInstance().create(RetrofitAPI::class.java)
                )
            )
        }

        composable(ViewRoutes.AddProtocolView.route) {
            AddProtocolView()
        }

        composable("${ViewRoutes.AddChecklistView.route}/{topicId}") { backStackEntry ->
            backStackEntry.arguments?.getString("topicId")?.let { topicId ->
                AddChecklistView(
                    navController,
                    ChecklistViewModel(
                        topicId,
                        RetrofitHelper.getInstance().create(RetrofitAPI::class.java)
                    ),
                )
            }
        }

        composable("${ViewRoutes.ProtocolView.route}/{topicId}") { backStackEntry ->
            backStackEntry.arguments?.getString("topicId")?.let { topicId ->
                ProtocolView(
                    navController,
                    ActivityViewModel(
                        topicId,
                        RetrofitHelper.getInstance().create(RetrofitAPI::class.java)
                    ),
                )
            }
        }
    }
}