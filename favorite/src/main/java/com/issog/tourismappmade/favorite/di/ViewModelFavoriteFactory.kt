package com.issog.tourismappmade.favorite.di

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.issog.tourismappmade.core.di.Injection
import com.issog.tourismappmade.core.domain.usecase.TourismUseCase
import com.issog.tourismappmade.favorite.FavoriteFragment
import com.issog.tourismappmade.favorite.FavoriteViewModel

class ViewModelFavoriteFactory(
    private val tourismUseCase: TourismUseCase
): ViewModelProvider.NewInstanceFactory() {
    companion object {
        @Volatile
        private var instance: ViewModelFavoriteFactory? = null

        fun getInstance(context: Context): ViewModelFavoriteFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFavoriteFactory(
                    Injection.provideTourismUseCase(context)
                )
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        when {
            modelClass.isAssignableFrom(FavoriteViewModel::class.java) -> {
                FavoriteViewModel(tourismUseCase) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
}