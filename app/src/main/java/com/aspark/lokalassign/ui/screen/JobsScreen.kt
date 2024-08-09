package com.aspark.lokalassign.ui.screen

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.aspark.lokalassign.model.Job
import com.aspark.lokalassign.network.ApiClient
import com.aspark.lokalassign.repository.JobsRepository
import com.aspark.lokalassign.room.JobsDatabase
import com.aspark.lokalassign.ui.UiState
import com.aspark.lokalassign.ui.theme.LokalAssignTheme
import com.aspark.lokalassign.viewModel.JobsViewModel
import com.aspark.lokalassign.viewModel.ViewModelFactory

@Composable
fun JobsScreen(
    jobsViewModel: JobsViewModel,
    modifier: Modifier,
    selectedJob: Job,
    onNavigate: (Job) -> Unit,
) {

    when (val uiState = jobsViewModel.jobs.collectAsState().value) {
        is UiState.Success -> {
//            Log.i("JobsScreen", "JobsScreen: ${uiState.data}")
            CardList(
                modifier,
                data = uiState.data,
                jobsViewModel = jobsViewModel
            ) { job ->
                onNavigate(job)
            }
        }

        is UiState.Error -> {
            Toast.makeText(LocalContext.current, "Something went wrong", Toast.LENGTH_SHORT).show()
        }

        is UiState.Loading -> {
            CircularProgressIndicator()
        }
    }
}

@Composable
fun CardList(
    modifier: Modifier,
    data: List<Job>, jobsViewModel: JobsViewModel,
    onClick: (Job) -> Unit
) {

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 16.dp, start = 16.dp, end = 16.dp),
        contentPadding = PaddingValues(top = 16.dp, bottom = 90.dp)
    ) {
        itemsIndexed(data, key = { index, job ->
            job.id
        }) { index, job ->

            if (job.id != 0)
                JobCard(job) { onClick(it) }

            if (index == data.size - 1) {
//                Log.i("JobScreen", "CardList: size: ${data.size}")
                jobsViewModel.getJobs()
            }
        }

        item {
            if (jobsViewModel.isLoading) {
                CircularProgressIndicator()
            } else if (jobsViewModel.endReached) {
                Text(
                    text = "No more jobs available",
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}


@Composable
fun JobCard(job: Job, onClick: (Job) -> Unit) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        onClick = { onClick(job) }
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = job.title ?: "Null", fontWeight = FontWeight.Bold)
//            Text(text = job.primaryDetails.place ?: "Null")
//            Text(text = job.primaryDetails.salary ?: "Null")
//            Text(text = job.customLink.removePrefix("tel:") ?: "Null")

//            job?.title?.let { Text(text = it, fontWeight = FontWeight.Bold) }
            job.primaryDetails?.place?.let { Text(text = it) }
            job.primaryDetails?.salary?.let { Text(text = it) }
            job.customLink?.removePrefix("tel:")?.let { Text(text = it) }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun JobsScreenPreview() {
    LokalAssignTheme {
        JobsScreen(viewModel(), Modifier, Job(), {})
    }
}