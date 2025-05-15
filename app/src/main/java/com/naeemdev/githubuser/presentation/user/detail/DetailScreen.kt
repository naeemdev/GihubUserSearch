package com.naeemdev.githubuser.presentation.user.detail

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.naeemdev.githubuser.R
import com.naeemdev.githubuser.domain.model.UserDetailModelD
import com.naeemdev.githubuser.domain.model.UserReposD
import com.naeemdev.githubuser.domain.model.UserSearchModelD
import com.naeemdev.githubuser.presentation.component.ErrorDialog
import com.naeemdev.githubuser.presentation.component.LoadingView
import com.naeemdev.githubuser.presentation.user.detail.DetailUIEvent.DismissDialog
import com.naeemdev.githubuser.presentation.user.detail.component.ItemRepos
import com.naeemdev.githubuser.presentation.user.detail.component.ProfileHeader
import com.naeemdev.githubuser.presentation.user.detail.component.StatsSection
import com.naeemdev.githubuser.presentation.user.detail.component.TabSection
import com.naeemdev.githubuser.presentation.user.detail.viewmodel.UserDetailViewModel
import com.naeemdev.githubuser.presentation.user.list.UserItemCard
import com.naeemdev.githubuser.ui.theme.GihubUserSearchTheme

@Composable
fun DetailScreen(
    onBackClicked: () -> Unit,
    gotoUserDetailScreen: (String) -> Unit,
) {
    val viewModel: UserDetailViewModel = hiltViewModel()
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
    val currentActivity = LocalActivity.current
    DetailScreenContent(
        uiState = uiState.value,
        onUiEvent = viewModel::onEvent,
        onBackClicked = onBackClicked,
        gotoUserDetailScreen = {
            gotoUserDetailScreen(it)
        },
        onOpenUrlClick = {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(it))
            currentActivity?.startActivity(intent)
        }
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreenContent(
    uiState: DetailUiState,
    onUiEvent: (DetailUIEvent) -> Unit,
    onBackClicked: () -> Unit,
    onOpenUrlClick: (String) -> Unit,
    gotoUserDetailScreen: (String) -> Unit,
) {

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = uiState.userDetailModel?.fullName.orEmpty(),
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = { onBackClicked() },
                        modifier = Modifier
                            .size(40.dp)
                            .padding(start = 8.dp)
                    ) {
                        Icon(
                            modifier = Modifier.size(30.dp),
                            painter = painterResource(R.drawable.outline_arrow_back_24),
                            contentDescription = stringResource(R.string.back)
                        )
                    }
                }
            )
        },
        containerColor = White
    ) { innerPadding ->
        val context = LocalActivity.current
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (uiState.userDetailModel != null) {
                item {
                    ProfileHeader(
                        fullName = uiState.userDetailModel.fullName,
                        userName = uiState.userDetailModel.login,
                        imageUrl = uiState.userDetailModel.avatarUrl,
                        bio = uiState.userDetailModel.bio,
                        isHireAble = uiState.userDetailModel.isHireAble,
                        onClick = {
                            Toast.makeText(context,
                                context?.getString(R.string.this_feature_is_not_available_yet), Toast.LENGTH_SHORT).show()
                        }
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    StatsSection(uiState.userDetailModel)

                    Spacer(modifier = Modifier.height(24.dp))
                }
                stickyHeader {
                    TabSection(
                        modifier = Modifier.background(White),
                        tabs = listOf(
                            R.string.repositories,
                            R.string.followers,
                            R.string.following
                        ),
                        selectedTab = uiState.selectedTab,
                        onTabSelected = { onUiEvent.invoke(DetailUIEvent.UpdateSelectedTab(it)) }
                    )
                }

                item { Spacer(modifier = Modifier.height(24.dp)) }

                when (uiState.selectedTab) {
                    0 -> {
                        itemsIndexed(
                            items = uiState.repositories,
                            key = { index, key -> "$index${key.name}${key.id}" }
                        ) { _, repo ->
                            ItemRepos(
                                name = repo.name,
                                programmingLanguage = repo.language,
                                stargazersCount = repo.stargazersCount ?: 0,
                                description = repo.description,
                                onClick = {
                                    onOpenUrlClick(
                                        repo.htmlUrl
                                    )
                                }
                            )
                        }

                    }

                    1, 2 -> {
                        itemsIndexed(
                            items = uiState.userList,
                            key = { index, key -> "$index${key.login}${key.id}" }
                        ) { _, userData ->
                            UserItemCard(
                                onClick = {
                                    gotoUserDetailScreen(userData.login)
                                },
                                user = userData,
                            )
                        }

                    }

                }


            }
        }
        if (uiState.isLoading) {
            LoadingView()
        }
        if (uiState.error != null) {
            ErrorDialog(
                description = stringResource(uiState.error),
                buttonText = stringResource(R.string.dismiss_button),
                onClickButton = {
                    onUiEvent(DismissDialog)
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDetailScreenContent() {
    val sampleUserDetailModel = UserDetailModelD(
        fullName = "Naeem",
        login = "naeemdev",
        avatarUrl = "https://via.placeholder.com/150",
        bio = "Sample bio",
        followers=0,
        following=0,
        publicRepos=0,
        publicGists=0,
        isHireAble = true
    )

    val sampleRepositories = listOf(
        UserReposD(
            name = "Repo 1",
            language = "Kotlin",
            htmlUrl = "https://github.com/naeemdev/repo1",
            id = 1,
            description = "Sample description",
            stargazersCount = 10
        ),
        UserReposD(
            name = "Repo 2",
            language = "Java",
            htmlUrl = "https://github.com/naeemdev/repo2",
            id = 2,
            description = "Sample description",
            stargazersCount = 10
        )
    )


    val uiState = DetailUiState(
        userDetailModel = sampleUserDetailModel,
        repositories = sampleRepositories,
        userList = emptyList(),
        selectedTab = 0,
        isLoading = false,
        error = null
    )
    GihubUserSearchTheme {
        DetailScreenContent(
            uiState = uiState,
            onUiEvent = { },
            onBackClicked = { },
            onOpenUrlClick = { },
            gotoUserDetailScreen = { }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewFallowerContent() {
    val sampleUserDetailModel = UserDetailModelD(
        fullName = "Naeem",
        login = "naeemdev",
        avatarUrl = "https://via.placeholder.com/150",
        bio = "Sample bio",
        followers=0,
        following=0,
        publicRepos=0,
        publicGists=0,
        isHireAble = true
    )


    val sampleUserList = listOf(
        UserSearchModelD(login = "naeemdev", id = 1, avatarUrl = "https://via.placeholder.com/150"),
        UserSearchModelD(login = "naeemdev", id = 2, avatarUrl = "https://via.placeholder.com/150")
    )

    val uiState = DetailUiState(
        userDetailModel = sampleUserDetailModel,
        repositories = emptyList(),
        userList = sampleUserList,
        selectedTab = 1,
        isLoading = false,
        error = null
    )
    GihubUserSearchTheme {
        DetailScreenContent(
            uiState = uiState,
            onUiEvent = { },
            onBackClicked = { },
            onOpenUrlClick = { },
            gotoUserDetailScreen = { }
        )
    }
}