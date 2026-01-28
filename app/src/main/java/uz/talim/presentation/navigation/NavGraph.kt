package uz.talim.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import uz.talim.presentation.auth.LoginScreen
import uz.talim.presentation.student.assignment.AssignmentDetailScreen
import uz.talim.presentation.student.home.StudentHomeScreen
import uz.talim.presentation.student.leaderboard.LeaderboardScreen
import uz.talim.presentation.teacher.create.CreateAssignmentScreen
import uz.talim.presentation.teacher.home.TeacherHomeScreen
import uz.talim.presentation.teacher.submissions.SubmissionsScreen

@Composable
fun TalimNavGraph(
    navController: NavHostController = rememberNavController(),
    startDestination: String = "login",
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
    ) {
        composable("login") {
            LoginScreen(navController = navController)
        }

        composable("student_home") {
            StudentHomeScreen(navController = navController)
        }

        composable("assignment_detail/{assignmentId}") { backStackEntry ->
            val assignmentId = backStackEntry.arguments?.getString("assignmentId") ?: ""
            AssignmentDetailScreen(
                navController = navController,
                assignmentId = assignmentId,
            )
        }

        composable("leaderboard") {
            LeaderboardScreen(navController = navController)
        }

        composable("teacher_home") {
            TeacherHomeScreen(navController = navController)
        }

        composable("create_assignment") {
            CreateAssignmentScreen(navController = navController)
        }

        composable("submissions/{assignmentId}") { backStackEntry ->
            val assignmentId = backStackEntry.arguments?.getString("assignmentId") ?: ""
            SubmissionsScreen(
                navController = navController,
                assignmentId = assignmentId,
            )
        }
    }
}
