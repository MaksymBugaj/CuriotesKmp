package ui.curiote.show.search

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import curioteskmp.composeapp.generated.resources.Res
import curioteskmp.composeapp.generated.resources.delete
import curioteskmp.composeapp.generated.resources.search
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringResource
import ui.theme.Dimens

@OptIn(ExperimentalResourceApi::class)
@Composable
fun HideableSearchTextField(
    text: String,
    isSearchActive: Boolean,
    onTextChange: (String) -> Unit,
    onSearchClick: () -> Unit,
    onCloseClick: () -> Unit,
    modifier: Modifier = Modifier,
    onFilterClick: () -> Unit
) {
    Box(modifier = modifier) {
        AnimatedVisibility(
            visible = isSearchActive,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            OutlinedTextField(
                value = text,
                onValueChange = onTextChange,
                shape = RoundedCornerShape(Dimens.roundedCornerSize),
                placeholder = { Text(text = stringResource(Res.string.search)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(Dimens.paddingLarge)
                    .padding(end = 80.dp)
            )
        }
        Row(modifier = Modifier.align(Alignment.CenterEnd)) {
            AnimatedVisibility(
                visible = isSearchActive,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                IconButton(onClick = onCloseClick) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Close search"
                    )
                }
            }
            AnimatedVisibility(
                visible = !isSearchActive,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                IconButton(onClick = onSearchClick) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Open search"
                    )
                }
            }
            IconButton(onClick = onFilterClick) {
                Icon(imageVector = Icons.Default.Share, contentDescription = "Filter")
            }
        }
    }

}