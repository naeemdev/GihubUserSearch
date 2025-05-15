package com.naeemdev.githubuser.presentation.user.detail.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.naeemdev.githubuser.R
import com.naeemdev.githubuser.domain.model.UserDetailModelD
import com.naeemdev.githubuser.ui.theme.GihubUserSearchTheme
import com.naeemdev.githubuser.ui.theme.TextColorPrimary
import com.naeemdev.githubuser.ui.theme.TextColorSecondary

@Composable
fun StatsSection(
    userDetailModelD: UserDetailModelD,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        StatItem("${userDetailModelD.publicRepos}", stringResource(R.string.repositories))
        StatItem("${userDetailModelD.publicGists}", stringResource(R.string.gists))
        StatItem("${userDetailModelD.followers}", stringResource(R.string.followers))
        StatItem("${userDetailModelD.following}", stringResource(R.string.following))
    }
}

@Composable
fun StatItem(value: String, label: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = value,
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
            color = TextColorPrimary
        )
        Text(text = label, style = MaterialTheme.typography.labelSmall, color = TextColorSecondary)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewStatsSection() {
    GihubUserSearchTheme {
        StatsSection(
            userDetailModelD = UserDetailModelD(
                publicRepos = 25,
                publicGists = 5,
                followers = 150,
                following = 120
            )
        )
    }
}