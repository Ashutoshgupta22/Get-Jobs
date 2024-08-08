package com.aspark.lokalassign.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.aspark.lokalassign.ui.screen.BookmarksScreen
import com.aspark.lokalassign.ui.screen.JobDetailsScreen
import com.aspark.lokalassign.ui.screen.JobsScreen

@Composable
fun MyNavHost(
    navController: NavHostController,
    innerPadding: PaddingValues
) {

    NavHost(navController = navController, startDestination = Screen.Jobs.route) {
        composable(Screen.Jobs.route) { JobsScreen(navController) }
        composable(Screen.Bookmarks.route) { BookmarksScreen() }
        composable(
            route = Screen.JobDetails.route,
            arguments = listOf(navArgument("jobId") { type = NavType.IntType })
        ) { backStackEntry ->
            val jobId = backStackEntry.arguments?.getInt("jobId")
            JobDetailsScreen(jobId)
        }
    }
}

sealed class Screen(val route: String) {
    data object Jobs : Screen("jobs")
    data object Bookmarks : Screen("bookmarks")
    data object JobDetails : Screen("job_details/{jobId}") {
        fun createRoute(jobId: Int) = "job_details/$jobId"
    }
}
