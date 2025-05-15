package com.naeemdev.githubuser.domain.usecases

import com.naeemdev.githubuser.domain.ErrorType
import com.naeemdev.githubuser.domain.Resource
import com.naeemdev.githubuser.domain.model.UserReposD
import com.naeemdev.githubuser.domain.repositories.GithubRepository
import com.naeemdev.githubuser.domain.usecase.UserReposUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.test.runTest
import kotlin.test.Test


class UserReposUseCaseTest {

    private val repository: GithubRepository = mockk()
    private val userReposUseCase = UserReposUseCase(repository)

    @Test
    fun `test invoke should return Resource Success when repository returns user repos`() =
        runTest {

            val userName = "user1"
            val expectedRepos = listOf(
                UserReposD(
                    name = "repo1",
                    id = 1,
                    htmlUrl = "https://github.com/user1/repo1",
                    language = "Kotlin",
                    stargazersCount = 0,
                    description = "repoDescription"
                ),
                UserReposD(
                    name = "repo2",
                    id = 2,
                    htmlUrl = "https://github.com/user1/repo2",
                    language = "Java",
                    stargazersCount = 0,
                    description = "repoDescription"
                )
            )
            coEvery { repository.userRepos(userName) } returns Resource.Success(expectedRepos)

            val result = userReposUseCase.invoke(userName)

            assertTrue(result is Resource.Success)
            assertEquals(expectedRepos, (result as Resource.Success).data)
            coVerify { repository.userRepos(userName) }
        }

    @Test
    fun `test invoke should return Resource Error when repository returns error`() = runTest {

        val userName = "user1"
        val error = Resource.Error<List<UserReposD>>(ErrorType.UNAUTHORIZED, null)
        coEvery { repository.userRepos(userName) } returns error

        val result = userReposUseCase.invoke(userName)

        assertTrue(result is Resource.Error)
        assertEquals(error, result)
        coVerify { repository.userRepos(userName) }
    }

    @Test
    fun `test invoke should return Resource Success with empty list when repository returns empty repos list`() =
        runTest {

            val userName = "user1"
            coEvery { repository.userRepos(userName) } returns Resource.Success(emptyList())
            val result = userReposUseCase.invoke(userName)

            assertTrue(result is Resource.Success)
            assertEquals(emptyList<UserReposD>(), (result as Resource.Success).data)
            coVerify { repository.userRepos(userName) }
        }
}