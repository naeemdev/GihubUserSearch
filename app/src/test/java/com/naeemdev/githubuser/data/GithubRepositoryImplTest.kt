package com.naeemdev.githubuser.data

import com.naeemdev.githubuser.data.mapper.UserDetailMapper.mapToDomainModel
import com.naeemdev.githubuser.data.mapper.UserReposMapper
import com.naeemdev.githubuser.data.mapper.UserSearchMapper
import com.naeemdev.githubuser.data.remote.network.ApiServiceGithub
import com.naeemdev.githubuser.data.remote.response.SearchUserResponse
import com.naeemdev.githubuser.data.remote.response.UserDetailModel
import com.naeemdev.githubuser.data.remote.response.UserReposResponse
import com.naeemdev.githubuser.data.remote.response.UserSearchModel
import com.naeemdev.githubuser.data.repositories.GithubRepositoryImpl
import com.naeemdev.githubuser.domain.ErrorHandler.handleApiError
import com.naeemdev.githubuser.domain.ErrorType
import com.naeemdev.githubuser.domain.Resource
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.MockitoAnnotations

class GithubRepositoryImplTest {
    private lateinit var repository: GithubRepositoryImpl
    private val apiService = mockk<ApiServiceGithub>()


    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        repository = GithubRepositoryImpl(apiService)
    }


    @Test
    fun `test getUserList success`() = runTest {

        val mockResponse = listOf(
            UserSearchModel("user1", 1, "avatarUrl1"),
            UserSearchModel("user2", 2, "avatarUrl2")
        )
        coEvery { apiService.getUsersList() } returns mockk {
            coEvery { isSuccessful } returns true
            coEvery { body() } returns mockResponse
        }

        val result = repository.getUserList()
        assertTrue(result is Resource.Success)
        assertEquals(
            UserSearchMapper.mapToDomainList(mockResponse),
            (result as Resource.Success).data
        )
        coVerify { apiService.getUsersList() }
    }

    @Test
    fun `test getUserList failure with error`() = runTest {
        coEvery { apiService.getUsersList() } returns mockk {
            coEvery { isSuccessful } returns false
            coEvery { handleApiError() } returns Resource.Error(ErrorType.UNAUTHORIZED)
        }


        val result = repository.getUserList()

        assertTrue(result is Resource.Error)
        coVerify { apiService.getUsersList() }
    }


    @Test
    fun `test searchUser success`() = runTest {
        val searchQuery = "searchQuery"
        val mockResponse = SearchUserResponse(
            totalCount = 0,
            inCompleteResults = false,
            items = listOf(
                UserSearchModel("user1", 1, "avatarUrl1"),
                UserSearchModel("user2", 2, "avatarUrl2")
            )
        )

        coEvery { apiService.searchUsers(searchQuery) } returns mockk {
            coEvery { isSuccessful } returns true
            coEvery { body() } returns mockResponse
        }

        val result = repository.searchUser(searchQuery)
        assertTrue(result is Resource.Success)
        assertEquals(
            UserSearchMapper.mapToDomainList(mockResponse.items),
            (result as Resource.Success).data
        )
        coVerify { apiService.searchUsers(searchQuery) }
    }

    @Test
    fun `test userDetail success`() = runTest {
        val userName = "naeemdev"
        val mockUserDetail = UserDetailModel(
            fullName = "Naeem",
            login = "naeemdev",
            avatarUrl = "avatarUrl",
            bio = "bio",
            isHireAble = true
        )
        coEvery { apiService.getUserDetails(any()) } returns mockk {
            coEvery { isSuccessful } returns true
            coEvery { body() } returns mockUserDetail
        }

        val result = repository.userDetail(userName)

        assertTrue(result is Resource.Success)
        assertEquals(mockUserDetail.mapToDomainModel(), (result as Resource.Success).data)
        coVerify { apiService.getUserDetails(userName) }
    }

    @Test
    fun `test userRepos success`() = runTest {
        val userName = "naeemdev"
        val mockRepos = listOf(
            UserReposResponse(name="Repo1",language= "Kotlin", htmlUrl="repoUrl", id=1),
            UserReposResponse(name="Repo2",language= "Java", htmlUrl="repoUrl", id=2),
        )

        coEvery { apiService.getGithubUserRepos(any()) } returns mockk {
            coEvery { isSuccessful } returns true
            coEvery { body() } returns mockRepos
        }

        val result = repository.userRepos(userName)

        assertTrue(result is Resource.Success)
        assertEquals(UserReposMapper.mapToDomainList(mockRepos), (result as Resource.Success).data)
        coVerify { apiService.getGithubUserRepos(userName) }
    }

    @Test
    fun `test getUserFollowers success`() = runTest {
        val userName = "naeemdev"
        val mockFollowers = listOf(
            UserSearchModel("follower1", 1, "avatarUrl1"),
            UserSearchModel("follower2", 2, "avatarUrl2")
        )
        coEvery { apiService.getGithubUserFollowers(any()) } returns mockk {
            coEvery { isSuccessful } returns true
            coEvery { body() } returns mockFollowers
        }

        val result = repository.getUserFollowers(userName)

        assertTrue(result is Resource.Success)
        assertEquals(
            UserSearchMapper.mapToDomainList(mockFollowers),
            (result as Resource.Success).data
        )
        coVerify { apiService.getGithubUserFollowers(userName) }
    }

    @Test
    fun `test getUserFollowing success`() = runTest {
        val userName = "naeemdev"
        val mockFollowers = listOf(
            UserSearchModel("follower1", 1, "avatarUrl1"),
            UserSearchModel("follower2", 2, "avatarUrl2")
        )
        coEvery { apiService.getGithubUserFollowing(any()) } returns mockk {
            coEvery { isSuccessful } returns true
            coEvery { body() } returns mockFollowers
        }

        val result = repository.getUserFollowing(userName)

        assertTrue(result is Resource.Success)
        assertEquals(
            UserSearchMapper.mapToDomainList(mockFollowers),
            (result as Resource.Success).data
        )
        coVerify { apiService.getGithubUserFollowing(userName) }
    }
}