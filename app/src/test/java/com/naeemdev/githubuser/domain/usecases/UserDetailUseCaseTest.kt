package com.naeemdev.githubuser.domain.usecases

import com.naeemdev.githubuser.domain.ErrorType
import com.naeemdev.githubuser.domain.Resource
import com.naeemdev.githubuser.domain.model.UserDetailModelD
import com.naeemdev.githubuser.domain.repositories.GithubRepository
import com.naeemdev.githubuser.domain.usecase.UserDetailUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals

class UserDetailUseCaseTest {

        private val repository: GithubRepository = mockk()
        private val userDetailUseCase = UserDetailUseCase(repository)

        @Test
        fun `test invoke should return Resource Success when repository returns user details`() = runTest {

            val userName = "user1"
            val expectedUserDetail = UserDetailModelD(
                login = "user1",
                avatarUrl = "https://avatar.com/user1",
                fullName = "User One",
                bio = "Developer",
                siteAdmin = false,
            )
            coEvery { repository.userDetail(userName) } returns Resource.Success(expectedUserDetail)

            val result = userDetailUseCase.invoke(userName)

            assertTrue(result is Resource.Success)
            assertEquals(expectedUserDetail, (result as Resource.Success).data)
            coVerify { repository.userDetail(userName) }
        }

        @Test
        fun `test invoke should return Resource Error when repository returns error`() = runTest {

            val userName = "user1"
            val error = Resource.Error<UserDetailModelD?>(ErrorType.UNAUTHORIZED, null)
            coEvery { repository.userDetail(userName) } returns error

            val result = userDetailUseCase.invoke(userName)

            assertTrue(result is Resource.Error)
            assertEquals(error, result)
            coVerify { repository.userDetail(userName) }
        }

    }