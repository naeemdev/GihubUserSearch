package com.naeemdev.githubuser.presentation.user.list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.naeemdev.githubuser.R
import com.naeemdev.githubuser.domain.model.UserSearchModelD
import com.naeemdev.githubuser.presentation.component.EmptyView
import com.naeemdev.githubuser.presentation.component.ErrorDialog
import com.naeemdev.githubuser.presentation.component.LoadingView
import com.naeemdev.githubuser.presentation.user.list.viewmodel.UserViewModel
import com.naeemdev.githubuser.ui.theme.GihubUserSearchTheme

@Composable
fun UserListScreen(
    gotoUserDetailScreen: (String) -> Unit,
) {
    val viewModel: UserViewModel = hiltViewModel()
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
    UserListContent(
        uiState = uiState.value,
        onUiEvent = viewModel::onEvent,
        gotoUserDetailScreen = gotoUserDetailScreen
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserListContent(
    uiState: UiState,
    gotoUserDetailScreen: (String) -> Unit,
    onUiEvent: (UIEvent) -> Unit
) {
    var searchText by remember { mutableStateOf(TextFieldValue("")) }
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.app_name),
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                }
            )
        },
        containerColor = White
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
        ) {
            OutlinedTextField(
                value = searchText,
                onValueChange = {
                    searchText = it
                    onUiEvent(UIEvent.SearchUser(it.text))
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                placeholder = { Text(stringResource(R.string.search_word_here)) },
                leadingIcon = {
                    Icon(
                        Icons.Filled.Search,
                        contentDescription = "Search Icon"
                    )
                },
                shape = RoundedCornerShape(50),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                    unfocusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                    disabledContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent
                )
            )
            if (uiState.dataList.isEmpty() && !uiState.isLoading){
                EmptyView(
                    message = stringResource(R.string.no_data_found),
                )
            }else {
                LazyColumn(
                    modifier = Modifier
                ) {
                    itemsIndexed(
                        items = uiState.dataList,
                        key = { index, key -> "$index${key.id}" }
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
                onUiEvent(UIEvent.DismissDialog)
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun UserListContentPreview() {
    val uiState = UiState(
        dataList = listOf(
            UserSearchModelD(
                id = 1,
                login = "test 1",
                avatarUrl = ""
            ),
            UserSearchModelD(
                id = 2,
                login = "test 2",
                avatarUrl = ""
            ),
            UserSearchModelD(
                id = 3,
                login = "test 3",
                avatarUrl = ""
            )
        )
    )
    GihubUserSearchTheme {
        UserListContent(
            uiState = uiState,
            gotoUserDetailScreen = {},
            onUiEvent = {},
        )
    }
}