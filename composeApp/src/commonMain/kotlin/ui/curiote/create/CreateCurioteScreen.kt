package ui.curiote.create

import CreateCurioteViewModel
import UpsertCurioteBaseViewModel
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button

import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import curioteskmp.composeapp.generated.resources.Res
import curioteskmp.composeapp.generated.resources.check_later
import curioteskmp.composeapp.generated.resources.create_curiote
import curioteskmp.composeapp.generated.resources.delete
import curioteskmp.composeapp.generated.resources.description
import curioteskmp.composeapp.generated.resources.link
import curioteskmp.composeapp.generated.resources.save
import curioteskmp.composeapp.generated.resources.title
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringResource
import ui.theme.Dimens

@OptIn(ExperimentalResourceApi::class)
@Composable
fun CreateCurioteScreen(
    viewModel: CreateCurioteViewModel,
    onSaveClick: () -> Unit
) {

    val curioteTitle by viewModel.curioteTitle.collectAsState()
    val curioteDescription by viewModel.curioteDescription.collectAsState()
    val needsDetails by viewModel.needsDetailedExplanation.collectAsState()
    val curioteLink by viewModel.curioteLink.collectAsState()

    CurioteContent(
        curioteTitle = curioteTitle,
        curioteDescription = curioteDescription,
        curioteLink = curioteLink,
        needsDetails = needsDetails,
        onSaveClick = {
            viewModel.saveCuriote()
            onSaveClick()
        },
        onTitleChange = viewModel::setCurioteTitle,
        onDescriptionChange = viewModel::setCurioteDescription,
        onLinkChange = viewModel::setCurioteLink,
        onCheckLaterChange = viewModel::setDetailedExplanation,
        viewModel = viewModel
    )
}

@OptIn(ExperimentalResourceApi::class)
@Composable
fun CurioteContent(
    curioteTitle: String,
    curioteDescription: String,
    curioteLink: String,
    needsDetails: Boolean,
    onSaveClick: () -> Unit,
    onTitleChange: (String) -> Unit,
    onDescriptionChange: (String) -> Unit,
    onLinkChange: (String) -> Unit,
    onCheckLaterChange: (Boolean) -> Unit,
    titleText: String = stringResource(Res.string.create_curiote),
    deleteButtonVisible: Boolean = false,
    onDeleteClick: () -> Unit = {},
    viewModel: UpsertCurioteBaseViewModel
) {
    val paddingDefault = Dimens.paddingDefault

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingDefault)
    ) {
        TextCustom(
            text = titleText,
            )
        OutlinedTextFieldCustom(
            value = curioteTitle,
            onValueChange = onTitleChange,
            label = { Text(text = stringResource(Res.string.title)) }
        )
        OutlinedTextFieldCustom(
            value = curioteDescription,
            onValueChange = onDescriptionChange,
            label = { Text(text = stringResource(Res.string.description)) }
        )
        OutlinedTextFieldCustom(
            value = curioteLink,
            onValueChange = onLinkChange,
            label = { Text(text = stringResource(Res.string.link)) },
            paddingDefault
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = paddingDefault),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextCustom(text = stringResource(Res.string.check_later))
            Switch(
                checked = needsDetails,
                onCheckedChange = onCheckLaterChange,
                modifier = Modifier.padding(start = paddingDefault),

                )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            if(deleteButtonVisible){
                CustomOutlinedButton(
                    onClick = onDeleteClick,
                    modifier = Modifier,
                    text = stringResource(Res.string.delete))
            }
            CustomOutlinedButton(
                onClick = {
                    onSaveClick()
                },
                modifier = Modifier,
                text = stringResource(Res.string.save)
            )
        }
        Spacer(modifier = Modifier.height(paddingDefault))
    }
}

