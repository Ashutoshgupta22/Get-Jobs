package com.aspark.lokalassign.model

import android.util.Log
import com.aspark.lokalassign.ui.Screen
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.google.gson.annotations.SerializedName

data class JobDetailsContent(
    @SerializedName("block1") val block1: String,
    @SerializedName("block2") val block2: String,
    @SerializedName("block3") val block3: String
)

fun parseJobDetailsContent(jsonString: String): JobDetailsContent {

    return try {
        Gson().fromJson(jsonString, JobDetailsContent::class.java)
    } catch (e: Exception) {
        Log.e("parseJobDetailsContent", "Failed to parse JSON: ${e.message}")
        JobDetailsContent("null", "null", "null")
    }


//    return Gson().fromJson(jsonString, JobDetailsContent::class.java)
}
