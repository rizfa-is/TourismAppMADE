package com.issog.tourismappmade.home

import androidx.lifecycle.ViewModel
import com.issog.tourismappmade.core.domain.usecase.TourismUseCase

class HomeViewModel(tourismUseCase: TourismUseCase): ViewModel() {
    val tourism = tourismUseCase.getAllTourism()
}