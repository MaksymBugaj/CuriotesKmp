package ui.category

import CategoryViewModel
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Divider
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import curioteskmp.composeapp.generated.resources.Res
import curioteskmp.composeapp.generated.resources.bulk_button_text
import curioteskmp.composeapp.generated.resources.confirm_button_text
import curioteskmp.composeapp.generated.resources.create_category
import curioteskmp.composeapp.generated.resources.dialog_description_no_curiotes_combined
import curioteskmp.composeapp.generated.resources.dialog_title_no_curiotes_combined
import curioteskmp.composeapp.generated.resources.dismiss_button_text
import curioteskmp.composeapp.generated.resources.empty_categories
import curioteskmp.composeapp.generated.resources.manual_button_text
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
    onCategoryItemClick: (category: Category) -> Unit,
    onManualAssignClick: () -> Unit,
    onBulkAssignClick: () -> Unit
) {
    val categories by categoryViewModel.categories.collectAsState()
    val curiotesCombined by categoryViewModel.curiotesCombined.collectAsState()


    if (categories.isEmpty()) {
        EmptyCategories(onCreateCategoryClick = onCreateCategoryClick)
    } else {
        CategoriesView(
            categories = categories,
            curiotesCombined = curiotesCombined,
            onCategoryItemClick = onCategoryItemClick,
            onBulkAssignClick = onBulkAssignClick,
            onManualAssignClick = onManualAssignClick,
            )
    }


}

/**
 * if curiotesCombined is empty, show a view that will display smt like:
 * You need to add curiotes to categories, do it "there" or go to the curiote screen and edit each curiote
 * "There" redirects to the screen where user selects a category and can select curiotes, and "go" redirects to the curiote screen
 * [onBulkAssignClick] -> user 'stays' at the screen and can bulk add curiotes to a category
 * [onManualAssignClick] -> user is navigated to the curiote screen
 */
@OptIn(ExperimentalResourceApi::class)
@Composable
fun CategoriesView(
    categories: List<Category>,
    curiotesCombined: List<Pair<Category, List<Curiote>>>,
    onCategoryItemClick: (category: Category) -> Unit,
    onBulkAssignClick: () -> Unit,
    onManualAssignClick: () -> Unit
) {

    if(curiotesCombined.isEmpty()){
        AlertDialogExample(
            dialogTitle = stringResource(Res.string.dialog_title_no_curiotes_combined),
            dialogText = stringResource(Res.string.dialog_description_no_curiotes_combined),
            icon = Icons.Default.Warning,
            dismissButtonText = stringResource(Res.string.dismiss_button_text),
            confirmButtonText = stringResource(Res.string.bulk_button_text),
            neutralButtonText = stringResource(Res.string.manual_button_text),
            onConfirmation = onBulkAssignClick,
            onNeutral = onManualAssignClick,
            onDismiss = {
                //todo hide dialog :)
            }
        )
    } else {
        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = Dimens.gridItemMinSize)
        ) {
            items(curiotesCombined) { item ->
                CategoryItem(item) {

                }
            }
        }
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
fun AlertDialogExample(
    dismissButtonText: String = stringResource(Res.string.dismiss_button_text),
    confirmButtonText: String = stringResource(Res.string.confirm_button_text),
    neutralButtonText: String? = null,
    onDismiss: () -> Unit = {},
    onConfirmation: () -> Unit = {},
    onNeutral: () -> Unit = {},
    dialogTitle: String,
    dialogText: String,
    icon: ImageVector,

    ) {
    AlertDialog(
        icon = {
            Icon(icon, contentDescription = "Example Icon")
        },
        title = {
            TextCustom(text = dialogTitle)
        },
        text = {
            TextCustom(text = dialogText)
        },
        onDismissRequest = {
            onDismiss()
        },
        confirmButton = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                CustomOutlinedButton(
                    onClick = {
                        onConfirmation()
                    },
                    text = confirmButtonText
                )
                CustomOutlinedButton(
                    onClick = {
                        onDismiss()
                    },
                    text = dismissButtonText
                )
                neutralButtonText?.let {neutralText ->
                    CustomOutlinedButton(
                        onClick = {
                            onNeutral()
                        },
                        text = neutralText
                    )
                }


            }
        }
    )
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