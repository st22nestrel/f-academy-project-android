package app.futured.academyproject.ui.screens.home

import androidx.compose.foundation.clickable
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
            onEvent<TierSelectedEvent> {
                viewModel.tryAgain()
            }
        }

        Home.Content(
            viewModel,
            viewState.tanks,
            viewState.error,
        )
    }
}

object Home {

    object State{
        var expanded = false // state variable for menu expansion
        val options = listOf("Option 1", "Option 2", "Option 3") // list of options for the menu
        var selectedOption = mutableStateOf(options[0])
    }

    interface Actions {

        fun navigateToDetailScreen(placeId: Int) = Unit

        fun tryAgain() = Unit
    }

    object PreviewActions : Actions

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun Content(
        actions: Actions,
        tanks: PersistentList<Tank>,
        error: Throwable?,
        modifier: Modifier = Modifier,
    ) {
        val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()

        Scaffold(
            modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
            topBar = {
                HomeTopAppBar(scrollBehavior)
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
                        HomeDropDownMenu(innerPadding, Options)
//                        LazyColumn(
//                            horizontalAlignment = Alignment.CenterHorizontally,
//                            contentPadding = innerPadding,
//                            verticalArrangement = Arrangement.spacedBy(Grid.d1),
//                            modifier = Modifier
//                                .fillMaxSize(),
//                        ) {
//                            items(tanks) { tank ->
//                                TankCard(
//                                    tank = tank,
//                                    onClick = actions::navigateToDetailScreen,
//                                )
//                            }
//                        }
                    }
                }
            },
        )
    }

    object Options{
        class Option(
            val name: String,
            val values: List<String>
        )

        //val options = listOf(Option("tier", listOf(1,2,3,4,5,6,7,8,9,10)))
        val options = listOf(Option("tier", listOf("1","2","3","4","5","6","7","8","9","10")))
    }

    @Composable
    @OptIn(ExperimentalMaterial3Api::class)
    private fun HomeDropDownMenu(innerPadding: PaddingValues, options: Options){
        Row (
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(innerPadding),

            ) {
                options.options.forEach { option ->
                    val expanded = remember { mutableStateOf(false) }
                    Column (
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .padding(vertical = Grid.d2, horizontal = Grid.d2),
                    ) {
                        Box(modifier = Modifier.padding(vertical = 8.dp)) {
                            Text(
                                text = option.name, style = MaterialTheme.typography.headlineSmall,
                                modifier = Modifier.clickable(onClick = { expanded.value = !expanded.value })
                            )
                            DropdownMenu(expanded = expanded.value, onDismissRequest = { expanded.value = false }) {
                                option.values.forEach { e ->
                                    DropdownMenuItem(
                                        text = {"fuck this"},
                                        onClick = {
                                            //TODO
                                            //selectedCategory.value = e.toString()
                                            expanded.value = false
                                        },
                                    )
                                }
                            }
                        }
                    }
                }
        }
    }


//    data class Grocery(val name: String, val category: String, val color: String)
//
//    val groceries = listOf(
//        Grocery("Apple", "Fruit", "Red"),
//        Grocery("Banana", "Fruit", "Yellow"),
//        Grocery("Broccoli", "Vegetable", "Green"),
//        Grocery("Carrot", "Vegetable", "Orange")
//    )
//    @Composable
//    @OptIn(ExperimentalMaterial3Api::class)
//    private fun HomeDropDownMenu(innerPadding: PaddingValues){
//        val selectedCategory = remember { mutableStateOf<String?>(null) }
//        val selectedColor = remember { mutableStateOf<String?>(null) }
//        val expandedCategory = remember { mutableStateOf(false) }
//        val expandedColor = remember { mutableStateOf(false) }
//
//        Row (
//            verticalAlignment = Alignment.CenterVertically,
//            modifier = Modifier
//             .padding(innerPadding),
//
//        ) {
//            Column (
//                verticalArrangement = Arrangement.Center,
//                modifier = Modifier
//                    .padding(vertical = Grid.d2, horizontal = Grid.d2),
//            ) {
//                Text("Category", style = MaterialTheme.typography.headlineSmall)
//                Box(modifier = Modifier.padding(vertical = 8.dp)) {
//                    Text(
//                        text = selectedCategory.value ?: "Select a category",
//                        modifier = Modifier.clickable(onClick = { expandedCategory.value = !expandedCategory.value })
//                    )
//                    DropdownMenu(expanded = expandedCategory.value, onDismissRequest = { expandedCategory.value = false }) {
//                        groceries.map { it.category }.distinct().forEach { category ->
//                            DropdownMenuItem(
//                                text = {category},
//                                onClick = {
//                                    selectedCategory.value = category
//                                    expandedCategory.value = false
//                                },
//                            )
//                        }
//                    }
//                }
//            }
//
//            Column (
//                verticalArrangement = Arrangement.Center,
//            ) {
//                Text("Color", style = MaterialTheme.typography.headlineSmall)
//                Box(modifier = Modifier.padding(vertical = 8.dp)) {
//                    Text(
//                        text = selectedColor.value ?: "Select a color",
//                        modifier = Modifier.clickable(onClick = { expandedColor.value = !expandedColor.value })
//                    )
//                    DropdownMenu(expanded = expandedColor.value, onDismissRequest = { expandedColor.value = false }) {
//                        groceries.map { it.color }.distinct().forEach { color ->
//                            DropdownMenuItem(
//                                text = {color},
//                                onClick = {
//                                    selectedColor.value = color
//                                    expandedColor.value = false
//                                })
//                        }
//                    }
//                }
//            }
//        }
//    }


    @Composable
    @OptIn(ExperimentalMaterial3Api::class)
    private fun HomeTopAppBar(scrollBehavior: TopAppBarScrollBehavior) {
        TopAppBar(
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
                IconButton(onClick = { State.expanded = !State.expanded }) { // toggle button for the menu
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
                }
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
        )
    }
}

