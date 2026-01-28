package uz.talim.presentation.teacher.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun TeacherHomeScreen(
    navController: NavController,
    viewModel: TeacherHomeViewModel = hiltViewModel(),
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        Text(
            text = "O'qituvchi bosh sahifasi",
            style = MaterialTheme.typography.headlineSmall,
        )
        Button(onClick = { navController.navigate("create_assignment") }) {
            Text("Topshiriq yaratish")
        }
    }
}
