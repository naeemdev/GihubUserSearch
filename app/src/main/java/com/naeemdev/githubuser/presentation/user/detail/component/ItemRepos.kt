package com.naeemdev.githubuser.presentation.user.detail.component

import android.accessibilityservice.GestureDescription
import android.graphics.drawable.Icon
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.naeemdev.githubuser.R
import com.naeemdev.githubuser.ui.theme.GihubUserSearchTheme
import com.naeemdev.githubuser.ui.theme.GreenAccent
import com.naeemdev.githubuser.ui.theme.TextColorPrimary

@Composable
fun ItemRepos(
    name: String,
    programmingLanguage: String,
    description: String,
    stargazersCount: Int,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp, horizontal = 5.dp),
        shape = RoundedCornerShape(5.dp),
        onClick = { onClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(
            containerColor = White
        )
    ) {
        Column(
            modifier = Modifier.padding(start = 8.dp)
        ) {
            Row(
                verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
            ) {
                Text(
                    text = name,
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(vertical = 5.dp)
                )
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = stringResource(R.string.star),
                    tint = Color.Black
                )
                Text(
                    text = stargazersCount.toString(),
                    style = MaterialTheme.typography.labelSmall,
                    color = TextColorPrimary,
                    modifier = Modifier.padding(horizontal = 5.dp)
                )

            }

            if (description.isNotEmpty()) {
                Text(
                    text = description,
                    style = MaterialTheme.typography.titleSmall,
                    color = TextColorPrimary,

                    )
                Spacer(modifier = Modifier.height(4.dp))
            }
            if (programmingLanguage.isNotEmpty()) {
                Text(
                    text = programmingLanguage,
                    style = MaterialTheme.typography.labelSmall,
                    color = White,
                    modifier = Modifier
                        .background(
                            GreenAccent,
                            shape = RoundedCornerShape(5.dp)
                        )
                        .padding(horizontal = 5.dp, vertical = 2.dp)
                )
                Spacer(modifier = Modifier.height(4.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewItemRepos() {
    GihubUserSearchTheme {
        ItemRepos(
            name = "Compose UI",
            programmingLanguage = "Kotlin",
            stargazersCount = 100,
            description = "Compose UI",
            onClick = { }
        )
    }
}