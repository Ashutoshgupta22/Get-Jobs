package com.aspark.lokalassign.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.aspark.lokalassign.model.Job
import com.aspark.lokalassign.model.PrimaryDetails
import com.aspark.lokalassign.ui.UiState
import com.aspark.lokalassign.viewModel.BookmarksViewModel
import com.aspark.lokalassign.viewModel.JobsViewModel

@Composable
fun BookmarksScreen(viewModel: JobsViewModel, onNavigate: (Job) -> Unit) {

    LaunchedEffect(Unit) {
        viewModel.getBookmarkedJobs()
    }

    Box(modifier = Modifier.fillMaxSize()) {

        when (val uiState = viewModel.bookmarkedJobs.collectAsState().value) {

            is UiState.Success -> {
                if (uiState.data.isEmpty()) {
                    EmptyScreen()
                } else {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        contentPadding = PaddingValues(top = 16.dp, bottom = 90.dp)
                    ) {
                        items(uiState.data) { job ->
                            JobCard(job = job) { selectedJob ->
                                onNavigate(selectedJob)
                            }
                        }
                    }
                }
            }

            is UiState.Loading -> LoadingScreen()

            is UiState.Error -> ErrorScreen()
        }
    }
    }

@Composable
fun EmptyScreen() {

    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("No bookmarked jobs")
    }
}


@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
        ) {
        CircularProgressIndicator()
    }
}

@Composable
fun ErrorScreen(modifier: Modifier = Modifier) {
    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
        ) {
        Text("Error loading jobs")
    }
}