package com.aspark.lokalassign.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.aspark.lokalassign.model.Job
import com.aspark.lokalassign.network.JobsApi

class JobsPagingSource(
    private val jobsApi: JobsApi
) : PagingSource<Int, Job>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Job> {
        return try {
            val currentPage = params.key ?: 1
            val response = jobsApi.getJobs(page = currentPage)
            val data = response.results

            LoadResult.Page(
                data = data,
                prevKey = if (currentPage == 1) null else currentPage - 1,
                nextKey = if (data.isEmpty()) null else currentPage + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Job>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}