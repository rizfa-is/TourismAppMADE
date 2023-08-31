package com.issog.tourismappmade.core.domain.repository

import androidx.lifecycle.LiveData
import com.issog.tourismappmade.core.data.Resource
import com.issog.tourismappmade.core.domain.entities.Tourism
import kotlinx.coroutines.flow.Flow

interface ITourismRepository {

    fun getAllTourism(): LiveData<Resource<List<Tourism>>>

    fun getFavoriteTourism(): LiveData<List<Tourism>>

    fun setFavoriteTourism(tourism: Tourism, state: Boolean)
}