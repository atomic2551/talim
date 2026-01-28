package uz.talim.data.remote.api

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import uz.talim.data.remote.dto.AssignmentDto
import uz.talim.data.remote.dto.CreateAssignmentRequest
import uz.talim.data.remote.dto.LeaderboardItemDto

interface TalimApiService {
    @GET("groups/{group_id}/assignments")
    suspend fun getAssignments(
        @Path("group_id") groupId: String,
    ): Response<List<AssignmentDto>>

    @POST("assignments")
    suspend fun createAssignment(
        @Body request: CreateAssignmentRequest,
    ): Response<AssignmentDto>

    @GET("groups/{group_id}/leaderboard")
    suspend fun getLeaderboard(
        @Path("group_id") groupId: String,
    ): Response<List<LeaderboardItemDto>>
}
