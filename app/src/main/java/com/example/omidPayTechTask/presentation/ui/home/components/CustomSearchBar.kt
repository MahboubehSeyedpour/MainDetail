package com.example.omidPayTechTask.presentation.ui.home.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.omidPayTechTask.utils.extensions.isBlankOrEmpty


@Composable
fun CustomSearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    onSearchClicked: () -> Unit,
    modifier: Modifier = Modifier,
    onDeleteClicked: () -> Unit,
    placeholderText: String = "",
) {

    val keyboardController = LocalSoftwareKeyboardController.current

    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            value = query,
            onValueChange = onQueryChange,
            placeholder = {
                Text(text = placeholderText, style = TextStyle(textAlign = TextAlign.Start))
            },
            trailingIcon = {
                AnimatedVisibility(
                    visible = !query.isBlankOrEmpty(), enter = fadeIn(), exit = fadeOut()
                ) {
                    Icon(painter = painterResource(id = android.R.drawable.ic_menu_close_clear_cancel),
                        contentDescription = "Delete",
                        modifier = Modifier.clickable { onDeleteClicked() })
                }
            },
            maxLines = 1,
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = {
                onSearchClicked()
                keyboardController?.hide()
            }),
        )
    }
}