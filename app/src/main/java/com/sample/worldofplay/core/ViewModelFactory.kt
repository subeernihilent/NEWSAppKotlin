package com.sample.worldofplay.core

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sample.worldofplay.core.network.ApiHelper
import com.sample.worldofplay.dashboard.data.StoriesRepository
import com.sample.worldofplay.dashboard.viewmodel.DashboardViewModel

class ViewModelFactory(private val apiHelper: ApiHelper) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DashboardViewModel::class.java)) {
            return DashboardViewModel(StoriesRepository(apiHelper)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}