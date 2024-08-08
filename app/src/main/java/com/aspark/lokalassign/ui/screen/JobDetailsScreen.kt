package com.aspark.lokalassign.ui.screen

import android.graphics.Color.parseColor
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aspark.lokalassign.model.Job
import com.aspark.lokalassign.ui.theme.LokalAssignTheme

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun JobDetailsScreen(job: Job) {

    Log.i("JobDetailsScreen", "JobDetailsScreen: job = $job")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState(), enabled = true)
    ) {
        // Company Name
        Text(
            text = job.companyName,
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        // Job Title
        Text(
            text = job.title,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        // Job Type, Experience, Location
        Row(
            modifier = Modifier.padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(text = "Job Type: ${job.primaryDetails?.jobType}")
                Text(text = "Experience: ${job.primaryDetails?.experience}")
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(text = "Location: ${job.primaryDetails?.place}")
                Text(text = "Salary: ${job.primaryDetails?.salary}")
            }
        }

        // Job Description
        Text(
            text = job.content,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Job Tags
        FlowRow(
            modifier = Modifier.padding(bottom = 16.dp),
//                mainAxisSpacing = 8.dp,
//                crossAxisSpacing = 8.dp
        ) {
            job.jobTags.forEach { tag ->
                Chip(
                    label = tag.value,
                    bgColor = Color(parseColor(tag.bgColor)),
                    textColor = Color(parseColor(tag.textColor))
                )
            }
        }

        // Contact Information
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Contact Preference: ${job.contactPreference?.preference}",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "WhatsApp: ${job.whatsappNo}",
                style = MaterialTheme.typography.bodyMedium
            )
        }

        // Apply Button
        Spacer(modifier = Modifier.weight(1f))
        Button(
            onClick = { /* Handle Apply */ },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        ) {
            Text(text = job.buttonText)
        }
    }
}

@Composable
fun Chip(label: String, bgColor: Color, textColor: Color) {
    Box(
        modifier = Modifier
            .background(bgColor, RoundedCornerShape(16.dp))
            .padding(horizontal = 12.dp, vertical = 6.dp)
    ) {
        Text(text = label, color = textColor)
    }
}

@Preview(showBackground = true)
@Composable
private fun JobsDetailsScreenPreview() {
    LokalAssignTheme {
        JobDetailsScreen(Job())
    }
}
