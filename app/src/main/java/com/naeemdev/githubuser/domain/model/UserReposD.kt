package com.naeemdev.githubuser.domain.model

data class UserReposD(
    val id: Int,
    val name: String,
    val language: String,
    val htmlUrl: String,
    val description: String,
    val stargazersCount: Int?,
)