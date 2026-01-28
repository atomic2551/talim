package uz.talim.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.talim.data.repository.AssignmentRepositoryImpl
import uz.talim.data.repository.AuthRepositoryImpl
import uz.talim.data.repository.LeaderboardRepositoryImpl
import uz.talim.domain.repository.AssignmentRepository
import uz.talim.domain.repository.AuthRepository
import uz.talim.domain.repository.LeaderboardRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideAuthRepository(
        repositoryImpl: AuthRepositoryImpl,
    ): AuthRepository = repositoryImpl

    @Provides
    @Singleton
    fun provideAssignmentRepository(
        repositoryImpl: AssignmentRepositoryImpl,
    ): AssignmentRepository = repositoryImpl

    @Provides
    @Singleton
    fun provideLeaderboardRepository(
        repositoryImpl: LeaderboardRepositoryImpl,
    ): LeaderboardRepository = repositoryImpl
}
