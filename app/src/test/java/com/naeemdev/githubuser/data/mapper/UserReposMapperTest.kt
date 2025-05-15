package com.naeemdev.githubuser.data.mapper

import com.naeemdev.githubuser.data.mapper.UserReposMapper.mapToDomain
import com.naeemdev.githubuser.data.remote.response.UserReposResponse
import com.naeemdev.githubuser.domain.model.UserReposD
import org.junit.Assert.assertTrue
import kotlin.test.Test
import kotlin.test.assertEquals

class UserReposMapperTest {

    @Test
    fun `test mapToDomain should map a UserReposResponse to UserReposD correctly`() {

        val dto = UserReposResponse(
            name = "repoName",
            id = 123,
            htmlUrl = "https://github.com/repoName",
            language = "Kotlin",
            description = "repoDescription",
            stargazersCount = 0
        )

        val expectedDomainModel = UserReposD(
            name = "repoName",
            id = 123,
            htmlUrl = "https://github.com/repoName",
            language = "Kotlin",
            stargazersCount = 0,
            description = "repoDescription"
        )


        val domainModel = dto.mapToDomain()

        assertEquals(expectedDomainModel, domainModel)
    }

    @Test
    fun `test mapToDomain should handle null and empty values correctly`() {

        val dto = UserReposResponse(
            name = null,
            id = null,
            htmlUrl = null,
            language = null
        )

        val expectedDomainModel = UserReposD(
            name = "",
            id = 0,
            htmlUrl = "",
            language = "",
            stargazersCount = 0,
            description = ""
        )

        val domainModel = dto.mapToDomain()

        assertEquals(expectedDomainModel, domainModel)
    }

    @Test
    fun `test mapToDomainList should map a list of UserReposResponse to a list of UserReposD`() {

        val dtoList = listOf(
            UserReposResponse(
                name = "repo1",
                id = 1,
                htmlUrl = "https://github.com/repo1",
                language = "Kotlin",
                stargazersCount = 0,
                description = "repoDescription"
            ),
            UserReposResponse(
                name = "repo2",
                id = 2,
                htmlUrl = "https://github.com/repo2",
                language = "Java",
                stargazersCount = 0,
                description = "repoDescription"
            )
        )

        val expectedDomainList = listOf(
            UserReposD(
                name = "repo1",
                id = 1,
                htmlUrl = "https://github.com/repo1",
                language = "Kotlin",
                stargazersCount = 0,
                description = "repoDescription"
            ),
            UserReposD(
                name = "repo2",
                id = 2,
                htmlUrl = "https://github.com/repo2",
                language = "Java",
                stargazersCount = 0,
                description = "repoDescription"
            )
        )

        val domainList = UserReposMapper.mapToDomainList(dtoList)
        assertEquals(expectedDomainList, domainList)
    }

    @Test
    fun `test mapToDomainList should handle an empty list correctly`() {

        val dtoList: List<UserReposResponse> = emptyList()


        val domainList = UserReposMapper.mapToDomainList(dtoList)

        assertTrue(domainList.isEmpty())
    }

}