package com.naeemdev.githubuser.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.naeemdev.githubuser.R
import com.naeemdev.githubuser.ui.theme.GihubUserSearchTheme

@Composable
fun EmptyView(
    message: String,
    modifier: Modifier = Modifier,
) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.empty_list))
    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations = LottieConstants.IterateForever
    )
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        LottieAnimation(
            composition = composition,
            progress = { progress },
        )

        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = message,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(16.dp))


    }

}

@Preview(showBackground = true)
@Composable
fun PreviewEmptyView() {
    GihubUserSearchTheme {
        EmptyView(
            message = stringResource(R.string.no_data_found)
        )
    }
}
