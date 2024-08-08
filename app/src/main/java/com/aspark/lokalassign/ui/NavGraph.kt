package com.aspark.lokalassign.ui

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import com.aspark.lokalassign.model.Job
import com.aspark.lokalassign.ui.screen.BookmarksScreen
import com.aspark.lokalassign.ui.screen.JobDetailsScreen
import com.aspark.lokalassign.ui.screen.JobsScreen
import com.aspark.lokalassign.viewModel.JobsViewModel

@Composable
fun MyNavHost(
    navController: NavHostController,
    innerPadding: PaddingValues,
    onNavigate: (String) -> Unit
) {

    NavHost(navController = navController, startDestination = "job_screen") {

        navigation(
            startDestination = Screen.Jobs.route,
            route = "job_screen"
        ) {
            composable(Screen.Jobs.route) { entry ->
                val viewModel = entry.sharedViewModel<JobsViewModel>(navController = navController)
                val selectedJob by viewModel.selectedJob.collectAsState()

                onNavigate(Screen.Jobs.route)

                JobsScreen(
                    selectedJob = selectedJob,
                    onNavigate = { job->
                        viewModel.selectJob(job)
                        navController.navigate(Screen.JobDetails.route)
                    }
                )
            }

            composable(
                route = Screen.JobDetails.route,
            ) { backStackEntry ->

                val viewModel = backStackEntry.sharedViewModel<JobsViewModel>(navController = navController)
                val selectedJob by viewModel.selectedJob.collectAsState()

                onNavigate(Screen.JobDetails.route)
                JobDetailsScreen(selectedJob){
                    navController.popBackStack()
                }
            }
        }

        composable(Screen.Bookmarks.route) {
            onNavigate(Screen.Bookmarks.route)
            BookmarksScreen()
        }

    }
}

@Composable
inline fun <reified T : ViewModel> NavBackStackEntry.sharedViewModel(
    navController: NavController
): T {
    val navGraphRoute = destination.parent?.route ?: return viewModel()
    val parentEntry = remember(this) {
        navController.getBackStackEntry(navGraphRoute)
    }
    return viewModel(parentEntry)
}

sealed class Screen(val route: String) {
    data object Jobs : Screen("jobs")
    data object Bookmarks : Screen("bookmarks")
    data object JobDetails : Screen("job_details") {
//        fun createRoute(job: Job) = "job_details/${job.id}"
    }
}
