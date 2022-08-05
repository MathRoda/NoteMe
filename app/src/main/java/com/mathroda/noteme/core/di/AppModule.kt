package com.mathroda.noteme.core.di

import android.app.Application
import androidx.room.Room
import com.mathroda.noteme.data.database.NoteMeDatabase
import com.mathroda.noteme.data.repository.NoteMeRepositoryImpl
import com.mathroda.noteme.domain.repository.NoteMeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesNoteMeDataBase(app: Application): NoteMeDatabase {
        return Room.databaseBuilder(
            app,
            NoteMeDatabase::class.java,
            NoteMeDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun providesNoteMeRepository(
        db: NoteMeDatabase
    ): NoteMeRepository {
        return NoteMeRepositoryImpl(db.dao)
    }
}
