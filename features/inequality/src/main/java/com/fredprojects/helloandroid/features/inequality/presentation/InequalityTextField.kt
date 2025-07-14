package com.fredprojects.helloandroid.features.inequality.presentation

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType

@Composable
internal fun InequalityTextField(
    value: String,
    onChange: (String) -> Unit,
    label: String,
    imeAction: ImeAction,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = value,
        onValueChange = { if ((it.toFloatOrNull() != null) || it.isEmpty() || (it == "-")) onChange(it) },
        modifier = modifier,
        label = { Text(label) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone, imeAction = imeAction)
    )
}