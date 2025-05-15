package com.naeemdev.githubuser.domain.usecases

import com.naeemdev.githubuser.domain.ErrorType
import com.naeemdev.githubuser.domain.Resource
import com.naeemdev.githubuser.domain.model.UserSearchModelD
import com.naeemdev.githubuser.domain.repositories.GithubRepository
import com.naeemdev.githubuser.domain.usecase.GetUserListUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.verify
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals


class GetUserListUseCaseTest {

    private val repository: GithubRepository = mockk()
    private val getUserListUseCase = GetUserListUseCase(repository)

    @Test
    fun `test invoke should return Resource Success when repository returns success`() = runTest {

        val expectedList = listOf(
            UserSearchModelD(login = "user1", id = 1, avatarUrl = "https://avatar.com/user1"),
            UserSearchModelD(login = "user2", id = 2, avatarUrl = "https://avatar.com/user2")
        )
        coEvery { repository.getUserList() } returns Resource.Success(expectedList)

        val result = getUserListUseCase.invoke()


        assertTrue(result is Resource.Success)
        assertEquals(expectedList, (result as Resource.Success).data)
        coVerify { repository.getUserList() }
    }

    @Test
    fun `test invoke should return Resource Error when repository returns error`() = runTest {

        val error = Resource.Error<List<UserSearchModelD>>(ErrorType.NO_INTERNET, null)
        coEvery { repository.getUserList() } returns error

        val result = getUserListUseCase.invoke()

        assertTrue(result is Resource.Error)
        assertEquals(error, result)
        coVerify { repository.getUserList() }
    }

    @Test
    fun `test invoke should return Resource Success with empty list when repository returns empty list`() = runTest {

        val emptyList = emptyList<UserSearchModelD>()
        coEvery { repository.getUserList() } returns Resource.Success(emptyList)


        val result = getUserListUseCase.invoke()

        assertTrue(result is Resource.Success)
        assertEquals(emptyList, (result as Resource.Success).data)
        coVerify { repository.getUserList() }
    }
}