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
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraph
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

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            NavigationBar {
                val currentBackStackEntry = navController.currentBackStackEntryAsState()
                val currentDestination = currentBackStackEntry.value?.destination

                NavigationBarItem(
                    icon = {
                        Icon(
                            painter = painterResource(id = R.drawable.work_outline),
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
                            painter = painterResource(id = R.drawable.bookmark_outline),
                            contentDescription = null
                        )
                    },
                    label = { Text("Bookmarks") },
                    selected = currentDestination?.route == Screen.Bookmarks.route,
                    onClick = { navController.navigate(Screen.Bookmarks.route) }
                )
            }
        }
    ) { innerPadding ->

        MyNavHost(navController = navController, innerPadding = innerPadding)
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    LokalAssignTheme {
        MainScreen()
    }
}