package com.sample.worldofplay.utils

class AppConstants {
    companion object {

        const val PASSWORD_CHAR_LENGTH = 5

        const val DASHBOARD_TOP_STORIES_URL: String =
            "https://hacker-news.firebaseio.com/v0/topstories.json"
        const val DASHBOARD_INDIVIDUAL_STORY_URL: String =
            "https://hacker-news.firebaseio.com/v0/item/"
    }
}