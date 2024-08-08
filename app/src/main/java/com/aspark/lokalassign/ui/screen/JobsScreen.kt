package com.aspark.lokalassign.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.aspark.lokalassign.model.Job
import com.aspark.lokalassign.ui.theme.LokalAssignTheme
import com.aspark.lokalassign.viewModel.JobsViewModel

@Composable
fun JobsScreen(
    jobsViewModel: JobsViewModel = viewModel(),
    selectedJob: Job,
    onNavigate: (Job) -> Unit,
) {
    val jobs: LazyPagingItems<Job> = jobsViewModel.jobs.collectAsLazyPagingItems()

   /* when(val uiState = jobsViewModel.jobs.collectAsState().value) {
         is UiState.Success -> {
             CardList(uiState.data) { job ->
                 onNavigate(job)
             }
        }

        is UiState.Error -> {

        }
        is UiState.Loading -> {

        }
    }*/

    CardList(jobs) { job ->
        onNavigate(job)
    }
}

@Composable
fun CardList(data: LazyPagingItems<Job>, onClick: (Job) -> Unit) {

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentPadding = PaddingValues(top = 16.dp, bottom = 90.dp)
    ) {
        items(data.itemCount) { index ->
            JobCard(data[index]!!) { onClick(it)}
        }
    }
}


@Composable
fun JobCard(job: Job, onClick: (Job) -> Unit) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        onClick = { onClick(job)}
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
        JobsScreen( viewModel(), Job(), {})
    }
}