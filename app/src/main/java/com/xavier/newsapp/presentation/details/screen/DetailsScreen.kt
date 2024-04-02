package com.xavier.newsapp.presentation.details.screen

import android.content.Intent
import android.content.res.Configuration
import android.net.Uri
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.xavier.newsapp.data.local.entities.toEntity
import com.xavier.newsapp.domain.models.news.Article
import com.xavier.newsapp.domain.models.news.Source
import com.xavier.newsapp.presentation.components.Dimens
import com.xavier.newsapp.presentation.details.components.DetailsTopAppBar
import com.xavier.newsapp.presentation.details.events.DetailsEvent
import com.xavier.newsapp.ui.theme.NewsAppTheme

@Composable
fun DetailsScreen(
    article: Article,
    onEvent: (DetailsEvent) -> Unit,
    navigateUp: () -> Unit,
    isBookmarked: Boolean

) {
    val context = LocalContext.current


    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            DetailsTopAppBar(
                onBrowsingClick = {
                    Intent(Intent.ACTION_VIEW).also {
                        it.data = Uri.parse(article.url)
                        if (it.resolveActivity(context.packageManager) != null) {
                            context.startActivity(it)
                        }
                    }
                },
                onShareClick = {
                    Intent(Intent.ACTION_SEND).also {
                        it.putExtra(Intent.EXTRA_TEXT, article.url)
                        it.type = "text/plain"
                        if (it.resolveActivity(context.packageManager) != null) {
                            context.startActivity(it)
                        }
                    }
                },
                onBookmarkClick = { onEvent(DetailsEvent.UpsertDeleteArticle(article.toEntity())) },
                onBackClick = {
                    navigateUp()
                },
                isBookMarked = isBookmarked
            )
        }
    ) {
        DetailsScreenContent(
            modifier = Modifier.padding(it),
            article = article
        )

    }

}

@Composable
fun DetailsScreenContent(modifier: Modifier, article: Article) {

    val context = LocalContext.current

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(
            start = Dimens.MediumPadding1,
            end = Dimens.MediumPadding1,
            top = Dimens.MediumPadding1
        )
    ) {
        item {
            AsyncImage(
                model = ImageRequest.Builder(context).data(article.urlToImage).build(),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(Dimens.ArticleImageHeight)
                    .clip(MaterialTheme.shapes.medium),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(Dimens.MediumPadding1))
            Text(
                text = article.title,
                style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.SemiBold),
                color = MaterialTheme.colorScheme.onBackground
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = article.content,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
    }
}


@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun DetailsScreenPreview() {

    val article = Article(
        author = "",
        title = "Coinbase says Apple blocked its last app release on NFTs in Wallet ... - CryptoSaurus",
        description = "Coinbase says Apple blocked its last app release on NFTs in Wallet ... - CryptoSaurus",
        content = "We use cookies and data to Deliver and maintain Google services Track outages and protect against spam, fraud, and abuse Measure audience engagement and site statistics to unde… [+1131 chars]",
        publishedAt = "2023-06-16T22:24:33Z",
        source = Source(
            id = "", name = "bbc"
        ),
        url = "https://consent.google.com/ml?continue=https://news.google.com/rss/articles/CBMiaWh0dHBzOi8vY3J5cHRvc2F1cnVzLnRlY2gvY29pbmJhc2Utc2F5cy1hcHBsZS1ibG9ja2VkLWl0cy1sYXN0LWFwcC1yZWxlYXNlLW9uLW5mdHMtaW4td2FsbGV0LXJldXRlcnMtY29tL9IBAA?oc%3D5&gl=FR&hl=en-US&cm=2&pc=n&src=1",
        urlToImage = "https://media.wired.com/photos/6495d5e893ba5cd8bbdc95af/191:100/w_1280,c_limit/The-EU-Rules-Phone-Batteries-Must-Be-Replaceable-Gear-2BE6PRN.jpg"
    )


    NewsAppTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            DetailsScreen(
                article = article,
                onEvent = {},
                navigateUp = {},
                isBookmarked = true
            )
        }
    }


}
