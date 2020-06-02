package com.sample.worldofplay.dashboard.data

import com.sample.worldofplay.core.network.ApiHelper

class StoriesRepository(private val apiHelper: ApiHelper) {
    suspend fun getStoriesId() = apiHelper.getStoriesId()
    fun getStory(id: Int) = apiHelper.getStory(id)
}