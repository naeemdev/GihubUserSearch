package com.naeemdev.githubuser.domain

import com.naeemdev.githubuser.R

enum class ErrorType(val errorMessageRes: Int) {
    NO_INTERNET(R.string.error_no_internet),
    FORBIDDEN(R.string.error_forbidden),
    UNAUTHORIZED(R.string.error_unauthorized),
    UNKNOWN(R.string.error_unknown),
    INTERNAL_SERVER_ERROR(R.string.error_internal_server)
}