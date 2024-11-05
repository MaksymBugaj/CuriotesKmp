package ui.curiote.edit

import EditCurioteViewModel
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import curioteskmp.composeapp.generated.resources.Res
import curioteskmp.composeapp.generated.resources.edit_curiote
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringResource
import ui.curiote.create.CurioteContent

@OptIn(ExperimentalResourceApi::class)
@Composable
fun EditCurioteScreen(
    viewModel: EditCurioteViewModel,
    navigateUp: () -> Unit,
    curioteId: Long
) {
    val curioteTitle by viewModel.curioteTitle.collectAsState()
    val curioteDescription by viewModel.curioteDescription.collectAsState()
    val needsDetails by viewModel.needsDetailedExplanation.collectAsState()
    val curioteLink by viewModel.curioteLink.collectAsState()

    LaunchedEffect(curioteId) {
        viewModel.restoreCuriote(curioteId)
        println("#NOPE EditCurioteScreen on launched effect: $curioteId")
    }

    DisposableEffect(Unit) {
        onDispose {
            println("#NOPE EditCurioteScreen curiote is disposed ")
        }
    }

    val curiote by viewModel.curiote.collectAsState()
    println("#NOPE EditCurioteScreen curiote: $curiote")
    curiote?.let { curioteModel ->
        //todo delete
        println("#NOPE EditCurioteScreen curioteModel:? $curioteModel")
        CurioteContent(
            curioteTitle = curioteTitle,
            curioteDescription = curioteDescription,
            curioteLink = curioteLink,
            needsDetails = needsDetails,
            onSaveClick = {
                viewModel.saveCuriote()
                navigateUp()
            },
            onTitleChange = viewModel::setCurioteTitle,
            onDescriptionChange = viewModel::setCurioteDescription,
            onLinkChange = viewModel::setCurioteLink,
            onCheckLaterChange = viewModel::setDetailedExplanation,
            titleText = stringResource(Res.string.edit_curiote),
            deleteButtonVisible = true,
            onDeleteClick = {
                viewModel.deleteCuriote(curioteModel)
                navigateUp()
            },
            viewModel = viewModel
        )
    }


}