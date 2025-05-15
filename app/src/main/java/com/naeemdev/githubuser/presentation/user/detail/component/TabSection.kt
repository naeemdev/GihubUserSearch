package com.naeemdev.githubuser.presentation.user.detail.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.naeemdev.githubuser.R
import com.naeemdev.githubuser.ui.theme.CardBackground
import com.naeemdev.githubuser.ui.theme.GihubUserSearchTheme
import com.naeemdev.githubuser.ui.theme.GreenHighlight
import com.naeemdev.githubuser.ui.theme.TextColorSecondary


@Composable
fun TabSection(
    tabs: List<Int>,
    modifier: Modifier = Modifier,
    selectedTab: Int, onTabSelected: (Int) -> Unit
) {

    Column(modifier = modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            tabs.forEachIndexed { index, tabName ->
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    TextButton(
                        onClick = { onTabSelected(index) },
                        colors = ButtonDefaults.textButtonColors(
                            contentColor = if (selectedTab == index) GreenHighlight else TextColorSecondary
                        )
                    ) {
                        Text(
                            text = stringResource(id = tabName),
                            fontWeight = if (selectedTab == index) FontWeight.Bold else FontWeight.Normal
                        )
                    }
                    if (selectedTab == index) {
                        Box(
                            modifier = Modifier
                                .width(50.dp)
                                .height(2.dp)
                                .background(GreenHighlight, shape = RoundedCornerShape(1.dp))
                        )
                    }
                }
            }
        }
        HorizontalDivider(
            color = CardBackground,
            thickness = 1.dp,
            modifier = Modifier.padding(top = 0.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewTabSection() {
    var selectedTab by remember { mutableIntStateOf(0) }

    GihubUserSearchTheme {
        TabSection(
            tabs = listOf(
                R.string.repositories,
                R.string.gists,
                R.string.followers,
                R.string.following
            ),
            selectedTab = selectedTab,
            onTabSelected = { newTab -> selectedTab = newTab }
        )
    }
}