package com.sample.worldofplay.dashboard.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.sample.worldofplay.core.Resource
import com.sample.worldofplay.dashboard.data.StoriesRepository
import kotlinx.coroutines.Dispatchers

class DashboardViewModel(private val storiesRepository: StoriesRepository) : ViewModel() {

    fun getStoriesId() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = storiesRepository.getStoriesId()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

    fun getStory(id: Int) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = storiesRepository.getStory(id)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
}