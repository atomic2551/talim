package uz.talim.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import uz.talim.data.local.database.TalimDatabase
import uz.talim.util.Constants
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): TalimDatabase {
        return Room.databaseBuilder(
            context,
            TalimDatabase::class.java,
            Constants.DATABASE_NAME,
        ).fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideAssignmentDao(database: TalimDatabase) = database.assignmentDao()
}
