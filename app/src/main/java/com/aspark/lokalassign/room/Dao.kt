package com.aspark.lokalassign.room

import androidx.room.Dao
import androidx.room.Query
import com.aspark.lokalassign.model.Job

@Dao
interface JobsDao {

//    @Query("SELECT * FROM jobs")
    fun getBookmarkedJobs(): List<Job>
}