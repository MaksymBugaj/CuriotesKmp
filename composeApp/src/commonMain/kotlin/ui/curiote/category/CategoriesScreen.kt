package ui.curiote.category

import CategoryViewModel
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Divider
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import curioteskmp.composeapp.generated.resources.Res
import curioteskmp.composeapp.generated.resources.create_category
import curioteskmp.composeapp.generated.resources.empty_categories
import domain.model.category.Category
import domain.model.curiote.Curiote
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringResource
import ui.curiote.create.CustomOutlinedButton
import ui.curiote.create.TextCustom
import ui.theme.Dimens

/**
 * Present Categories.
 * In case of empty Categories, display Card with a text and a button.
 * If there are Categories, but empty CuriotesCombined, display a pop up, saying that you need to asign a category to a curiote.Button Stay and move
 */
@Composable
fun CategoriesScreen(
    categoryViewModel: CategoryViewModel,
    onCreateCategoryClick: () -> Unit,
    onAssignCategoryClick: () -> Unit,
    onCategoryItemClick: (category: Category) -> Unit
) {
    val categories by categoryViewModel.categories.collectAsState()
    val curiotesCombined by categoryViewModel.curiotesCombined.collectAsState()


    if (categories.isEmpty()) {
        EmptyCategories(onCreateCategoryClick = onCreateCategoryClick)
    } else {
        CategoriesView(
            categories = categories,
            curiotesCombined = curiotesCombined,
            onCategoryItemClick = onCategoryItemClick
            )
    }


}

/**
 * if curiotesCombined is empty, show a view that will display smt like:
 * You need to add curiotes to categories, do it "there" or go to the curiote screen and edit each curiote
 * "There" redirects to the screen where user selects a category and can select curiotes, and "go" redirects to the curiote screen
 */
@Composable
fun CategoriesView(
    categories: List<Category>,
    curiotesCombined: List<Pair<Category, List<Curiote>>>,
    onCategoryItemClick: (category: Category) -> Unit,
) {

    if(curiotesCombined.isEmpty())

    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = Dimens.gridItemMinSize)
    ) {
        items(curiotesCombined) { item ->
            CategoryItem(item) {

            }
        }
    }
}

@Composable
fun EmptyCategoriesScreen(modifier: Modifier = Modifier) {

}

@Composable
fun CategoryItem(
    categoryCombined: Pair<Category, List<Curiote>>,
    onCategoryItemClick: (category: Category) -> Unit,

    ) {
    Card(
        modifier = Modifier.padding(Dimens.paddingDefault),
        border = BorderStroke(Dimens.dividerThickness,color = MaterialTheme.colorScheme.onPrimaryContainer)
    ) {
        Column (modifier = Modifier.padding(Dimens.paddingDefault)){
            TextCustom(text = "Category: ${categoryCombined.first.name}", fontWeight = FontWeight.Bold)
            Divider(color = MaterialTheme.colorScheme.primary, thickness = Dimens.dividerThickness)
            Spacer(modifier = Modifier.height(Dimens.paddingLarge))
            TextCustom(text = "Curiotes: ${categoryCombined.second.size}")
        }
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
fun EmptyCategories(
    onCreateCategoryClick: () -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize().padding(Dimens.paddingDefault),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(modifier = Modifier.wrapContentSize().padding(Dimens.paddingDefault)) {
            Column(
                modifier = Modifier.padding(Dimens.paddingDefault),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TextCustom(
                    text = stringResource(Res.string.empty_categories)
                )
                CustomOutlinedButton(
                    text = stringResource(Res.string.create_category),
                    onClick = onCreateCategoryClick
                )
            }
        }
    }

}