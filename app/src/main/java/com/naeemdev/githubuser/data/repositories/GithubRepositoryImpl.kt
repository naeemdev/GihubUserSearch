package com.naeemdev.githubuser.data.repositories

import com.naeemdev.githubuser.data.mapper.UserDetailMapper.mapToDomainModel
import com.naeemdev.githubuser.data.mapper.UserReposMapper
import com.naeemdev.githubuser.data.mapper.UserSearchMapper
import com.naeemdev.githubuser.data.remote.network.ApiServiceGithub
import com.naeemdev.githubuser.domain.ErrorHandler.handleApiError
import com.naeemdev.githubuser.domain.ErrorHandler.handleNetworkError
import com.naeemdev.githubuser.domain.ErrorHandler.handleUnknownError
import com.naeemdev.githubuser.domain.Resource
import com.naeemdev.githubuser.domain.model.UserDetailModelD
import com.naeemdev.githubuser.domain.model.UserReposD
import com.naeemdev.githubuser.domain.model.UserSearchModelD
import com.naeemdev.githubuser.domain.repositories.GithubRepository
import java.net.UnknownHostException
import javax.inject.Inject

class GithubRepositoryImpl @Inject constructor(
    private val apiService: ApiServiceGithub,
) : GithubRepository {

    override suspend fun getUserList(): Resource<List<UserSearchModelD>> {
        return try {
            val response = apiService.getUsersList()
            if (response.isSuccessful) {
                val userList = response.body()?.let {
                    UserSearchMapper.mapToDomainList(it)
                } ?: emptyList()

                Resource.Success(userList)
            } else {
                response.handleApiError()
            }

        } catch (exception: UnknownHostException) {
            exception.handleNetworkError()
        } catch (exception: Exception) {
            exception.handleUnknownError()
        }
    }

    override suspend fun searchUser(query: String): Resource<List<UserSearchModelD>> {
        return try {
            val response = apiService.searchUsers(query)
            if (response.isSuccessful) {
                val userList = response.body()?.items?.let {
                    UserSearchMapper.mapToDomainList(it)
                } ?: emptyList()

                Resource.Success(userList)
            } else {
                response.handleApiError()
            }

        } catch (exception: UnknownHostException) {
            exception.handleNetworkError()
        } catch (exception: Exception) {
            exception.handleUnknownError()
        }
    }

    override suspend fun userDetail(userName: String): Resource<UserDetailModelD?> {
        return try {
            val response = apiService.getUserDetails(userName)
            if (response.isSuccessful) {
                val userDetail = response.body()?.mapToDomainModel()
                Resource.Success(userDetail)
            } else {
                response.handleApiError()
            }

        } catch (exception: UnknownHostException) {
            exception.handleNetworkError()
        } catch (exception: Exception) {
            exception.handleUnknownError()
        }
    }

    override suspend fun userRepos(userName: String): Resource<List<UserReposD>> {
        return try {
            val response = apiService.getGithubUserRepos(userName)
            if (response.isSuccessful) {
                val userList = response.body()?.let {
                    UserReposMapper.mapToDomainList(it)
                } ?: emptyList()

                Resource.Success(userList)
            } else {
                response.handleApiError()
            }

        } catch (exception: UnknownHostException) {
            exception.handleNetworkError()
        } catch (exception: Exception) {
            exception.handleUnknownError()
        }
    }

    override suspend fun getUserFollowers(userName: String): Resource<List<UserSearchModelD>> {
        return try {
            val response = apiService.getGithubUserFollowers(userName)
            if (response.isSuccessful) {
                val userList = response.body()?.let {
                    UserSearchMapper.mapToDomainList(it)
                } ?: emptyList()

                Resource.Success(userList)
            } else {
                response.handleApiError()
            }

        } catch (exception: UnknownHostException) {
            exception.handleNetworkError()
        } catch (exception: Exception) {
            exception.handleUnknownError()
        }
    }
    override suspend fun getUserFollowing(userName: String): Resource<List<UserSearchModelD>> {
        return try {
            val response = apiService.getGithubUserFollowing(userName)
            if (response.isSuccessful) {
                val userList = response.body()?.let {
                    UserSearchMapper.mapToDomainList(it)
                } ?: emptyList()

                Resource.Success(userList)
            } else {
                response.handleApiError()
            }

        } catch (exception: UnknownHostException) {
            exception.handleNetworkError()
        } catch (exception: Exception) {
            exception.handleUnknownError()
        }
    }

}