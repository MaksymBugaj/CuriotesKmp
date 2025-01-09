package ui.category

import domain.model.category.Category
import domain.model.category.CurioteCategoryCombined

/**
 * View State for Categories. When the user enters this screen, can see the list of Categories. Each Category has a counter.
 * when user enters the screen and have and empty category, dialog shows up.
 * When dialog has been shown at least two times, it won't show up again in the future -> but the button to asign will be visible
 * States:
 * [EmptyCategoriesState] - no category objects
 * [EmptyCombinedCategoriesState] - show dialog that clicking an icon in the category card can lead to the assigning category to the curiote screen.
 *
 *
 */
sealed class CategoriesViewState {
    data object EmptyCategoriesState: CategoriesViewState()
    data class EmptyCombinedCategoriesState(val categories: List<Category>): CategoriesViewState()
    data class CombinedCategoriesState(val combinedCategories: List<CurioteCategoryCombined>): CategoriesViewState()
    data object Loading: CategoriesViewState()


}