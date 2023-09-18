package app.futured.academyproject.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import app.futured.academyproject.R
import app.futured.academyproject.data.model.local.Tank
import app.futured.academyproject.navigation.NavigationDestinations
import app.futured.academyproject.tools.arch.EventsEffect
import app.futured.academyproject.tools.arch.onEvent
import app.futured.academyproject.tools.compose.ScreenPreviews
import app.futured.academyproject.tools.preview.TanksProvider
import app.futured.academyproject.ui.components.Showcase
import app.futured.academyproject.ui.theme.Grid
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckBoxOutlineBlank
import androidx.compose.material.icons.outlined.CheckBoxOutlineBlank
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import app.futured.academyproject.util.filters.Filters
import app.futured.academyproject.ui.components.TankCard

@Composable
fun HomeScreen(
    navigation: NavigationDestinations,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    with(viewModel) {
        EventsEffect {
            onEvent<NavigateToDetailEvent> {
                navigation.navigateToDetailScreen(tankId = it.tankId)
            }
            onEvent<NavigateToCompareTableEvent> {
                navigation.navigateToCompareTableScreen()
            }
            onEvent<FilterSelectedEvent> {
                viewModel.filterTanks()
            }
        }

        Home.Content(
            viewModel,
            viewState.tanks,
            viewState.error,
            filters = viewState.filters
        )
    }
}

object Home {

    interface Actions {

        fun navigateToDetailScreen(tankId: Int) = Unit
        fun navigateToCompareTableScreen() = Unit

        fun tryAgain() = Unit

        fun filterTanks() = Unit

        fun onSelected(tankId: Int) = Unit
    }

    object PreviewActions : Actions

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun Content(
        actions: Actions,
        tanks: PersistentList<Tank>,
        error: Throwable?,
        filters: Filters,
        modifier: Modifier = Modifier,

        ) {
        val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()

        Scaffold(
            modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
            topBar = {
                HomeTopAppBar(scrollBehavior, actions)
            },
            bottomBar = {

            },
            content = { innerPadding ->
                when {
                    error != null -> {
                        Error(onTryAgain = actions::tryAgain)
                    }
                    tanks.isEmpty() -> {
                        Loading()
                    }
                    tanks.isNotEmpty() -> {
                        HomeDropDownMenuFilters(innerPadding, filters, actions)
                        LazyColumn(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(Grid.d1),
                            modifier = Modifier
                                .padding(innerPadding)
                                .padding(top = 80.dp)
                                .fillMaxSize(),
                        ) {
                            items(tanks) { tank ->
                                TankCard(
                                    tank = tank,
                                    onClick = actions::navigateToDetailScreen,
                                    onSelected = actions::onSelected
                                )
                            }
                        }
                    }
                }
            },
        )
    }

    @Composable
    fun HomeDropDownMenuFilters(innerPadding: PaddingValues, filters: Filters, actions: Actions) {


        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(innerPadding)
        ) { filters.iterator().forEach { filter ->
                var expanded by remember { mutableStateOf(false) }
                Button(
                    onClick = { expanded = true },
                    modifier = Modifier
                        .padding(vertical = Grid.d2, horizontal = Grid.d2),
                    ) {
                    Text(
                        text = filter.description, style = MaterialTheme.typography.headlineSmall,
                    )
                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false },
//                        modifier = Modifier
//                            .padding(top = Grid.d2),
                    ) {
                        filter.values.forEach { e ->
                            DropdownMenuItem(
                                text = { Text(e.toString()) },
                                onClick = {
                                    //expanded = false
                                    if (!filter.selectedValues.contains(e)) {
                                        filter.selectedValues.add(e)
                                        //filter.selectedValues = filter.selectedValues.sorted()
                                    }
                                    else {
                                        filter.selectedValues.remove(e)
                                    }
                                    actions.filterTanks()
                                },
                                leadingIcon = {
                                    Icon(
                                        imageVector = (
                                            if (filter.selectedValues.contains(e)) Icons.Filled.Check
                                            else Icons.Outlined.CheckBoxOutlineBlank
                                            ),
                                        contentDescription = null
                                    )
                                })
                        }
                    }
                }

            }
        }
    }


    @Composable
    @OptIn(ExperimentalMaterial3Api::class)
    private fun HomeTopAppBar(scrollBehavior: TopAppBarScrollBehavior, actions: Actions) {
        MediumTopAppBar(
            title = {
                Text(
                    stringResource(R.string.app_map_name),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            },
            navigationIcon = {
                IconButton(onClick = { /* doSomething() */ }) {
                    Icon(
                        imageVector = Icons.Filled.Menu,
                        contentDescription = null,
                    )
                }
            },
            colors = TopAppBarDefaults.largeTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.background,
                scrolledContainerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(Grid.d1),
            ),
            scrollBehavior = scrollBehavior,
            actions = {
                Button(
                    onClick = { actions.navigateToCompareTableScreen() },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.secondaryContainer,
                        contentColor = MaterialTheme.colorScheme.secondary,
//                        disabledContainerColor = MaterialTheme.colorScheme.secondaryContainer,
//                        disabledContentColor = MaterialTheme.colorScheme.secondary
                    )
                    //colors = MaterialTheme.colorScheme.secondary
                ) {
                    Text(
                        text = "Compare selected tanks", style = MaterialTheme.typography.headlineSmall,
                    )
                }

                /*IconButton(onClick = { State.expanded = !State.expanded }) { // toggle button for the menu
                    Icon(Icons.Filled.MoreVert, "More") // icon for the menu
                }
                DropdownMenu( // menu component
                    expanded = State.expanded, // menu expansion state
                    onDismissRequest = { State.expanded = false } // close the menu when dismissed
                ) {
                    State.options.forEach { option -> // iterate over each option
                        DropdownMenuItem(
                            text = { Text(text = option) },
                            onClick = { // menu item component
                                State.selectedOption.value = option // update the selected option
                                State.expanded = false // close the menu
                            },
                        )
                    }
                }*/
            }
        )
    }

    @Composable
    private fun Loading() {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            contentAlignment = Alignment.Center,
        ) {
            CircularProgressIndicator()
        }
    }

    @Composable
    private fun Error(
        onTryAgain: () -> Unit,
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            contentAlignment = Alignment.Center,
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(Grid.d1),
            ) {
                Text(
                    text = "Yups, Error Happened!",
                    style = MaterialTheme.typography.titleSmall,
                    textAlign = TextAlign.Center,
                )
                Text(
                    text = "Not our proudest moment. Can you try it again?",
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Center,
                )
                Button(onClick = onTryAgain) {
                    Text(
                        text = "Try again",
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Center,
                    )
                }
            }
        }
    }
}

@ScreenPreviews
@Composable
private fun HomeContentPreview(@PreviewParameter(TanksProvider::class) tanks: PersistentList<Tank>) {
    Showcase {
        Home.Content(
            Home.PreviewActions,
            tanks,
            error = null,
            filters = Filters(),
        )
    }
}

//@ScreenPreviews
@Composable
private fun HomeContentWithErrorPreview() {
    Showcase {
        Home.Content(
            Home.PreviewActions,
            tanks = persistentListOf(),
            error = IllegalStateException("Test"),
            filters = Filters(),
        )
    }
}

//@ScreenPreviews
@Composable
private fun HomeContentWithLoadingPreview() {
    Showcase {
        Home.Content(
            Home.PreviewActions,
            tanks = persistentListOf(),
            error = null,
            filters = Filters(),
        )
    }
}

