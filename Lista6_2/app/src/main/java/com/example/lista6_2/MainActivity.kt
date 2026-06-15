package com.example.lista6_2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AssignmentApp()
        }
    }
}

data class Task(
    val id: Int,
    val description: String,
    val maxPoints: Int
)

data class AssignmentList(
    val id: String,
    val subject: String,
    val listNumber: Int,
    val grade: Double,
    val tasks: List<Task>
)

val sampleAssignmentLists = listOf(
    AssignmentList(
        "PUM1_L1",
        "Programowanie Urządzeń Mobilnych 1",
        1,
        4.5,
        listOf(
            Task(1, "Implementacja FizzBuzz", 3),
            Task(2, "Sprawdzenie palindromu", 3),
            Task(3, "Trójkąt Pascala", 4)
        )
    ),
    AssignmentList(
        "PUM1_L2",
        "Programowanie Urządzeń Mobilnych 1",
        2,
        5.0,
        listOf(
            Task(1, "Funkcje rozszerzające", 4),
            Task(2, "Funkcje wyższego rzędu", 6)
        )
    ),
    AssignmentList(
        "SO_L1",
        "Systemy Operacyjne",
        1,
        3.5,
        listOf(
            Task(1, "Implementacja semafora", 5),
            Task(2, "Problem producenta-konsumenta", 5)
        )
    ),
    AssignmentList(
        "SO_L2",
        "Systemy Operacyjne",
        2,
        4.0,
        listOf(
            Task(1, "Algorytmy szeregowania CPU", 6),
            Task(2, "Zarządzanie pamięcią", 4)
        )
    )
)

@Composable
fun AssignmentApp() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = currentRoute == "lists",
                    onClick = {
                        navController.navigate("lists") {
                            launchSingleTop = true
                        }
                    },
                    label = { Text("Listy") },
                    icon = {}
                )
                NavigationBarItem(
                    selected = currentRoute == "grades",
                    onClick = {
                        navController.navigate("grades") {
                            launchSingleTop = true
                        }
                    },
                    label = { Text("Oceny") },
                    icon = {}
                )
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "lists",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("lists") {
                AssignmentListsScreen(
                    assignmentLists = sampleAssignmentLists,
                    onListClick = { assignmentList ->
                        navController.navigate("details/${assignmentList.id}")
                    }
                )
            }
            composable("grades") {
                GradesSummaryScreen(sampleAssignmentLists)
            }
            composable(
                route = "details/{listId}",
                arguments = listOf(navArgument("listId") { type = NavType.StringType })
            ) { backStackEntry ->
                val listId = backStackEntry.arguments?.getString("listId")
                val assignmentList = sampleAssignmentLists.firstOrNull { it.id == listId }

                if (assignmentList != null) {
                    ListDetailScreen(assignmentList)
                } else {
                    Text(
                        text = "Nie znaleziono listy",
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun AssignmentListsScreen(
    assignmentLists: List<AssignmentList>,
    onListClick: (AssignmentList) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(assignmentLists) { assignmentList ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .clickable { onListClick(assignmentList) }
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(text = assignmentList.subject)
                    Text(text = "Lista ${assignmentList.listNumber}")
                    Text(text = "Ocena: ${assignmentList.grade}")
                    Text(text = "Liczba zadań: ${assignmentList.tasks.size}")
                }
            }
        }
    }
}

@Composable
fun GradesSummaryScreen(
    assignmentLists: List<AssignmentList>,
    modifier: Modifier = Modifier
) {
    val gradesBySubject = assignmentLists.groupBy { it.subject }

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(gradesBySubject.entries.toList()) { (subject, lists) ->
            val averageGrade = lists.map { it.grade }.average()

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(text = subject)
                    Text(text = "Średnia: %.2f".format(averageGrade))
                }
            }
        }
    }
}

@Composable
fun ListDetailScreen(
    assignmentList: AssignmentList,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        item {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = assignmentList.subject)
                Text(text = "Lista ${assignmentList.listNumber}")
            }
        }

        items(assignmentList.tasks) { task ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(text = "Zadanie ${task.id}")
                    Text(text = task.description)
                    Text(text = "Maks. punktów: ${task.maxPoints}")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AssignmentAppPreview() {
    AssignmentListsScreen(
        assignmentLists = sampleAssignmentLists,
        onListClick = {}
    )
}
