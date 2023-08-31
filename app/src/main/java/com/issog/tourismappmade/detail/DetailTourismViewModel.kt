package com.issog.tourismappmade.detail

import androidx.lifecycle.ViewModel
import com.issog.tourismappmade.core.domain.entities.Tourism
import com.issog.tourismappmade.core.domain.usecase.TourismUseCase

class DetailTourismViewModel(
    private val tourismUseCase: TourismUseCase
): ViewModel() {
    fun setFavoriteTourism(tourism: Tourism, isSaveTourism: Boolean) {
        tourismUseCase.setFavoriteTourism(tourism, isSaveTourism)
    }
}