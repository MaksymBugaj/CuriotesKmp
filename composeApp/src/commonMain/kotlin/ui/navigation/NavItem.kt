package ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector

sealed class NavItem(val title: String, val screenRoute: String, val icon: ImageVector) {
    data object Curiotes : NavItem(title = NavItemName.CURIOTE_ITEM_NAME, screenRoute = NavRoute.CURIOTES, icon = Icons.Default.Home)
    data object CreateCuriote : NavItem(title = NavItemName.CREATE_CURIOTE_ITEM_NAME, screenRoute = NavRoute.CREATE_CURIOTE, icon = Icons.Default.Add)
    data object EditCuriote : NavItem(title = NavItemName.EDIT_CURIOTE_ITEM_NAME, screenRoute = NavRoute.EDIT_CURIOTE, icon = Icons.Default.Edit)
    data object Explore : NavItem(title = NavItemName.EXPLORE_ITEM_NAME, screenRoute = NavRoute.EXPLORE, icon = Icons.Default.Search)
    data object Categories : NavItem(title = NavItemName.CATEGORIES_ITEM_NAME, screenRoute = NavRoute.CATEGORIES, icon = Icons.Default.Place)
    data object CreateCategories : NavItem(title = NavItemName.CREATE_CATEGORIES_ITEM_NAME, screenRoute = NavRoute.CREATE_CATEGORIES, icon = Icons.Default.Place)
}

