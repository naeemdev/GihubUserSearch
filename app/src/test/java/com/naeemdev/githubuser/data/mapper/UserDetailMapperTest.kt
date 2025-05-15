package com.naeemdev.githubuser.data.mapper

import com.naeemdev.githubuser.data.mapper.UserDetailMapper.mapToDomainModel
import com.naeemdev.githubuser.data.remote.response.UserDetailModel
import com.naeemdev.githubuser.domain.model.UserDetailModelD
import kotlin.test.Test
import kotlin.test.assertEquals

class UserDetailMapperTest {

    @Test
    fun `test mapToDomainModel() should map the object correctly`() {
        val dto = UserDetailModel(
            login = "testUser",
            avatarUrl = "https://avatar.com/testUser",
            gravatarId = "testGravatarId",
            url = "https://api.github.com/users/testUser",
            htmlUrl = "https://github.com/testUser",
            followersUrl = "https://api.github.com/users/testUser/followers",
            followingUrl = "https://api.github.com/users/testUser/following",
            gistsUrl = "https://gist.github.com/testUser",
            starredUrl = "https://github.com/starred",
            subscriptionsUrl = "https://github.com/subscribed",
            organizationsUrl = "https://api.github.com/users/testUser/orgs",
            reposUrl = "https://api.github.com/users/testUser/repos",
            eventsUrl = "https://api.github.com/users/testUser/events",
            receivedEventsUrl = "https://api.github.com/users/testUser/received_events",
            type = "User",
            siteAdmin = true,
            fullName = "Test User",
            company = "Test Corp",
            blog = "https://testuser.com",
            location = "Test City",
            email = "testuser@example.com",
            isHireAble = true,
            bio = "Software Developer",
            twitterUsername = "testUserTweets",
            publicRepos = 10,
            publicGists = 2,
            followers = 50,
            following = 100,
            createdAt = "2020-01-01T00:00:00Z",
            updatedAt = "2020-02-01T00:00:00Z"
        )

        val expectedDomainModel = UserDetailModelD(
            login = "testUser",
            avatarUrl = "https://avatar.com/testUser",
            gravatarId = "testGravatarId",
            url = "https://api.github.com/users/testUser",
            htmlUrl = "https://github.com/testUser",
            followersUrl = "https://api.github.com/users/testUser/followers",
            followingUrl = "https://api.github.com/users/testUser/following",
            gistsUrl = "https://gist.github.com/testUser",
            starredUrl = "https://github.com/starred",
            subscriptionsUrl = "https://github.com/subscribed",
            organizationsUrl = "https://api.github.com/users/testUser/orgs",
            reposUrl = "https://api.github.com/users/testUser/repos",
            eventsUrl = "https://api.github.com/users/testUser/events",
            receivedEventsUrl = "https://api.github.com/users/testUser/received_events",
            type = "User",
            siteAdmin = true,
            fullName = "Test User",
            company = "Test Corp",
            blog = "https://testuser.com",
            location = "Test City",
            email = "testuser@example.com",
            isHireAble = true,
            bio = "Software Developer",
            twitterUsername = "testUserTweets",
            publicRepos = 10,
            publicGists = 2,
            followers = 50,
            following = 100,
            createdAt = "2020-01-01T00:00:00Z",
            updatedAt = "2020-02-01T00:00:00Z"
        )

        val domainModel = dto.mapToDomainModel()

        assertEquals(expectedDomainModel, domainModel)
    }
    @Test
    fun `test mapToDomainModel() should handle null values correctly`() {
        val dto = UserDetailModel(
            login = null,
            avatarUrl = null,
            gravatarId = null,
            url = null,
            htmlUrl = null,
            followersUrl = null,
            followingUrl = null,
            gistsUrl = null,
            starredUrl = null,
            subscriptionsUrl = null,
            organizationsUrl = null,
            reposUrl = null,
            eventsUrl = null,
            receivedEventsUrl = null,
            type = null,
            siteAdmin = null,
            fullName = null,
            company = null,
            blog = null,
            location = null,
            email = null,
            isHireAble = null,
            bio = null,
            twitterUsername = null,
            publicRepos = null,
            publicGists = null,
            followers = null,
            following = null,
            createdAt = null,
            updatedAt = null
        )

        val expectedDomainModel = UserDetailModelD(
            login = "",
            avatarUrl = "",
            gravatarId = "",
            url = "",
            htmlUrl = "",
            followersUrl = "",
            followingUrl = "",
            gistsUrl = "",
            starredUrl = "",
            subscriptionsUrl = "",
            organizationsUrl = "",
            reposUrl = "",
            eventsUrl = "",
            receivedEventsUrl = "",
            type = "",
            siteAdmin = false,
            fullName = "",
            company = "",
            blog = "",
            location = "",
            email = "",
            isHireAble = false,
            bio = "",
            twitterUsername = "",
            publicRepos = null,
            publicGists = null,
            followers = null,
            following = null,
            createdAt = "",
            updatedAt = ""
        )


        val domainModel = dto.mapToDomainModel()

        assertEquals(expectedDomainModel, domainModel)
    }
}