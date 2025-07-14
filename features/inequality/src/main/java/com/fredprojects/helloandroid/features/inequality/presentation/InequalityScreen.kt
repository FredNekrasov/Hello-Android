package com.fredprojects.helloandroid.features.inequality.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.fredprojects.helloandroid.features.inequality.InequalityResult
import com.fredprojects.helloandroid.features.inequality.R

@Composable
fun InequalityScreen(
    inequalityResult: InequalityResult,
    calculate: (Float?, Float?) -> Unit,
    modifier: Modifier = Modifier
) {
    var a by rememberSaveable { mutableStateOf("") }
    var b by rememberSaveable { mutableStateOf("") }
    Column(modifier, Arrangement.Center, Alignment.CenterHorizontally) {
        Text("ax + b < 0")
        Spacer(Modifier.height(8.dp))
        InequalityTextField(a, { a = it }, "a", ImeAction.Next)
        InequalityTextField(b, { b = it }, "b", ImeAction.Done)
        Spacer(Modifier.height(8.dp))
        Text(
            text = if(!inequalityResult.isSuccess()) {
                stringResource(inequalityResult.content())
            } else inequalityResult.solution
        )
        Spacer(Modifier.height(8.dp))
        Button(onClick = { calculate(a.toFloatOrNull(), b.toFloatOrNull()) }) {
            Text(stringResource(R.string.displayResult))
        }
    }
}