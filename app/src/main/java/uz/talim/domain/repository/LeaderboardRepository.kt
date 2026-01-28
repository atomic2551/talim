package uz.talim.domain.repository

import uz.talim.domain.model.StudentRank
import uz.talim.util.Resource

interface LeaderboardRepository {
    suspend fun getLeaderboard(groupId: String): Resource<List<StudentRank>>
}
