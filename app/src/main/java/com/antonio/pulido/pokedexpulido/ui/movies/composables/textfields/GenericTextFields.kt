package com.antonio.pulido.pokedexpulido.ui.movies.composables.textfields

import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.antonio.pulido.pokedexpulido.ui.theme.PokedexFont
import com.antonio.pulido.pokedexpulido.ui.theme.TextColor

@Composable
fun GenericTextField(
    value: String,
    onValueTextChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    isError: Boolean = false,
    icon: Painter? = null,
    label: String,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Next,
    isTextArea: Boolean = false,
    isEnabled: Boolean = true,
    onClick: () -> Unit = {},
    insideTextStyle: TextStyle = TextStyle.Default.copy(fontSize = 11.sp, fontFamily = PokedexFont),
    conditionLabel: Boolean = false,
    placeHolder: @Composable () -> Unit = {},
    @StringRes supportingText: Int? = null,
    heighSize: Int = 46
) {

    Column(
        modifier = modifier
    ) {
        if (label.isNotEmpty()) {
            Text(
                text = label,
                style = MaterialTheme.typography.labelMedium,
                modifier = Modifier.padding(start = 12.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
        }
        if (conditionLabel) {
            Text(
                text = "  ",
                style = MaterialTheme.typography.labelMedium,
                modifier = Modifier.padding(start = 12.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
        }
        OutlinedTextField(
            value = value,
            onValueChange = onValueTextChange,
            modifier = Modifier
                .fillMaxWidth()
                .height(if (isTextArea) 120.dp else heighSize.dp)
                .clickable {
                    onClick()
                },
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = TextColor,
                unfocusedTextColor = TextColor,
                cursorColor = TextColor,
                errorTextColor = Color.Red,
                unfocusedBorderColor = TextColor,
                focusedBorderColor = TextColor,
                disabledBorderColor = TextColor,
                disabledTextColor = TextColor,
                disabledContainerColor = Color.White,
                errorContainerColor = Color.White,
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
            ),

            shape = RoundedCornerShape(10.dp),
            trailingIcon = {
                if (icon != null) {
                    Icon(
                        painter = icon,
                        contentDescription = null,
                        modifier = Modifier.size(16.dp),
                        tint = TextColor
                    )
                }
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = keyboardType,
                imeAction = imeAction
            ),
            isError = isError,
            enabled = isEnabled,
            singleLine = !isTextArea,
            textStyle = insideTextStyle,
            placeholder = placeHolder
        )
        Spacer(modifier = modifier.height(8.dp))
        if (supportingText!=null){
            Text(
                text = stringResource(id = supportingText),
                style = MaterialTheme.typography.labelSmall.copy(
                    color = TextColor
                ),
                modifier = modifier.padding(start = 16.dp)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GenericDropDown(
    options: List<String>,
    selectedOption: String,
    onOptionSelected: (String) -> Unit,
    modifier: Modifier = Modifier,
    isError: Boolean = false,
    icon: Painter? = null,
    label: String,
    isEnabled: Boolean = false,
    conditionLabel: Boolean = false,
    @StringRes supportingText: Int? = null,
    heighSize: Int = 46
) {
    var expanded by remember { mutableStateOf(false) }

    Column(modifier = modifier) {
        if (label.isNotEmpty()) {
            Text(
                text = label,
                style = MaterialTheme.typography.labelMedium,
                modifier = Modifier.padding(start = 12.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
        }
        if (conditionLabel) {
            Text(
                text = "  ",
                style = MaterialTheme.typography.labelMedium,
                modifier = Modifier.padding(start = 12.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
        }

        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded }
        ) {
            OutlinedTextField(
                value = selectedOption,
                onValueChange = { },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(heighSize.dp)
                    .menuAnchor(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = TextColor,
                    unfocusedTextColor = TextColor,
                    cursorColor = TextColor,
                    errorTextColor = Color.Red,
                    unfocusedBorderColor = TextColor,
                    focusedBorderColor = TextColor,
                    disabledBorderColor = TextColor,
                    disabledTextColor = TextColor,
                    disabledContainerColor = Color.White,
                    errorContainerColor = Color.White,
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                ),
                shape = RoundedCornerShape(10.dp),
                trailingIcon = {
                    if (icon != null) {
                        Icon(
                            painter = icon,
                            contentDescription = null,
                            modifier = Modifier.size(16.dp),
                            tint = TextColor
                        )
                    }
                },
                isError = isError,
                enabled = isEnabled,
                singleLine = true,
                readOnly = true, // Important for drop down
                textStyle = TextStyle.Default.copy(fontSize = 11.sp, fontFamily = PokedexFont)
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                options.forEach { option ->
                    DropdownMenuItem(
                        text = { Text(text = option) },
                        onClick = {
                            onOptionSelected(option)
                            expanded = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        if (supportingText != null) {
            Text(
                text = stringResource(id = supportingText),
                style = MaterialTheme.typography.labelSmall.copy(color = TextColor),
                modifier = Modifier.padding(start = 16.dp)
            )
        }
    }
}