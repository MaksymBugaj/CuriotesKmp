package ui.category

import CategoryViewModel
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Divider
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.withFrameNanos
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import curioteskmp.composeapp.generated.resources.Res
import curioteskmp.composeapp.generated.resources.confirm_button_text
import curioteskmp.composeapp.generated.resources.create_category
import curioteskmp.composeapp.generated.resources.curiotes
import curioteskmp.composeapp.generated.resources.dialog_description_no_curiotes_combined
import curioteskmp.composeapp.generated.resources.dialog_title_no_curiotes_combined
import curioteskmp.composeapp.generated.resources.dismiss_button_text
import curioteskmp.composeapp.generated.resources.empty_categories
import curioteskmp.composeapp.generated.resources.manual_button_text
import domain.model.category.Category
import domain.model.curiote.Curiote
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
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
) {
    val uiState by categoryViewModel.uiState.collectAsState()

    HandleIUState(
        uiState,
        categoryViewModel = categoryViewModel,
        onCreateCategoryClick = onCreateCategoryClick,
        onCategoryItemClick = onCategoryItemClick,
        onManualAssignClick = onManualAssignClick,

    )

//    if (categories.isEmpty()) {
//        EmptyCategories(onCreateCategoryClick = onCreateCategoryClick)
//    } else {
//        CategoriesView(
//            categories = categories,
//            curiotesCombined = curiotesCombined,
//            onCategoryItemClick = onCategoryItemClick,
//            onBulkAssignClick = onBulkAssignClick,
//            onManualAssignClick = onManualAssignClick,
//            )
//    }


}

@Composable
fun HandleIUState(
    uiState: CategoriesViewState,
    categoryViewModel: CategoryViewModel,
                  onCreateCategoryClick: () -> Unit,
                  onCategoryItemClick: (category: Category) -> Unit,
                  onManualAssignClick: () -> Unit,
) {
    when(uiState){
        is CategoriesViewState.CombinedCategoriesState -> {
            CombinedCategoriesView()
        }
        CategoriesViewState.EmptyCategoriesState -> {
            EmptyCategories(onCreateCategoryClick = onCreateCategoryClick)
        }
        is CategoriesViewState.EmptyCombinedCategoriesState -> {
            CategoriesWithoutCuriotesView(
                categories = uiState.categories,
                onCategoryItemClick = onCategoryItemClick,
                onManualAssignClick = onManualAssignClick,
                onCreateCategoryClick = onCreateCategoryClick
            )
        }
        CategoriesViewState.Loading -> {
            LoadingView()
        }
    }
}

@Composable
fun CombinedCategoriesView(
    
) {
    
}



@Composable
fun CategoriesWithoutCuriotesView(
    categories: List<Category>,
    onCategoryItemClick: (category: Category) -> Unit,
    onManualAssignClick: () -> Unit,
    onCreateCategoryClick: () -> Unit
) {
    val showDialog = remember {
        mutableStateOf(true)
    }

    if(showDialog.value) {
        EmptyCombinedCategoriesAlertDialog(
            onManualAssignClick = onManualAssignClick,
            dismiss = {
                showDialog.value = false
            }
        )
    }

    CategoriesView(
        categories = categories,
        onCategoryItemClick = onCategoryItemClick,
        onCreateCategoryClick = onCreateCategoryClick
    )

}

@Composable
fun LoadingView() {
    ShimmeringCategoriesScreen()
}

@Composable
fun CategoryItem(
    item: Category,
    onCategoryItemClick: (category: Category) -> Unit,
    curiotesCount: Int
) {
    Card(
        modifier = Modifier.padding(Dimens.paddingDefault).clickable { onCategoryItemClick(item) },
        border = BorderStroke(Dimens.dividerThickness,color = MaterialTheme.colorScheme.onPrimaryContainer)
    ) {
        Column (modifier = Modifier.padding(Dimens.paddingDefault)){
            TextCustom(text = "Category: ${item.name}", fontWeight = FontWeight.Bold)
            Divider(color = MaterialTheme.colorScheme.primary, thickness = Dimens.dividerThickness)
            Spacer(modifier = Modifier.height(Dimens.paddingLarge))
            TextCustom(text = "Curiotes: $curiotesCount")
        }
    }
}