@Composable
fun CustomOutlinedButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick:() -> Unit
) {
    OutlinedButton(
        onClick = onClick,
        modifier = modifier,
        colors = ButtonDefaults.outlinedButtonColors(
            contentColor = MaterialTheme.colorScheme.primary
        ),
        content = {
            TextCustom(text = text)
        }

    )
}

@Composable
fun TextCustom(
    text: String,
    modifier: Modifier = Modifier,
    fontWeight: FontWeight = FontWeight.Normal,
    fontSize: TextUnit = TextUnit.Unspecified
) {
    Text(
        text = text,
        modifier = modifier,
        color = MaterialTheme.colorScheme.primary,
        fontWeight = fontWeight,
        fontSize = fontSize
    )
}
@Composable
private fun OutlinedTextFieldCustom(
    value: String,
    onValueChange: (String) -> Unit,
    label: @Composable (() -> Unit)? = null,
    paddingDefault: Dp = Dimens.paddingDefault,
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = paddingDefault),
        label = label,
        colors = outlinedTextFieldPrimaryColors()
    )
}

@Composable
fun outlinedTextFieldPrimaryColors(): TextFieldColors {
    val primaryColor = MaterialTheme.colorScheme.primary
    return OutlinedTextFieldDefaults.colors(
        focusedTextColor = primaryColor,
        focusedBorderColor = primaryColor,
        unfocusedBorderColor = primaryColor,
        cursorColor = primaryColor,
        focusedLabelColor = primaryColor,
        unfocusedPlaceholderColor = primaryColor,
        focusedPlaceholderColor = primaryColor
    )
}


//todo to move to HEX project
@Composable
fun CustomCardSection() {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .background(Color.White)
    ) {
        SectionWithRoundedBottom(
            text = "Out on: 28 Aug '24 (Today)",
            icon = Icons.Default.Person,
            modifier = Modifier.clickable { /* Handle click */ }
        )
        Spacer(modifier = Modifier.height(8.dp))
        SectionWithRoundedBottom(
            text = "1 Adult",
            icon = Icons.Default.Person,
            modifier = Modifier.clickable { /* Handle click */ }
        )
        Spacer(modifier = Modifier.height(8.dp))
        SectionWithRoundedBottom(
            text = "0 Children",
            icon = Icons.Default.Person,
            modifier = Modifier.clickable { /* Handle click */ }
        )
        Spacer(modifier = Modifier.height(8.dp))
        SectionWithRoundedBottom(
            text = "0 Railcards applied",
            icon = Icons.Default.Person,
            modifier = Modifier.clickable { /* Handle click */ }
        )
    }
}

@Composable
fun SectionWithRoundedBottom(
    text: String,
    icon: ImageVector,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(bottomEnd = 8.dp, bottomStart = 8.dp))
            .background(Color.Black)
            .padding(bottom = 1.dp)
    ) {
        Box(modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(bottomEnd = 7.dp, bottomStart = 7.dp))
            .background(Color.White)
            .padding(bottom = 14.dp)) {


            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    modifier = Modifier.size(16.dp),
                    tint = Color(0xFF6200EE) // Fioletowy kolor
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = text,
                    fontSize = 16.sp,
                    color = Color.Black
                )
            }
        }
    }
}


@Composable
fun DynamicLinkList(viewModel: UpsertCurioteBaseViewModel) {
    viewModel.curioteLinks.value
    var linkList = remember { mutableStateListOf("") }

    LazyColumn(modifier = Modifier.padding(16.dp)) {
        item {
            Text(text = "Add Links", style = MaterialTheme.typography.titleLarge)
        }

        items(linkList.size) { index ->
            OutlinedTextField(
                value = linkList[index],
                onValueChange = {
                    newValue -> linkList[index] = newValue
                    viewModel.setLinksHelper(newValue, index)
                                },
                label = { Text("Link ${index + 1}") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
            )
        }

        item {
            Button(
                onClick = { linkList.add("") },
                modifier = Modifier.padding(top = 8.dp)
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add Link")
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Add Link")
            }
        }
    }
}