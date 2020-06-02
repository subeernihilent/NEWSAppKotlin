package com.sample.worldofplay.core.network

class ApiHelper(private val apiService: ApiService) {
    suspend fun getStoriesId() = apiService.getStoriesId()
    fun getStory(id: Int) = apiService.getStory(id)

}