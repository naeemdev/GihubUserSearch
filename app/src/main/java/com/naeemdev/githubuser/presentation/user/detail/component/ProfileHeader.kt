package com.naeemdev.githubuser.presentation.user.detail.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.naeemdev.githubuser.R
import com.naeemdev.githubuser.ui.theme.BlueTagBackground
import com.naeemdev.githubuser.ui.theme.GihubUserSearchTheme
import com.naeemdev.githubuser.ui.theme.GreenAccent
import com.naeemdev.githubuser.ui.theme.TextColorPrimary
import com.naeemdev.githubuser.ui.theme.TextColorSecondary

@Composable
fun ProfileHeader(
    fullName: String,
    bio: String? = null,
    userName: String,
    imageUrl: String,
    isHireAble: Boolean = false,
    onClick: () -> Unit = { }
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp, start = 16.dp, end = 16.dp)
    ) {

        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(imageUrl)
                .crossfade(true)
                .build(),
            contentDescription = stringResource(R.string.profile_picture),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
                .border(2.dp, Color.DarkGray, CircleShape)
        )


        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = fullName,
            style = MaterialTheme.typography.titleLarge.copy(
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            ),
            color = TextColorPrimary
        )
        Text(
            text = "@$userName",
            style = MaterialTheme.typography.bodyMedium,
            color = TextColorSecondary,
            fontSize = 16.sp
        )
        if (isHireAble) {
            Text(
                modifier = Modifier
                    .background(BlueTagBackground, shape = RoundedCornerShape(4.dp))
                    .padding(5.dp),
                text = stringResource(R.string.available_for_hire),
                style = MaterialTheme.typography.bodyMedium,
                color = White,
                fontSize = 16.sp
            )
        }
        bio?.let {
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = it,
                style = MaterialTheme.typography.bodyMedium,
                color = TextColorSecondary,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { onClick() },
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = GreenAccent),
            modifier = Modifier.fillMaxWidth(0.9f)
        ) {
            Text("Follow", color = White, fontWeight = FontWeight.Bold)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewProfileHeader() {
    GihubUserSearchTheme {
        ProfileHeader(
            fullName = "Naeem",
            userName = "naeemdev",
            imageUrl = "https://example.com/profile.jpg",
            isHireAble = true,
            bio = "Software Developer | Passionate about technology and open source"
        )
    }
}