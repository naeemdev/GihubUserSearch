package com.naeemdev.githubuser.data.mapper

import com.naeemdev.githubuser.data.remote.response.UserDetailModel
import com.naeemdev.githubuser.domain.model.UserDetailModelD

object UserDetailMapper {

    // Convert a UserDetailModel (DTO) to a UserDetailModelD (Domain Model)
    fun UserDetailModel.mapToDomainModel(): UserDetailModelD {
        return UserDetailModelD(
            login = login.orEmpty(),
            avatarUrl = avatarUrl.orEmpty(),
            gravatarId = gravatarId.orEmpty(),
            url = url.orEmpty(),
            htmlUrl = htmlUrl.orEmpty(),
            followersUrl = followersUrl.orEmpty(),
            followingUrl = followingUrl.orEmpty(),
            gistsUrl = gistsUrl.orEmpty(),
            starredUrl = starredUrl.orEmpty(),
            subscriptionsUrl = subscriptionsUrl.orEmpty(),
            organizationsUrl = organizationsUrl.orEmpty(),
            reposUrl = reposUrl.orEmpty(),
            eventsUrl = eventsUrl.orEmpty(),
            receivedEventsUrl = receivedEventsUrl.orEmpty(),
            type = type.orEmpty(),
            siteAdmin = siteAdmin != null,
            fullName = fullName.orEmpty(),
            company = company.orEmpty(),
            blog = blog.orEmpty(),
            location = location.orEmpty(),
            email = email.orEmpty(),
            isHireAble = isHireAble != null,
            bio = bio.orEmpty(),
            twitterUsername = twitterUsername.orEmpty(),
            publicRepos = publicRepos,
            publicGists =  publicGists,
            followers = followers,
            following = following,
            createdAt = createdAt.orEmpty(),
            updatedAt = updatedAt.orEmpty()
        )
    }
}