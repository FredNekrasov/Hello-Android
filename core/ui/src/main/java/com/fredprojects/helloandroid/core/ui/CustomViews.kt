package com.fredprojects.helloandroid.core.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

typealias Action = () -> Unit
// text fields
@Composable
fun HAHeaderText(
    text: String,
    textStyle: TextStyle,
    modifier: Modifier = Modifier
) {
    Text(
        text,
        modifier,
        fontFamily = FontFamily.Serif,
        textAlign = TextAlign.Center,
        style = textStyle
    )
}
@Composable
fun HAText(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified
) {
    Text(
        text,
        modifier,
        color = color,
        fontFamily = FontFamily.Serif,
        textAlign = TextAlign.Justify
    )
}
@Composable
fun HANumericTextField(
    value: String,
    onValueChange: (String) -> Unit,
    labelId: Int,
    isValueCorrect: Boolean = true,
    imeAction: ImeAction = ImeAction.Done,
    keyboardType: KeyboardType = KeyboardType.NumberPassword,
    errorString: String = stringResource(R.string.error)
) {
    TextField(
        value,
        { if(((it.toIntOrNull() != null) && (it.toInt() >= 0)) || (it == "")) onValueChange(it) },
        label = { HAText(stringResource(labelId)) },
        isError = !isValueCorrect,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType, imeAction = imeAction),
        supportingText = { if (!isValueCorrect) HAText(errorString, color = MaterialTheme.colorScheme.error) },
        colors = OutlinedTextFieldDefaults.colors()
    )
}
@Composable
fun HATextField(
    value: String,
    onValueChange: (String) -> Unit,
    labelId: Int,
    isValueCorrect: Boolean = true,
    imeAction: ImeAction = ImeAction.Next,
    keyboardType: KeyboardType = KeyboardType.Text,
    errorString: String = stringResource(R.string.error)
) {
    TextField(
        value,
        onValueChange,
        label = { HAText(stringResource(labelId))},
        isError = !isValueCorrect,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType, imeAction = imeAction),
        supportingText = { if (!isValueCorrect) HAText(errorString, color = MaterialTheme.colorScheme.error) },
        shape = MaterialTheme.shapes.small,
        colors = OutlinedTextFieldDefaults.colors()
    )
}
// buttons
@Composable
fun HAButton(onClick: Action, text: String, modifier: Modifier = Modifier) {
    Button(onClick, modifier) { HAText(text) }
}
@Composable
fun HARadioButton(text: String, selected: Boolean, onSelect: Action) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        RadioButton(selected = selected, onClick = onSelect)
        HAText(text)
    }
}
@Composable
fun HAIconButton(
    onClick: Action,
    icon: ImageVector,
    modifier: Modifier = Modifier
) {
    IconButton(onClick, modifier) { Icon(icon, icon.toString()) }
}
@Composable
fun HAFloatingActionButton(icon: ImageVector, onClick: Action) {
    FloatingActionButton(onClick) {
        Icon(icon, icon.toString())
    }
}
// other
@Composable
fun HACard(
    modifier: Modifier,
    primaryColor: Color,
    secondaryColor: Color,
    cornerRadius: Dp = 10.dp,
    cutCornerSize: Dp = 30.dp
) {
    Canvas(modifier) {
        val clipPath = Path().apply {
            lineTo(size.width - cutCornerSize.toPx(), 0f)
            lineTo(size.width, cutCornerSize.toPx())
            lineTo(size.width, size.height)
            lineTo(0f, size.height)
            close()
        }
        clipPath(clipPath) {
            drawRoundRect(color = primaryColor, size = size, cornerRadius = CornerRadius(cornerRadius.toPx()))
            drawRoundRect(
                color = secondaryColor,
                topLeft = Offset(size.width - cutCornerSize.toPx(), - 100f),
                size = Size(cutCornerSize.toPx() + 100f, cutCornerSize.toPx() + 100f),
                cornerRadius = CornerRadius(cornerRadius.toPx())
            )
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HATopBar(
    goBack: Action,
    action: @Composable (RowScope.() -> Unit) = {}
) {
    TopAppBar(
        title = { HAHeaderText(stringResource(R.string.app_name), textStyle = MaterialTheme.typography.headlineSmall) },
        navigationIcon = { HAIconButton(goBack, Icons.AutoMirrored.Default.KeyboardArrowLeft) },
        actions = action,
        windowInsets = WindowInsets.captionBar
    )
}