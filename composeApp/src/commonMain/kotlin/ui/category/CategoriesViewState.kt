package ui.category

import domain.model.category.Category
import domain.model.category.CurioteCategoryCombined

/**
 * View State for Categories.
 * States:
 * [EmptyCategoriesState] - no category objects
 *
 */
sealed class CategoriesViewState {
    data object EmptyCategoriesState: CategoriesViewState()
    data object EmptyCombinedCategoriesState: CategoriesViewState()
    data object DisplayEmptyCombinedCategoriesDialogState: CategoriesViewState()
    data object DismissEmptyCombinedCategoriesDialogState: CategoriesViewState()
    data class DisplayCategoriesCombinedView(val combined: List<CurioteCategoryCombined>): CategoriesViewState()


}