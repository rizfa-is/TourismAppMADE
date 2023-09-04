package com.issog.tourismappmade.core.data.source.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.issog.tourismappmade.core.data.source.remote.network.ApiResponse
import com.issog.tourismappmade.core.data.source.remote.network.ApiService
import com.issog.tourismappmade.core.data.source.remote.response.ListTourismResponse
import com.issog.tourismappmade.core.data.source.remote.response.TourismResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource private constructor(private val apiService: ApiService) {
    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(apiService: ApiService): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource(apiService)
            }
    }

    fun getAllTourism(): LiveData<ApiResponse<List<TourismResponse>>> {
        val resultData = MutableLiveData<ApiResponse<List<TourismResponse>>>()

        try {
            val client = apiService.getTourismList()
            client.enqueue(object : Callback<ListTourismResponse> {
                override fun onResponse(
                    call: Call<ListTourismResponse>,
                    response: Response<ListTourismResponse>
                ) {
                    val tourismList = response.body()?.places
                    resultData.postValue(
                        if (tourismList != null)
                            ApiResponse.Success(tourismList)
                        else
                            ApiResponse.Empty
                    )
                }

                override fun onFailure(call: Call<ListTourismResponse>, t: Throwable) {
                    resultData.value = ApiResponse.Error(t.message.toString())
                    Log.e("RemoteDataSource", t.message.toString())
                }
            })
        } catch (e: Exception){
            resultData.value = ApiResponse.Error(e.toString())
            Log.e("RemoteDataSource", e.toString())
        }

        return resultData
    }
}