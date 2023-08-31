package com.issog.tourismappmade.core.domain.usecase

import androidx.lifecycle.LiveData
import com.issog.tourismappmade.core.data.Resource
import com.issog.tourismappmade.core.domain.entities.Tourism

interface TourismUseCase {

    fun getAllTourism(): LiveData<Resource<List<Tourism>>>

    fun getFavoriteTourism(): LiveData<List<Tourism>>

    fun setFavoriteTourism(tourism: Tourism, state: Boolean)
}