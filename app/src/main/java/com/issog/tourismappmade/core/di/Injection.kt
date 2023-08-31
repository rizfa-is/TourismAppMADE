package com.issog.tourismappmade.core.di

import android.content.Context
import com.issog.tourismappmade.core.data.TourismRepository
import com.issog.tourismappmade.core.data.source.local.LocalDataSource
import com.issog.tourismappmade.core.data.source.local.room.TourismDatabase
import com.issog.tourismappmade.core.data.source.remote.RemoteDataSource
import com.issog.tourismappmade.core.domain.repository.ITourismRepository
import com.issog.tourismappmade.core.domain.usecase.TourismInteractor
import com.issog.tourismappmade.core.domain.usecase.TourismUseCase
import com.issog.tourismappmade.core.utils.AppExecutorHelper
import com.issog.tourismappmade.core.utils.JsonHelper

object Injection {
    private fun provideRepository(context: Context): ITourismRepository {
        val database = TourismDatabase.getInstance(context)

        val remoteDataSource = RemoteDataSource.getInstance(JsonHelper(context))
        val localDataSource = LocalDataSource.getInstance(database.tourismDao())
        val appExecutors = AppExecutorHelper()

        return TourismRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }

    fun provideTourismUseCase(context: Context): TourismUseCase {
        val repository = provideRepository(context)
        return TourismInteractor(repository)
    }
}