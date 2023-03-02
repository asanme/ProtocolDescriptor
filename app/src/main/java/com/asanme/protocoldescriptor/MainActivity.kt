package com.asanme.protocoldescriptor

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.asanme.protocoldescriptor.model.RetrofitAPI
import com.asanme.protocoldescriptor.model.enum.ViewRoutes
import com.asanme.protocoldescriptor.model.helper.RetrofitHelper
import com.asanme.protocoldescriptor.ui.theme.ProtocolDescriptorTheme
import com.asanme.protocoldescriptor.view.*
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
    val retrofit = RetrofitHelper.getInstance().create(RetrofitAPI::class.java)
    val navController = rememberNavController()
    val topicViewModel = TopicViewModel(retrofit, navController)
    val activityViewModel = ActivityViewModel(retrofit, navController)
    val checklistViewModel = ChecklistViewModel(retrofit, navController)

    NavHost(
        navController = navController,
        startDestination = ViewRoutes.TopicView.route
    ) {
        composable(ViewRoutes.TopicView.route) {
            TopicsView(
                topicViewModel
            )
        }

        composable("${ViewRoutes.ProtocolView.route}/{topicId}") { backStackEntry ->
            backStackEntry.arguments?.getString("topicId")?.let { topicId ->
                LaunchedEffect(navController) {
                    activityViewModel.updateTopicId(topicId)
                }

                ActivityView(
                    activityViewModel
                )
            }
        }

        composable("${ViewRoutes.AddChecklistView.route}/{topicId}") { backStackEntry ->
            backStackEntry.arguments?.getString("topicId")?.let { topicId ->
                LaunchedEffect(navController) {
                    checklistViewModel.clearTasks()
                    checklistViewModel.updateTopicId(topicId)
                }

                AddChecklistView(
                    checklistViewModel
                )
            }
        }

        composable(
            "${ViewRoutes.ChecklistView.route}/{topicId}/{checklistId}",
            arguments = listOf(
                navArgument("topicId") {
                    type = NavType.StringType
                },
                navArgument("checklistId") {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val topicId = backStackEntry.arguments?.getString("topicId")
            val checklistId = backStackEntry.arguments?.getString("checklistId")

            if (topicId != null && checklistId != null) {
                checklistViewModel.updateChecklistId(checklistId)
                checklistViewModel.updateTopicId(topicId)
                LaunchedEffect(navController) {
                    checklistViewModel.retrieveTask()
                }

                ChecklistView(
                    checklistViewModel
                )
            }
        }

        composable(ViewRoutes.AddProtocolView.route) {
            AddProtocolView()
        }
    }
}