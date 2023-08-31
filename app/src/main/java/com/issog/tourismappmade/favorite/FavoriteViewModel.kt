package com.issog.tourismappmade.favorite

import androidx.lifecycle.ViewModel
import com.issog.tourismappmade.core.domain.usecase.TourismUseCase

class FavoriteViewModel(tourismUseCase: TourismUseCase): ViewModel() {
    val favoriteTourism = tourismUseCase.getFavoriteTourism()
}