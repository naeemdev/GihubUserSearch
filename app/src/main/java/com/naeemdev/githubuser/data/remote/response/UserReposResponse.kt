package com.naeemdev.githubuser.data.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserReposResponse(
    @SerialName("login") var login: String? = null,
    @SerialName("id") var id: Int? = null,
    @SerialName("node_id") var nodeId: String? = null,
    @SerialName("name") var name: String? = null,
    @SerialName("owner") var owner: UserSearchModel? = null,
    @SerialName("full_name") var fullName: String? = null,
    @SerialName("description") var description: String? = null,
    @SerialName("forks_url") var forksUrl: String? = null,
    @SerialName("html_url") var htmlUrl: String? = null,
    @SerialName("created_at") var createdAt: String? = null,
    @SerialName("updated_at") var updatedAt: String? = null,
    @SerialName("language") var language: String? = null,
    @SerialName("stargazers_count") var stargazersCount: Int? = null,
)




