package com.naeemdev.githubuser.domain.usecases

import com.naeemdev.githubuser.domain.ErrorType
import com.naeemdev.githubuser.domain.Resource
import com.naeemdev.githubuser.domain.model.UserSearchModelD
import com.naeemdev.githubuser.domain.repositories.GithubRepository
import com.naeemdev.githubuser.domain.usecase.UserFollowersUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import kotlin.test.Test


class UserFollowersUseCaseTest {

    private val repository: GithubRepository = mockk()
    private val userFollowersUseCase = UserFollowersUseCase(repository)

    @Test
    fun `test invoke should return Resource Success when repository returns user followers`() = runTest {

        val userName = "user1"
        val expectedFollowers = listOf(
            UserSearchModelD(login = "follower1", id = 1, avatarUrl = "https://avatar.com/follower1"),
            UserSearchModelD(login = "follower2", id = 2, avatarUrl = "https://avatar.com/follower2")
        )
        coEvery { repository.getUserFollowers(userName) } returns Resource.Success(expectedFollowers)

        val result = userFollowersUseCase.invoke(userName)

        assertTrue(result is Resource.Success)
        assertEquals(expectedFollowers, (result as Resource.Success).data)
        coVerify { repository.getUserFollowers(userName) }
    }

    @Test
    fun `test invoke should return Resource Error when repository returns error`() = runTest {

        val userName = "user1"
        val error = Resource.Error<List<UserSearchModelD>>(ErrorType.UNKNOWN, null)
        coEvery { repository.getUserFollowers(userName) } returns error

        val result = userFollowersUseCase.invoke(userName)

        assertTrue(result is Resource.Error)
        assertEquals(error, result)
        coVerify { repository.getUserFollowers(userName) }
    }

    @Test
    fun `test invoke should return Resource Success with empty list when repository returns empty followers list`() = runTest {

        val userName = "user1"
        coEvery { repository.getUserFollowers(userName) } returns Resource.Success(emptyList())

        val result = userFollowersUseCase.invoke(userName)

        assertTrue(result is Resource.Success)
        assertEquals(emptyList<UserSearchModelD>(), (result as Resource.Success).data)
        coVerify { repository.getUserFollowers(userName) }
    }
}