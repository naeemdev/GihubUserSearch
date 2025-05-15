package com.naeemdev.githubuser.data.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserDetailModel(
    @SerialName("login") var login: String? = null,
    @SerialName("id") var id: Int? = null,
    @SerialName("node_id") var nodeId: String? = null,
    @SerialName("avatar_url") var avatarUrl: String? = null,
    @SerialName("gravatar_id") var gravatarId: String? = null,
    @SerialName("url") var url: String? = null,
    @SerialName("html_url") var htmlUrl: String? = null,
    @SerialName("followers_url") var followersUrl: String? = null,
    @SerialName("following_url") var followingUrl: String? = null,
    @SerialName("gists_url") var gistsUrl: String? = null,
    @SerialName("starred_url") var starredUrl: String? = null,
    @SerialName("subscriptions_url") var subscriptionsUrl: String? = null,
    @SerialName("organizations_url") var organizationsUrl: String? = null,
    @SerialName("repos_url") var reposUrl: String? = null,
    @SerialName("events_url") var eventsUrl: String? = null,
    @SerialName("received_events_url") var receivedEventsUrl: String? = null,
    @SerialName("type") var type: String? = null,
    @SerialName("site_admin") var siteAdmin: Boolean? = null,
    @SerialName("name") var fullName: String? = null,
    @SerialName("company") var company: String? = null,
    @SerialName("blog") var blog: String? = null,
    @SerialName("location") var location: String? = null,
    @SerialName("email") var email: String? = null,
    @SerialName("hireable") var isHireAble: Boolean? = null,
    @SerialName("bio") var bio: String? = null,
    @SerialName("twitter_username") var twitterUsername: String? = null,
    @SerialName("public_repos") var publicRepos: Int? = null,
    @SerialName("public_gists") var publicGists: Int? = null,
    @SerialName("followers") var followers: Int? = null,
    @SerialName("following") var following: Int? = null,
    @SerialName("created_at") var createdAt: String? = null,
    @SerialName("updated_at") var updatedAt: String? = null,
)



