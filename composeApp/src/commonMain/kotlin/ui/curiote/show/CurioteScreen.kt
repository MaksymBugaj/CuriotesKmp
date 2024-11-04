package ui.curiote.show

import CurioteViewModel
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.FabPosition
import androidx.compose.material.Icon
import androidx.compose.material.RadioButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import curioteskmp.composeapp.generated.resources.Res
import curioteskmp.composeapp.generated.resources.curiotes
import domain.curiote.Curiote
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.Month
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringResource
import ui.curiote.create.TextCustom
import ui.curiote.show.search.HideableSearchTextField
import ui.theme.Dimens

@OptIn(ExperimentalResourceApi::class)
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
        backgroundColor = MaterialTheme.colorScheme.surface,
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
                        TextCustom(
                            text = stringResource(Res.string.curiotes),
                            fontWeight = FontWeight.SemiBold,
                            fontSize = MaterialTheme.typography.bodyLarge.fontSize
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
                    println("#NOPE: LECIMY Z IDKIMI: ${curiote.id}")
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
        if (curiote.toCheck) MaterialTheme.colorScheme.tertiaryContainer else MaterialTheme.colorScheme.primaryContainer
    Card(modifier = Modifier
        .padding(Dimens.paddingSemi)
        .clickable {
            onCurioteClick(curiote.id)
        }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .background(color = cardBackgroundColor)
                .padding(Dimens.paddingDefault)
        ) {

            Column(modifier = Modifier.weight(0.85f)) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    curiote.title?.let { title ->
                        Text(
                            text = title,
                            fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.weight(9f),
                            textAlign = TextAlign.Center
                        )
                    }
                    Icon(
                        imageVector = Icons.Outlined.Info,
                        contentDescription = "Action Required",
                        tint = MaterialTheme.colorScheme.error,
                        modifier = Modifier.size(20.dp).weight(1f).alpha(alertAlpha.value)
                    )
                }
                curiote.curiote?.let { curiote ->
                    Text(
                        text = curiote,
                        fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                        color = Color.White,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Start
                    )
                }

                curiote.links?.map { curioteLink ->
                    Row {
                        Text(
                            text = curioteLink.link,
                            fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                            color = Color.White,
                            modifier = Modifier,
                            textAlign = TextAlign.Start
                        )
                        if (curioteLink.link.lowercase().contains("http")) {
                            Icon(
                                imageVector = Icons.Outlined.Notifications,
                                contentDescription = "Action Required",
                                tint = MaterialTheme.colorScheme.error,
                            )
                        }
                    }
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth().wrapContentHeight().weight(0.15f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center

            ) {

                Box(
                    modifier = Modifier
                        .height(30.dp)
                        .width(2.dp)
                        .clip(
                            RoundedCornerShape(2.dp)
                        )
                        .background(MaterialTheme.colorScheme.onPrimaryContainer)
                )

                Column(modifier = Modifier.padding(start = Dimens.paddingDefault)) {
                    val parsedDate: LocalDate = curiote.created.date
                    val month = parsedDate.month.getDisplayName().uppercase()

                    // Getting the day of the month (e.g., "26")
                    val day = parsedDate.dayOfMonth.toString()
                    Text(
                        text = month,
                        fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary,
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = day,
                        fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary,
                        textAlign = TextAlign.Center
                    )
                }
            }


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

fun Month.getDisplayName(): String {
    return when (this) {
        Month.JANUARY -> "JAN"
        Month.FEBRUARY -> "FEB"
        Month.MARCH -> "MAR"
        Month.APRIL -> "APR"
        Month.MAY -> "MAY"
        Month.JUNE -> "JUN"
        Month.JULY -> "JUL"
        Month.AUGUST -> "AUG"
        Month.SEPTEMBER -> "SEP"
        Month.OCTOBER -> "OCT"
        Month.NOVEMBER -> "NOV"
        Month.DECEMBER -> "DEC"
        else -> ""
    }
}

data class SelectableItem(
    val id: Int,
    val title: String,
    val curiote: String,
    val created: LocalDateTime,
    val toCheck: Boolean,
)