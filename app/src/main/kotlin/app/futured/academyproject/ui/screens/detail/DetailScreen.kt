package app.futured.academyproject.ui.screens.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
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
import androidx.hilt.navigation.compose.hiltViewModel
import app.futured.academyproject.data.model.local.Place
import app.futured.academyproject.navigation.NavigationDestinations
import app.futured.academyproject.tools.arch.EventsEffect
import app.futured.academyproject.tools.arch.onEvent
import app.futured.academyproject.tools.compose.ScreenPreviews
import app.futured.academyproject.ui.components.Showcase

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
            viewState.place,
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
        place: Place?,
        modifier: Modifier = Modifier,
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = "DetailScreen") },
                    actions = {
                            val (iconRes, iconColor) = if (place?.isFavourite == true) {
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
            place?.let {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .padding(contentPadding)
                        .fillMaxSize(),
                ) {
                    Text(text = place.name)
                }
            }
        }
    }
}

@ScreenPreviews
@Composable
fun DetailContentPreview() {
    Showcase {
        Detail.Content(
            Detail.PreviewActions,
            place = null,
        )
    }
}
