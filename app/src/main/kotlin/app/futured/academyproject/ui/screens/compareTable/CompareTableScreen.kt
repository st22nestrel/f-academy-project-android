package app.futured.academyproject.ui.screens.compareTable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import app.futured.academyproject.data.model.local.Tank
import app.futured.academyproject.data.model.local.TankComparable
import app.futured.academyproject.navigation.NavigationDestinations
import app.futured.academyproject.tools.arch.EventsEffect
import app.futured.academyproject.tools.arch.onEvent
import app.futured.academyproject.ui.components.TankCardVertical
import app.futured.academyproject.ui.screens.home.NavigateToDetailEvent
import app.futured.academyproject.ui.theme.Grid
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.PersistentMap

@Composable
fun CompareTableScreeen(
    navigation: NavigationDestinations,
    viewModel: CompareTableViewModel = hiltViewModel(),
){
    with(viewModel){
        EventsEffect{
            onEvent<NavigateToDetailEvent> {
                navigation.navigateToDetailScreen(tankId = it.tankId)
            }
            onEvent<NavigateBackEvent> {
                navigation.popBackStack()
            }
        }
        CompareTable.Content(
            viewModel,
            viewState.tanks,
            viewState.tanksComparable
        )
    }
}

object CompareTable {

    interface Actions {
        fun navigateBack() = Unit
        fun navigateToDetailScreen(tankId: Int) = Unit
    }

    object PreviewActions : Actions

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun Content(
        actions: Actions,
        //tanks: PersistentList<Pair<Tank,TankComparable>>,
        tanks: PersistentList<Tank>,
        tanksComparable: PersistentMap<Int, TankComparable>,
        //tanksComparable: PersistentList<TankComparable>,
        modifier: Modifier = Modifier,
    ){
        val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()

        Scaffold(
            modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
            topBar = {
                CompareTableTopAppBar(scrollBehavior = scrollBehavior, navigateBack = actions::navigateBack)
            },
            content = {innerPadding ->
                when {
                    tanks.isEmpty() or tanksComparable.isEmpty() -> {
                        Loading()
                    }
                    tanks.isNotEmpty() -> {
                        LazyRow(
                            horizontalArrangement = Arrangement.spacedBy(Grid.d1),
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .padding(innerPadding)
                                .fillMaxSize(),
                        ) {
                            items(tanks) { tank ->
                                tanksComparable[tank.id]?.let {
                                    TankCardVertical(
                                        tank = tank,
                                        tankStatistics = it,
                                        onIconClicked = actions::navigateToDetailScreen
                                    )
                                }
                            }
                        }
                    }
                }
            }
        )
    }
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
@OptIn(ExperimentalMaterial3Api::class)
private fun CompareTableTopAppBar(scrollBehavior: TopAppBarScrollBehavior, navigateBack: () -> Unit) {
    TopAppBar(
        title = {
            Text(
                text = "Porovnání tanku",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        },
        navigationIcon = {
            IconButton(onClick = { navigateBack() }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBackIosNew,
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
        }
    )
}
