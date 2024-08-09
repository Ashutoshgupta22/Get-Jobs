package com.aspark.lokalassign.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.aspark.lokalassign.network.ApiClient
import com.aspark.lokalassign.repository.JobsRepository
import com.aspark.lokalassign.room.JobsDatabase
import com.aspark.lokalassign.ui.screen.BookmarksScreen
import com.aspark.lokalassign.ui.screen.JobDetailsScreen
import com.aspark.lokalassign.ui.screen.JobsScreen
import com.aspark.lokalassign.viewModel.BookmarksViewModel
import com.aspark.lokalassign.viewModel.JobsViewModel
import com.aspark.lokalassign.viewModel.ViewModelFactory

@Composable
fun MyNavHost(
    navController: NavHostController,
    innerPadding: PaddingValues,
    onNavigate: (String) -> Unit
) {
    val jobsRepository = JobsRepository(
        ApiClient.jobsApi,
        JobsDatabase.getDatabase(LocalContext.current).jobDao()
    )
    val viewModelFactory = ViewModelFactory(jobsRepository)

    NavHost(navController = navController, startDestination = "job_screen") {

        navigation(
            startDestination = Screen.Jobs.route,
            route = "job_screen"
        ) {
            composable(Screen.Jobs.route) { entry ->
                val viewModel =
                    entry.sharedViewModel<JobsViewModel>(navController, viewModelFactory)
                val selectedJob by viewModel.selectedJob.collectAsState()

                onNavigate(Screen.Jobs.route)

                JobsScreen(
                    viewModel,
                    Modifier.padding(innerPadding),
                    selectedJob = selectedJob,
                    onNavigate = { job ->
                        viewModel.selectJob(job)
                        navController.navigate(Screen.JobDetails.route)
                    }
                )
            }

            composable(
                route = Screen.JobDetails.route,
            ) { backStackEntry ->

                val viewModel = backStackEntry.sharedViewModel<JobsViewModel>(
                    navController = navController,
                    factory = viewModelFactory
                )
                val selectedJob by viewModel.selectedJob.collectAsState()

                onNavigate(Screen.JobDetails.route)
                JobDetailsScreen(viewModel, selectedJob) {
                    navController.popBackStack()
                }
            }

            composable(Screen.Bookmarks.route) { backStackEntry ->
                onNavigate(Screen.Bookmarks.route)

                val viewModel = backStackEntry.sharedViewModel<JobsViewModel>(
                    navController = navController,
                    factory = viewModelFactory
                )
                val selectedJob by viewModel.selectedJob.collectAsState()

                BookmarksScreen(viewModel) { job ->
                    viewModel.selectJob(job)
                    navController.navigate(Screen.JobDetails.route)
                }
            }
        }
    }
}

@Composable
inline fun <reified T : ViewModel> NavBackStackEntry.sharedViewModel(
    navController: NavController,
    factory: ViewModelFactory
): T {
    val navGraphRoute = destination.parent?.route ?: return viewModel(factory = factory)
    val parentEntry = remember(this) {
        navController.getBackStackEntry(navGraphRoute)
    }
    return viewModel(parentEntry, factory = factory)
}

sealed class Screen(val route: String) {
    data object Jobs : Screen("jobs")
    data object Bookmarks : Screen("bookmarks")
    data object JobDetails : Screen("job_details") {
//        fun createRoute(job: Job) = "job_details/${job.id}"
    }
}
