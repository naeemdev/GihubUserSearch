package com.naeemdev.githubuser.data.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class SearchUserResponse(
    @SerialName("items")  val items: List<UserSearchModel>,
    @SerialName("total_count") var totalCount: Int? = null,
    @SerialName("incomplete_results") var inCompleteResults: Boolean? = null,
)




