package com.aspark.lokalassign

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraph
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.aspark.lokalassign.ui.MyNavHost
import com.aspark.lokalassign.ui.Screen
import com.aspark.lokalassign.ui.theme.LokalAssignTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LokalAssignTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    var showBottomBar by remember { mutableStateOf(true) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            if (showBottomBar) BottomNavBar(navController = navController)
        }
    ) { innerPadding ->

        MyNavHost(navController = navController, innerPadding = innerPadding) { route ->
            showBottomBar = route != Screen.JobDetails.route
        }
    }
}

@Composable
fun BottomNavBar(navController: NavHostController) {
    NavigationBar {
        val currentBackStackEntry = navController.currentBackStackEntryAsState()
        val currentDestination = currentBackStackEntry.value?.destination

        NavigationBarItem(
            icon = {
                Icon(
                    painter = painterResource(id =
                    if(currentDestination?.route == Screen.Jobs.route)
                        R.drawable.work_filled
                        else R.drawable.work_outline
                    ),
                    contentDescription = null
                )
            },
            label = { Text("Jobs") },
            selected = currentDestination?.route == Screen.Jobs.route,
            onClick = { navController.navigate(Screen.Jobs.route) }
        )
        NavigationBarItem(
            icon = {
                Icon(
                    painter = painterResource(id =
                    if(currentDestination?.route == Screen.Bookmarks.route)
                        R.drawable.bookmark_filled
                    else R.drawable.bookmark_outline
                    ),
                    contentDescription = null
                )
            },
            label = { Text("Bookmarks") },
            selected = currentDestination?.route == Screen.Bookmarks.route,
            onClick = { navController.navigate(Screen.Bookmarks.route) }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    LokalAssignTheme {
        MainScreen()
    }
}