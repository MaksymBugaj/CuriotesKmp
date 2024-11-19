package ui.navigation

import CategoryViewModel
import CreateCategoryViewModel
import CreateCurioteViewModel
import CurioteViewModel
import EditCurioteViewModel
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import org.koin.compose.currentKoinScope
import ui.category.CategoriesScreen
import ui.category.CreateCategoryScreen
import ui.curiote.create.CreateCurioteScreen
import ui.curiote.create.TextCustom
import ui.curiote.edit.EditCurioteScreen
import ui.curiote.show.CurioteScreen

@Composable
fun MainView() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BottomNavigatonBar(navController = navController) }
    ) { innerPadding ->
        NavigationHost(navController = navController, modifier = Modifier.padding(innerPadding))
    }
}

@Composable
fun NavigationHost(
    navController: NavHostController,
    modifier: Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = NavItem.Curiotes.screenRoute,
        modifier = modifier
    ) {
        composable(NavItem.CreateCuriote.screenRoute) {
            val viewModel = koinViewModel<CreateCurioteViewModel>()
            CreateCurioteScreen(viewModel = viewModel) {
                navController.navigateUp()
            }
        }
        composable(NavItem.Curiotes.screenRoute) {
            val viewModel = koinViewModel<CurioteViewModel>()
            CurioteScreen(
                viewModel = viewModel,
                onCreateCurioteClick = {
                    navController.navigate(NavItem.CreateCuriote.screenRoute)
                },
                onCurioteClick = { curioteId ->
                    navController.navigate("${NavItem.EditCuriote.screenRoute}/$curioteId")

                }
            )
        }
        composable(route = "${NavItem.EditCuriote.screenRoute}/{curioteId}",
            arguments = listOf(
                navArgument("curioteId") {
                    type = NavType.LongType
                    defaultValue = -1L
                }
            )
        ) { navBackStackEntry ->
            val curioteId = navBackStackEntry.arguments?.getLong("curioteId", -1L)
            val viewModel = koinViewModel<EditCurioteViewModel>()
            curioteId?.let {
                EditCurioteScreen(viewModel = viewModel, curioteId = it, navigateUp = {
                    navController.navigateUp()
                })
            }
        }
        composable(route = NavItem.Explore.screenRoute) {
            //todo ExploreScreen()
        }

        composable(route = NavItem.Categories.screenRoute) {
            val categoryViewModel = koinViewModel<CategoryViewModel>()
            CategoriesScreen(
                categoryViewModel = categoryViewModel,
                onCreateCategoryClick = {
                    navController.navigate(NavItem.CreateCategories.screenRoute)
                },
                onBulkAssignClick = {
                    navController.navigate(NavItem.BulkAssign.screenRoute)
                },
                onCategoryItemClick = {

                },
                onManualAssignClick = {
                    navController.navigateUp()
                }
            )
        }
        composable(route = NavItem.BulkAssign.screenRoute) {

        }
        composable(route = NavItem.CreateCategories.screenRoute) {
            val viewModel = koinViewModel<CreateCategoryViewModel>()
            CreateCategoryScreen(viewModel) {
                navController.navigateUp()
            }
        }
    }
}

@Composable
fun BottomNavigatonBar(navController: NavHostController) {
    val screens = listOf(
        NavItem.Curiotes,
        NavItem.CreateCuriote,
        NavItem.Explore,
        NavItem.Categories
    )

    BottomNavigation(
    ) {
        screens.forEach { screen ->
            BottomNavigationItem(
                icon = {
                    Icon(
                        screen.icon,
                        contentDescription = screen.title,
                        tint = MaterialTheme.colorScheme.primary
                    )
                },
                label = {
                    TextCustom(
                        text = screen.title,
                        modifier = Modifier.wrapContentSize(),
                        fontSize = MaterialTheme.typography.labelMedium.fontSize,
                        maxLines = 1
                    )
                },
                selected = navController.currentDestination?.route == screen.screenRoute,
                onClick = {
                    navController.navigate(screen.screenRoute) {
                        popUpTo(NavItem.Curiotes.screenRoute) {
                            saveState = true
                        }
                        // Utrzymanie stanu
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )

        }
    }
}

@Composable
inline fun <reified T : ViewModel> koinViewModel(): T {
    val scope = currentKoinScope()
    return viewModel {
        scope.get<T>()
    }
}