@Composable
fun CreateNewCategoryItem(onCreateCategoryClick: () -> Unit) {
    Card(
        modifier = Modifier.padding(Dimens.paddingDefault).clickable { onCreateCategoryClick() },
        border = BorderStroke(Dimens.dividerThickness,color = MaterialTheme.colorScheme.onPrimaryContainer)
    ) {
        Column (
            modifier = Modifier.fillMaxSize().padding(Dimens.paddingDefault),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){
            RotatingIconTimed(
                icon = Icons.Filled.Add,
                contentDescription = "Add"
            )
        }
    }
}

@Composable
fun RotatingIconTimed(
    icon: ImageVector,
    contentDescription: String?,
    rotationDuration: Long = 1000L // Czas trwania animacji w milisekundach
) {
    val rotationAngle = remember { Animatable(0f) }
    var isRotating by remember { mutableStateOf(true) }

    LaunchedEffect(isRotating) {
        if (isRotating) {
            val startTime = withFrameNanos { it } // Pobieramy czas początkowy
            while (isRotating) {
                val currentTime = withFrameNanos { it }
                val elapsedTime = (currentTime - startTime) / 1_000_000 // Konwersja na milisekundy
                val progress = (elapsedTime / rotationDuration.toFloat()).coerceAtMost(1f)

                // Przyspieszanie i zwalnianie - dynamiczny czas obrotu
                val dynamicDuration = (400 + 900 * (1f - progress)).toInt() // Wolniej przy końcu

                rotationAngle.animateTo(
                    targetValue = rotationAngle.value + 360f,
                    animationSpec = tween(durationMillis = dynamicDuration, easing = LinearEasing)
                )

                if (progress >= 1f) {
                    isRotating = false // Kończymy animację po upływie czasu
                }
            }
        }
    }

    Icon(
        imageVector = icon,
        contentDescription = contentDescription,
        modifier = Modifier
            .graphicsLayer(rotationZ = rotationAngle.value) // Obrót ikony
            .size(90.dp),
        tint = MaterialTheme.colorScheme.primary
    )
}



@Composable
fun VibratingIconTimed(icon: ImageVector, contentDescription: String?, vibrationDuration: Long = 3000L) {
    var isVibrating by remember { mutableStateOf(true) }
    val offsetX = remember { Animatable(0f) } // Używamy Animatable do kontroli animacji

    // Uruchamiamy wibrację przez określony czas
    LaunchedEffect(isVibrating) {
        if (isVibrating) {
            // Animacja wibracji
            launch {
                while (isVibrating) {
                    offsetX.animateTo(
                        targetValue = 10f,
                        animationSpec = tween(durationMillis = 100, easing = LinearEasing)
                    )
                    offsetX.animateTo(
                        targetValue = -10f,
                        animationSpec = tween(durationMillis = 100, easing = LinearEasing)
                    )
                }
            }
            // Zatrzymujemy wibrację po zadanym czasie
            delay(vibrationDuration)
            isVibrating = false
        }
    }

    Icon(
        imageVector = icon,
        contentDescription = contentDescription,
        modifier = Modifier
            .offset(x = offsetX.value.dp) // Przesunięcie animowane
            .size(48.dp),
        tint = MaterialTheme.colorScheme.primary
    )
}



@Composable
fun ShimmeringCategoriesScreen() {
    
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
    onCategoryItemClick: (category: Category) -> Unit,
    onCreateCategoryClick: () -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = Dimens.gridItemMinSize)
    ) {
        item {
            CreateNewCategoryItem (onCreateCategoryClick = onCreateCategoryClick)
        }
        items(categories) { item: Category ->
            CategoryItem(
                item = item,
                onCategoryItemClick = onCategoryItemClick,
                curiotesCount = 0 //fixme to be replaced
                )
        }
    }
}

@Composable
@OptIn(ExperimentalResourceApi::class)
private fun EmptyCombinedCategoriesAlertDialog(
    onManualAssignClick: () -> Unit,
    dismiss: () -> Unit
) {
    AlertDialogExample(
        dialogTitle = stringResource(Res.string.dialog_title_no_curiotes_combined),
        dialogText = stringResource(Res.string.dialog_description_no_curiotes_combined),
        icon = Icons.Default.Warning,
        dismissButtonText = stringResource(Res.string.dismiss_button_text),
        confirmButtonText = stringResource(Res.string.curiotes),
        onConfirmation = onManualAssignClick,
        onDismiss = dismiss
    )
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