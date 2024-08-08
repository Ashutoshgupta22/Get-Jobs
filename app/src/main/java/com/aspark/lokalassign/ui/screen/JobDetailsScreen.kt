@file:OptIn(ExperimentalLayoutApi::class)

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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aspark.lokalassign.R
import com.aspark.lokalassign.model.Job
import com.aspark.lokalassign.model.parseJobDetailsContent
import com.aspark.lokalassign.ui.theme.LokalAssignTheme

@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
@Composable
fun JobDetailsScreen(job: Job, onBackClick: () -> Unit) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Job Details") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Default.ArrowBack,
                            contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.largeTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            )
        }
    ) { innerPadding ->
        Content(job,Modifier.padding(innerPadding))
    }
}

@Composable
fun Content(job: Job, modifier: Modifier) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp)
            .verticalScroll(rememberScrollState(), enabled = true)
    ) {

        // Job Title
        Text(
            text = job.title,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(bottom = 8.dp, top = 16.dp)
        )

        // Company Name
        Text(
            text = job.companyName,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(bottom = 8.dp),
            color = Color.DarkGray
        )

        // Job Type, Experience, Location
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, bottom = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(text = "Experience:  ${job.primaryDetails?.experience}")
            Text(text = "Location:  ${job.primaryDetails?.place}")

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Top
            ) {
                Text(text = "Salary:  ${job.primaryDetails?.salary}")

                Spacer(modifier = Modifier.weight(1f))

                var selected by remember { mutableStateOf(false) }

                IconButton(
                    modifier = Modifier
                        .size(40.dp),
                    onClick = { selected = !selected }
                ) {
                    Icon(
                        painter = painterResource(
                            id = if(!selected) R.drawable.bookmark_outline
                            else R.drawable.bookmark_filled
                        ),
                        contentDescription = "Bookmark" )
                        }
            }
        }

        HorizontalDivider()
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Description",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .padding(bottom = 8.dp)
        )

        // Job Description

        job?.content?.let {
            val content = parseJobDetailsContent(job.content)
            Text(
                text = content.block1,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            Text(
                text = content.block2,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            Text(
                text = content.block3,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }

        // Job Tags
        FlowRow(
            modifier = Modifier.padding(bottom = 16.dp, top = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp)
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
//            Text(
//                text = "Contact Preference: ${job.contactPreference?.preference}",
//                style = MaterialTheme.typography.bodyMedium
//            )
            Text(
                text = "WhatsApp: ${job.whatsappNo}",
                style = MaterialTheme.typography.bodyMedium
            )
        }

        // Apply Button
        Button(
            onClick = {  },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        ) {
            Text(text = job.buttonText)
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
fun Chip(label: String, bgColor: Color, textColor: Color) {
    Box(
        modifier = Modifier
            .background(bgColor, RoundedCornerShape(16.dp))
            .padding(horizontal = 12.dp, vertical = 6.dp)
    ) {
        Text(text = label, color = textColor, fontSize = 14.sp)
    }
}

@Preview(showBackground = true)
@Composable
private fun JobsDetailsScreenPreview() {
    LokalAssignTheme {
        JobDetailsScreen(Job()) {}
    }
}
