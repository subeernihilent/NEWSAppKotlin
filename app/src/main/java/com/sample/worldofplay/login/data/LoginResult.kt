package com.sample.worldofplay.login.data

import com.sample.worldofplay.login.view.LoggedInUserView

/**
 * Authentication result : success (user details) or error message.
 */
data class LoginResult (
    val success: LoggedInUserView? = null,
    val error:Int? = null
)
