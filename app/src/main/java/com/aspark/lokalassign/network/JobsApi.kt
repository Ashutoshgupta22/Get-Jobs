package com.aspark.lokalassign.network

import com.aspark.lokalassign.model.Job
import retrofit2.http.GET
import retrofit2.http.Query

interface JobsApi {

    @GET("/")
    suspend fun getJobs(@Query("page") page: Int = 1): List<Job>
}