package ui.curiote.show

import CurioteViewModel
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Checkbox
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.RadioButton
import androidx.compose.material.Scaffold

import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add

import androidx.compose.material.icons.filled.Notifications

import androidx.compose.material.icons.outlined.Info
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.benasher44.uuid.Uuid
import curioteskmp.composeapp.generated.resources.Res
import curioteskmp.composeapp.generated.resources.curiotes
import domain.curiote.Curiote
import kotlinx.datetime.LocalDateTime
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringResource
import ui.curiote.show.search.HideableSearchTextField
import ui.theme.Dimens

@OptIn(ExperimentalFoundationApi::class, ExperimentalResourceApi::class)
@Composable
fun CurioteScreen(
    viewModel: CurioteViewModel,
    onCreateCurioteClick: () -> Unit,
    onCurioteClick: (curioteId: Long) -> Unit,
) {
    val paddingDefault = Dimens.paddingDefault

    val curiotes by viewModel.curiotes.collectAsState(emptyList())
    val searchText by viewModel.searchText.collectAsState()
    val isSearchActive by viewModel.isSearchActive.collectAsState(initial = false)

    var expanded by remember { mutableStateOf(false) }
    val sortByDate by viewModel.sortByDateModified.collectAsState(initial = false)
    val sortByDone by viewModel.sortByDone.collectAsState(initial = false)

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = onCreateCurioteClick,
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add curiote",
                    tint = Color.White
                )
            }
        },
        floatingActionButtonPosition = FabPosition.End
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Row {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    HideableSearchTextField(
                        text = searchText,
                        isSearchActive = isSearchActive,
                        onTextChange = viewModel::setSearchValue,
                        onSearchClick = viewModel::onToggleChanged,
                        onCloseClick = viewModel::onToggleChanged,
                        modifier = Modifier.fillMaxWidth().wrapContentHeight(),
                        onFilterClick = { expanded = !expanded }
                    )
                    this@Column.AnimatedVisibility(
                        visible = !isSearchActive,
                        enter = fadeIn(),
                        exit = fadeOut()
                    ) {
                        Text(
                            text = stringResource(Res.string.curiotes),
                            fontWeight = FontWeight.SemiBold,
                            fontSize = MaterialTheme.typography.body1.fontSize
                        )
                    }
                    Box(
                        contentAlignment = Alignment.TopEnd // Pozycjonowanie ikony z prawej strony
                    ) {
                        DropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false },
                            modifier = Modifier.align(Alignment.CenterEnd),
                            offset = DpOffset(x = (-16).dp, y = 20.dp)
                        ) {
                            DropdownMenuItem(
                                onClick = {
                                    viewModel.updateSortOptions(!sortByDate, false)
                                    viewModel.setSearchValue("")
                                    expanded = false
                                }
                            ) {
                                Row {
                                    RadioButton(
                                        selected = sortByDate,
                                        onClick = null
                                    )
                                    Text(text = "Sort by Date Modified")
                                }
                            }
                            DropdownMenuItem(
                                onClick = {
                                    viewModel.updateSortOptions(false, !sortByDone)
                                    viewModel.setSearchValue("")
                                    expanded = false
                                }
                            ) {
                                Row {
                                    RadioButton(
                                        selected = sortByDone,
                                        onClick = null // Obsługujemy to kliknięciem na całym elemencie
                                    )
                                    Text(text = "Sort by Done")
                                }
                            }
                        }
                    }
                }

            }
            LazyColumn(
                modifier = Modifier.weight(1f)
            ) {
                items(
                    items = curiotes,
                    key = {
                        it.id
                    }) { curiote ->
                    CurioteItem(curiote = curiote, onCurioteClick = {
                        onCurioteClick(it)
                    })
                }
            }
        }
    }
}

@Composable
fun CustomAlertDialog(
    curiote: Curiote,
    onDelete: (Curiote) -> Unit,
    onDismiss: () -> Unit,
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = "Alert Dialog") },
        text = { Text(text = "Jetpack Compose Alert Dialog") },
        confirmButton = { // 6
            Button(
                onClick = {
                    onDelete(curiote)
                }
            ) {
                Text(
                    text = "Confirm",
                    color = Color.White
                )
            }
        },
        dismissButton = {
            Button(
                onClick = onDismiss,
                content = {
                    Text(
                        text = "Dismiss",
                        color = Color.White
                    )
                }
            )
        }

    )
}

@Composable
fun CurioteItem(
    curiote: Curiote,
    onCurioteClick: (curioteId: Long) -> Unit,
) {
    val alertAlpha = remember {
        mutableStateOf(0f)
    }
    if (curiote.toCheck) alertAlpha.value = 1f
    val cardBackgroundColor =
        if (curiote.toCheck) MaterialTheme.colors.onError else MaterialTheme.colors.onBackground
    Card(modifier = Modifier
        .padding(Dimens.paddingSemi)
        .clickable {
            onCurioteClick(curiote.id)
        }

    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .background(color = cardBackgroundColor)
                .padding(Dimens.paddingDefault)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                curiote.title?.let { title ->
                    Text(
                        text = title,
                        fontSize = MaterialTheme.typography.body1.fontSize,
                        color = MaterialTheme.colors.primary,
                        modifier = Modifier.weight(9f),
                        textAlign = TextAlign.Center
                    )
                }
                Icon(
                    imageVector = Icons.Outlined.Info,
                    contentDescription = "Action Required",
                    tint = MaterialTheme.colors.error,
                    modifier = Modifier.size(20.dp).weight(1f).alpha(alertAlpha.value)
                )
            }
            curiote.curiote?.let { curiote ->
                Text(
                    text = curiote,
                    fontSize = MaterialTheme.typography.caption.fontSize,
                    color = MaterialTheme.colors.primary,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Start
                )
            }
            Text(
                text = curiote.created.date.toString(),
                fontSize = MaterialTheme.typography.caption.fontSize,
                color = MaterialTheme.colors.primary,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.End
            )


//            if(!curiousNote.links.isNullOrEmpty())  {
//                curiousNote.links.map { link ->
//                    Text(
//                        text = link.link,
//                        modifier = Modifier.padding(Dimens.Padding.paddingDefault)
//                    )
//                }
//            }
        }
    }
}

data class SelectableItem(
    val id: Int,
    val title: String,
    val curiote: String,
    val created: LocalDateTime,
    val toCheck: Boolean,
)