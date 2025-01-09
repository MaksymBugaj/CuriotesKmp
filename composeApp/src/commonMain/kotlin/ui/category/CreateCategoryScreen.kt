package ui.category

import CreateCategoryViewModel
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import curioteskmp.composeapp.generated.resources.Res
import curioteskmp.composeapp.generated.resources.create_category
import curioteskmp.composeapp.generated.resources.save
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringResource
import ui.curiote.create.CustomOutlinedButton
import ui.curiote.create.OutlinedTextFieldCustom
import ui.curiote.create.TextCustom
import ui.theme.Dimens

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
            .padding(Dimens.paddingDefault),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        TextCustom(
            text = stringResource(Res.string.create_category),
            modifier = Modifier.padding(bottom = Dimens.paddingDefault)
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