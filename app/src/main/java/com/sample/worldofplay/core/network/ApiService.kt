package com.sample.worldofplay.core.network

import com.sample.worldofplay.dashboard.model.StoriesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("topstories.json")
    suspend fun getStoriesId(): List<Int>

    @GET("item/{articleid}.json")
    fun getStory(@Path("articleid") id: Int): Call<StoriesResponse?>?
}