package uz.talim.data.repository

import uz.talim.data.remote.api.TalimApiService
import uz.talim.data.remote.dto.toDomain
import uz.talim.domain.model.StudentRank
import uz.talim.domain.repository.LeaderboardRepository
import uz.talim.util.Resource
import javax.inject.Inject

class LeaderboardRepositoryImpl @Inject constructor(
    private val api: TalimApiService,
) : LeaderboardRepository {
    override suspend fun getLeaderboard(groupId: String): Resource<List<StudentRank>> {
        return try {
            val response = api.getLeaderboard(groupId)
            if (response.isSuccessful) {
                val items = response.body().orEmpty()
                val ranks = items.mapIndexed { index, dto ->
                    dto.toDomain(index + 1)
                }
                Resource.Success(ranks)
            } else {
                Resource.Error("Xatolik: ${response.message()}")
            }
        } catch (e: Exception) {
            Resource.Error("Xatolik: ${e.localizedMessage}")
        }
    }
}
