package com.naeemdev.githubuser.data.remote.network

import com.naeemdev.githubuser.data.remote.response.SearchUserResponse
import com.naeemdev.githubuser.data.remote.response.UserDetailModel
import com.naeemdev.githubuser.data.remote.response.UserReposResponse
import com.naeemdev.githubuser.data.remote.response.UserSearchModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiServiceGithub {

    @GET("users")
    suspend fun getUsersList(): Response<List<UserSearchModel>>

    @GET("users/{username}")
    suspend fun getUserDetails(
        @Path("username") username: String
    ): Response<UserDetailModel>

    @GET("search/users")
    suspend fun searchUsers(
        @Query("q") query: String?
    ): Response<SearchUserResponse>

    @GET("users/{username}/followers")
    suspend fun getGithubUserFollowers(
        @Path("username") username: String
    ): Response<List<UserSearchModel>>

    @GET("users/{username}/following")
    suspend fun getGithubUserFollowing(
        @Path("username") username: String,
    ): Response<List<UserSearchModel>>

    @GET("users/{username}/repos")
    suspend fun getGithubUserRepos(
        @Path("username") username: String,
    ): Response<List<UserReposResponse>>
}