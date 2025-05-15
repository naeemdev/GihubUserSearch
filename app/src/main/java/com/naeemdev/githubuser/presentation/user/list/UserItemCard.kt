package com.naeemdev.githubuser.presentation.user.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import com.naeemdev.githubuser.R
import com.naeemdev.githubuser.domain.model.UserSearchModelD
import com.naeemdev.githubuser.ui.theme.GihubUserSearchTheme


@Composable
fun UserItemCard(
    modifier: Modifier = Modifier,
    user: UserSearchModelD,
    onClick: (UserSearchModelD) -> Unit = {}
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
    ) {
        Row(
            modifier = Modifier
                .padding(8.dp)
                .clickable {
                    onClick(user)
                },
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                modifier = Modifier
                    .size(60.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop,
                model = ImageRequest.Builder(LocalContext.current)
                    .data(user.avatarUrl)
                    .build(),
                placeholder = painterResource(R.drawable.placeholder),
                error = painterResource(R.drawable.ic_error),
                contentDescription = stringResource(R.string.user_image),
            )

            Text(
                modifier = Modifier
                    .padding(start = 8.dp)
                    .fillMaxWidth()
                    .weight(1f),
                text = user.login,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.bodyLarge
            )

            Icon(
                painter = painterResource(R.drawable.outline_navigate_next_24),
                contentDescription = "Next",
                modifier = Modifier.padding(10.dp),
                tint = MaterialTheme.colorScheme.primary
            )
        }
        HorizontalDivider(
            modifier = Modifier.padding(start = 80.dp, end = 16.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewVenueCard() {
    GihubUserSearchTheme {
        UserItemCard(
            user = UserSearchModelD(
                id = 1,
                login = "Sample ",
                avatarUrl = "https://example.com/image.jpg"
            )
        )
    }
}