package com.aspark.lokalassign.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class JobEntity(
    @PrimaryKey val id: Int,
    val name: String,
)