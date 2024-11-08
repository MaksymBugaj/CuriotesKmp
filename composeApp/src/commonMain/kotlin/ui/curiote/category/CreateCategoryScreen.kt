package ui.curiote.category

import CategoryViewModel
import CreateCategoryViewModel
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import curioteskmp.composeapp.generated.resources.Res
import curioteskmp.composeapp.generated.resources.create_category
import curioteskmp.composeapp.generated.resources.create_curiote
import curioteskmp.composeapp.generated.resources.save
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringResource
import ui.curiote.create.CustomOutlinedButton
import ui.curiote.create.OutlinedTextFieldCustom
import ui.curiote.create.TextCustom

@OptIn(ExperimentalResourceApi::class)
@Composable
fun CreateCategoryScreen(
    viewModel: CreateCategoryViewModel,
    onNavigateBack: () -> Unit
) {
    val categoryName by viewModel.categoryName.collectAsState()
    Column(
        modifier = Modifier
            .fillMaxSize()
    ){
        TextCustom(
            text = stringResource(Res.string.create_category)
        )
        OutlinedTextFieldCustom(
            value = categoryName,
            onValueChange = viewModel::setCategoryName
        )

        CustomOutlinedButton(
            text = stringResource(Res.string.save),
            onClick = {
                viewModel.createCategory()
                onNavigateBack()
            }
        )
    }
}