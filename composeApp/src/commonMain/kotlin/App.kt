
import androidx.compose.material.Surface
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.benasher44.uuid.Uuid
import org.koin.compose.KoinContext
import org.koin.compose.currentKoinScope
import ui.curiote.create.CreateCurioteScreen
import ui.curiote.edit.EditCurioteScreen
import ui.curiote.show.CurioteScreen
import ui.theme.AppTheme

@Composable
fun App() {
    AppTheme {
        KoinContext {
            Surface(
                color = MaterialTheme.colorScheme.surface
            ) {
                NavigationHost()
            }
        }
    }
}

@Composable
fun NavigationHost() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = NavItem.Curiotes.screenRoute
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
        ){ navBackStackEntry ->
            val curioteId = navBackStackEntry.arguments?.getLong("curioteId", -1L)
            println("CurioteId to pass in navigation::: $curioteId")
//            println("NavBackStack: ${navController.currentBackStack.value.size}")
//            println("NavBackStack: ${navController.currentBackStack.value}")
            val viewModel = koinViewModel<EditCurioteViewModel>()
            curioteId?.let {
                EditCurioteScreen(viewModel = viewModel, curioteId = it, navigateUp =  {
                    navController.navigateUp()
                }
                )
            }
        }
    }
}

sealed class NavItem(val title: String, val screenRoute: String) {
    data object Curiotes : NavItem(title = "Curiotes", screenRoute = NavRoute.curiotes)
    data object CreateCuriote : NavItem(title = "Create", screenRoute = NavRoute.createCuriote)
    data object EditCuriote : NavItem(title = "Edit", screenRoute = NavRoute.editCuriote)
}

object NavRoute {
    const val curiotes = "CurioteScreen"
    const val createCuriote = "CreateCurioteScreen"
    const val editCuriote = "EditCurioteScreen"
}
@Composable
inline fun<reified T: ViewModel> koinViewModel(): T {
    val scope = currentKoinScope()
    return viewModel {
        scope.get<T>()
    }
}