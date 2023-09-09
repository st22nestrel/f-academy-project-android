package app.futured.academyproject.navigation

import androidx.navigation.NavController

interface NavigationDestinations {
    fun popBackStack()
    fun navigateToDetailScreen(tankId: Int)
    fun navigateToCompareTableScreen()
}

/**
 * Class that triggers navigation actions on provided [navController].
 */
class NavigationDestinationsImpl(private val navController: NavController) : NavigationDestinations {

    override fun popBackStack() {
        navController.popBackStack()
    }

    override fun navigateToDetailScreen(tankId: Int) =
        navController.navigate(Destination.Detail.buildRoute(tankId))

    override fun navigateToCompareTableScreen() {
        navController.navigate(Destination.CompareTable.route)
    }
}
