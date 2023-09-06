package app.futured.academyproject.ui.screens.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.hilt.navigation.compose.hiltViewModel
import app.futured.academyproject.data.model.local.Place
import app.futured.academyproject.data.model.local.Tank
import app.futured.academyproject.navigation.NavigationDestinations
import app.futured.academyproject.tools.arch.EventsEffect
import app.futured.academyproject.tools.arch.onEvent
import app.futured.academyproject.tools.compose.ScreenPreviews
import app.futured.academyproject.tools.preview.TanksProvider
import app.futured.academyproject.ui.components.Showcase
import app.futured.academyproject.ui.theme.Grid
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import kotlinx.collections.immutable.PersistentList

@Composable
fun DetailScreen(
    navigation: NavigationDestinations,
    viewModel: DetailViewModel = hiltViewModel(),
) {
    with(viewModel) {
        EventsEffect {
            onEvent<NavigateBackEvent> {
                navigation.popBackStack()
            }
        }

        Detail.Content(
            this,
            viewState.tank,
        )
    }
}

object Detail {

    interface Actions {
        fun navigateBack()
        fun onFavorite()
    }

    object PreviewActions : Actions {
        override fun navigateBack() {}
        override fun onFavorite() {}
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun Content(
        actions: Actions,
        tank: Tank?,
        modifier: Modifier = Modifier,
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = "Info about ${tank?.shortName}") },
                    actions = {
                            val (iconRes, iconColor) = if (tank?.isFavourite == true) {
                                Icons.Filled.Favorite to MaterialTheme.colorScheme.error
                            } else {
                                Icons.Filled.FavoriteBorder to MaterialTheme.colorScheme.onSurface
                            }
                        // TODO Step 9 - Implement UI - add Icon button with right icon and tint color
                        // When place is favorite use Icons.Filled.Favorite icon and MaterialTheme.colorScheme.error color
                        // When place is not favorite use Icons.Filled.FavoriteBorder icon and MaterialTheme.colorScheme.onSurface color
                        // Use IconButton component
                        // Use on click listener onFavorite (Hint: onClick = actions::onFavorite)
                            IconButton(onClick = actions::onFavorite) {
                                Icon(
                                    imageVector = iconRes,
                                    tint = iconColor,
                                    contentDescription = null,
                                    )
                            }
                    },
                    navigationIcon = {
                        IconButton(onClick = { actions.navigateBack() }) {
                            Icon(Icons.Filled.ArrowBack, contentDescription = null)
                        }
                    },
                )
            },
            modifier = modifier,
        ) { contentPadding ->
            tank?.let {
//                Card(
//                    colors = CardDefaults.cardColors(),
//                    modifier = Modifier
//                        .size(Grid.d15),
//                ){
//
//                }
//                Row {
//
//                    Text(text = tank.name)
//                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .padding(contentPadding)
                        .fillMaxHeight(),
                ) {
                    Row (
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .padding(vertical = Grid.d2, horizontal = Grid.d2)
                    ) {
                        Card(
                            modifier = Modifier
                                .size(Grid.d30),
                        ) {
                            Image(
                                painter = rememberAsyncImagePainter(
                                    ImageRequest.Builder(LocalContext.current)
                                        .data(tank.bigIcon.replace("http", "https"))
                                        .build(),
                                ),
                                contentDescription = tank.bigIcon,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier.aspectRatio(1f),
                            )
                        }

                        Column(
                            verticalArrangement = Arrangement.Center,
                            modifier = Modifier
                                .weight(1f)
                                .padding(vertical = Grid.d2, horizontal = Grid.d4),
                        ) {
                            Text(
                                text = "Name:",
                                style = MaterialTheme.typography.titleLarge,
                                color = MaterialTheme.colorScheme.onSurface,
                                maxLines = 2,
                                overflow = TextOverflow.Ellipsis,
                            )
                            Text(
                                text = "Type:",
                            )
                            Text(
                                text = "Tier:",
                            )
                            Text(
                                text = "Nation:",
                            )
                        }

                        Column(
                            verticalArrangement = Arrangement.Center,
                            modifier = Modifier
                                .weight(1f)
                                .padding(vertical = Grid.d2, horizontal = Grid.d4),
                        ) {
                            Text(
                                text = tank.name,
                                style = MaterialTheme.typography.titleLarge,
                                color = MaterialTheme.colorScheme.onSurface,
                                maxLines = 2,
                                overflow = TextOverflow.Ellipsis,
                            )
                            Text(
                                text = tank.tankType,
                            )
                            Text(
                                text = tank.tier.toString(),
                            )
                            Text(
                                text = tank.nation,
                            )
                        }
                    }

                    Text(
                        text = tank.description,
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.padding(vertical = Grid.d4, horizontal = Grid.d2)
                    )

                }
            }
        }
    }
}

@ScreenPreviews
@Composable
private fun DetailContentPreview(@PreviewParameter(TanksProvider::class) tanks: PersistentList<Tank>) {
    Showcase {
        Detail.Content(
            Detail.PreviewActions,
            tank = tanks.first(),
        )
    }
}
