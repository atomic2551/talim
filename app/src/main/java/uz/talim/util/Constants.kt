package uz.talim.util

object Constants {
    const val BASE_URL = "https://api.talim.uz/v1/"
    const val DATABASE_NAME = "talim_database"

    object PreferenceKeys {
        const val ACCESS_TOKEN = "access_token"
        const val USER_ID = "user_id"
        const val USER_ROLE = "user_role"
        const val GROUP_ID = "group_id"
    }

    object Routes {
        const val LOGIN = "login"
        const val STUDENT_HOME = "student_home"
        const val TEACHER_HOME = "teacher_home"
        const val ASSIGNMENT_DETAIL = "assignment_detail/{assignmentId}"
        const val LEADERBOARD = "leaderboard"
        const val CREATE_ASSIGNMENT = "create_assignment"
        const val SUBMISSIONS = "submissions/{assignmentId}"
    }
}
