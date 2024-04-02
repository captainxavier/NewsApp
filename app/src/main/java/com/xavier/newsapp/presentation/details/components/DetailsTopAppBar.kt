package com.xavier.newsapp.presentation.details.components

import android.content.res.Configuration
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.xavier.newsapp.R
import com.xavier.newsapp.presentation.components.NewsTopAppBar
import com.xavier.newsapp.ui.theme.NewsAppTheme

@Composable
fun DetailsTopAppBar(
    onBrowsingClick: () -> Unit,
    onShareClick: () -> Unit,
    onBookmarkClick: () -> Unit,
    onBackClick: () -> Unit,
    isBookMarked: Boolean
) {
    NewsTopAppBar(
        title = "",
        actions = {
            IconButton(onClick = { onBookmarkClick() }) {
                if (isBookMarked) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_bookmark_added),
                        contentDescription = "Bookmark Icon",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
                else {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_bookmark),
                        contentDescription = "Bookmark Icon",
                        tint = MaterialTheme.colorScheme.onBackground
                    )
                }
            }
            IconButton(onClick = { onShareClick() }) {
                Icon(
                    imageVector = Icons.Filled.Share,
                    contentDescription = "Share Icon",
                    tint = MaterialTheme.colorScheme.onBackground
                )
            }
            IconButton(onClick = { onBrowsingClick() }) {
                Icon(
                    painterResource(id = R.drawable.ic_network),
                    contentDescription = "Open Browser Icon",
                    tint = MaterialTheme.colorScheme.onBackground
                )
            }
        },
        navigationIcon = {
            IconButton(onClick = { onBackClick() }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_arrow_back),
                    contentDescription = "Back Icon",
                    tint = MaterialTheme.colorScheme.onBackground
                )
            }
        }

    )

}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun DetailsTopAppBarPreview() {

    NewsAppTheme {
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
            DetailsTopAppBar(
                onBrowsingClick = { /*TODO*/ },
                onShareClick = { /*TODO*/ },
                onBookmarkClick = { /*TODO*/ },
                onBackClick = {},
                isBookMarked = true
            )
        }
    }

}