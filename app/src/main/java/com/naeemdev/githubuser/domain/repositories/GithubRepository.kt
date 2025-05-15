package com.naeemdev.githubuser.domain.repositories

import com.naeemdev.githubuser.domain.Resource
import com.naeemdev.githubuser.domain.model.UserDetailModelD
import com.naeemdev.githubuser.domain.model.UserReposD
import com.naeemdev.githubuser.domain.model.UserSearchModelD


interface GithubRepository {
    suspend fun getUserList(): Resource<List<UserSearchModelD>>
    suspend fun searchUser(query: String): Resource<List<UserSearchModelD>>
    suspend fun userDetail(userName: String): Resource<UserDetailModelD?>
    suspend fun userRepos(userName: String): Resource<List<UserReposD>>
    suspend fun getUserFollowers(userName: String): Resource<List<UserSearchModelD>>
    suspend fun getUserFollowing(userName: String): Resource<List<UserSearchModelD>>
}