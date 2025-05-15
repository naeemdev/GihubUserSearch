package com.naeemdev.githubuser.domain.usecases

import com.naeemdev.githubuser.domain.ErrorType
import com.naeemdev.githubuser.domain.Resource
import com.naeemdev.githubuser.domain.model.UserSearchModelD
import com.naeemdev.githubuser.domain.repositories.GithubRepository
import com.naeemdev.githubuser.domain.usecase.SearchUserUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals


class SearchUserUseCaseTest {

    private val repository: GithubRepository = mockk()
    private val searchUserUseCase = SearchUserUseCase(repository)

    @Test
    fun `test invoke should return Resource Success when repository returns success`() = runTest {
        val query = "user"
        val expectedList = listOf(
            UserSearchModelD(login = "user1", id = 1, avatarUrl = "https://avatar.com/user1"),
            UserSearchModelD(login = "user2", id = 2, avatarUrl = "https://avatar.com/user2")
        )
        coEvery { repository.searchUser(query) } returns Resource.Success(expectedList)

        val result = searchUserUseCase.invoke(query)

        assertTrue(result is Resource.Success)
        assertEquals(expectedList, (result as Resource.Success).data)
        coVerify { repository.searchUser(query) }
    }

    @Test
    fun `test invoke should return Resource Error when repository returns error`() = runTest {
        val query = "user"
        val error = Resource.Error<List<UserSearchModelD>>(ErrorType.INTERNAL_SERVER_ERROR, null)
        coEvery { repository.searchUser(query) } returns error

        val result = searchUserUseCase.invoke(query)

        assertTrue(result is Resource.Error)
        assertEquals(error, result)
        coVerify { repository.searchUser(query) }
    }

    @Test
    fun `test invoke should return Resource Success with empty list when repository returns empty list`() = runTest {

        val query = "user"
        val emptyList = emptyList<UserSearchModelD>()
        coEvery { repository.searchUser(query) } returns Resource.Success(emptyList)

        val result = searchUserUseCase.invoke(query)

        assertTrue(result is Resource.Success)
        assertEquals(emptyList, (result as Resource.Success).data)
        coVerify { repository.searchUser(query) }
    }
}