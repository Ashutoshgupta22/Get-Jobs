package com.aspark.lokalassign.repository

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.aspark.lokalassign.model.Job
import com.aspark.lokalassign.model.JobResponse
import com.aspark.lokalassign.network.JobsApi
import com.aspark.lokalassign.paging.JobsPagingSource
import com.aspark.lokalassign.ui.UiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class JobsRepository(private val jobsApi: JobsApi) {

    fun getJobs(): Flow<PagingData<Job>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { JobsPagingSource(jobsApi) }
        ).flow
    }
}