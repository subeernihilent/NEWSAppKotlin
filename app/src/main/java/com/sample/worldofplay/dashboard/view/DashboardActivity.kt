package com.sample.worldofplay.dashboard.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sample.worldofplay.R
import com.sample.worldofplay.core.Status
import com.sample.worldofplay.core.ViewModelFactory
import com.sample.worldofplay.core.network.ApiHelper
import com.sample.worldofplay.core.network.RetrofitBuilder
import com.sample.worldofplay.dashboard.model.Stories
import com.sample.worldofplay.dashboard.model.StoriesResponse
import com.sample.worldofplay.dashboard.viewmodel.DashboardViewModel
import kotlinx.android.synthetic.main.activity_dashboard.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class DashboardActivity : AppCompatActivity() {

    private lateinit var viewModel: DashboardViewModel
    private lateinit var dashboardAdapter: DashboardAdapter
    val list = ArrayList<Stories>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_dashboard)
        initViewModel()

        setUpUI()
        setUpObservers()

    }

    private fun setUpObservers() {
        viewModel.getStoriesId().observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        recyclerView.visibility = View.VISIBLE
                        progressBar.visibility = View.GONE
                        resource.data?.let { users ->
                            getIndividualStories(users)
                        }
                    }
                    Status.ERROR -> {
                        recyclerView.visibility = View.VISIBLE
                        progressBar.visibility = View.GONE
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                        progressBar.visibility = View.VISIBLE
                        recyclerView.visibility = View.GONE
                    }
                }
            }
        })
    }

    private fun getIndividualStories(users: List<Int>) {
        val topStories: List<Int> = users
        for (i in 1..10){
            viewModel.getStory(topStories[i]).observe(this, Observer {
                it?.let { resource ->
                    when (resource.status) {
                        Status.SUCCESS -> {
                            recyclerView.visibility = View.VISIBLE
                            progressBar.visibility = View.GONE
                            resource.data?.let { users ->

                                users.enqueue(object : Callback<StoriesResponse?>{
                                    override fun onFailure(
                                        call: Call<StoriesResponse?>,
                                        t: Throwable
                                    ) {
                                        TODO("Not yet implemented")
                                    }

                                    override fun onResponse(
                                        call: Call<StoriesResponse?>,
                                        response: Response<StoriesResponse?>
                                    ) {
                                        val title: String? = response.body()?.title
                                        val url: String? = response.body()?.url
                                        list.add(Stories(title, url))
                                        dashboardAdapter = DashboardAdapter(this@DashboardActivity, list)
                                        recyclerView.adapter = dashboardAdapter
                                        dashboardAdapter.notifyDataSetChanged()
                                    }

                                })
                            }
                        }
                        Status.ERROR -> {
                            recyclerView.visibility = View.VISIBLE
                            progressBar.visibility = View.GONE
                            Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                        }
                        Status.LOADING -> {
                            progressBar.visibility = View.VISIBLE
                            recyclerView.visibility = View.GONE
                        }
                    }
                }
            })
        }
    }

    private fun setUpUI() {
        recyclerView.addItemDecoration(DividerItemDecoration(this, RecyclerView.VERTICAL))
        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(ApiHelper(RetrofitBuilder.apiService))
        ).get(DashboardViewModel::class.java)
    }
}