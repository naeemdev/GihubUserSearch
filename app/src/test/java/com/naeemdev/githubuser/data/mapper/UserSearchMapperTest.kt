package com.naeemdev.githubuser.data.mapper

import com.naeemdev.githubuser.data.mapper.UserSearchMapper.mapToDomain
import com.naeemdev.githubuser.data.remote.response.UserSearchModel
import com.naeemdev.githubuser.domain.model.UserSearchModelD
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlin.test.Test

class UserSearchMapperTest {

    @Test
    fun `test mapToDomain should map UserSearchModel to UserSearchModelD correctly`() {
        val dto = UserSearchModel(
            login = "user123",
            id = 12345,
            avatarUrl = "https://avatar.com/user123"
        )

        val expectedDomainModel = UserSearchModelD(
            login = "user123",
            id = 12345,
            avatarUrl = "https://avatar.com/user123"
        )


        val domainModel = dto.mapToDomain()

        assertEquals(expectedDomainModel, domainModel)
    }

    @Test
    fun `test mapToDomain should handle null values correctly`() {

        val dto = UserSearchModel(
            login = null,
            id = null,
            avatarUrl = null
        )

        val expectedDomainModel = UserSearchModelD(
            login = "",
            id = 0,
            avatarUrl = ""
        )

        val domainModel = dto.mapToDomain()

        assertEquals(expectedDomainModel, domainModel)
    }

    @Test
    fun `test mapToDomainList should map a list of UserSearchModel to a list of UserSearchModelD correctly`() {

        val dtoList = listOf(
            UserSearchModel(login = "user1", id = 1, avatarUrl = "https://avatar.com/user1"),
            UserSearchModel(login = "user2", id = 2, avatarUrl = "https://avatar.com/user2")
        )

        val expectedDomainList = listOf(
            UserSearchModelD(login = "user1", id = 1, avatarUrl = "https://avatar.com/user1"),
            UserSearchModelD(login = "user2", id = 2, avatarUrl = "https://avatar.com/user2")
        )

        val domainList = UserSearchMapper.mapToDomainList(dtoList)

        assertEquals(expectedDomainList, domainList)
    }

    @Test
    fun `test mapToDomainList should handle empty list correctly`() {

        val dtoList: List<UserSearchModel> = emptyList()

        val domainList = UserSearchMapper.mapToDomainList(dtoList)

        assertTrue(domainList.isEmpty())
    }
}