package com.antonio.pulido.pokedexpulido.ui.composables.items

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.antonio.pulido.pokedexpulido.R
import com.antonio.pulido.pokedexpulido.ui.theme.PrimaryCard
import com.antonio.pulido.pokedexpulido.ui.theme.Secondary

@Composable
fun ItemInfo(
    modifier: Modifier = Modifier,
    title: String,
    showEdit: () -> Unit,
    editActor: () -> Unit,
    onDissmiDialog: () -> Unit,
    listOptions: List<String>,
    onChangeText: (String) -> Unit,
    titleDialog: String,
    content: @Composable () -> Unit
) {
    var isExpanded by remember { mutableStateOf(false) }

    Column(modifier = modifier.fillMaxWidth()) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .clickable {
                    isExpanded = !isExpanded
                },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = title,
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.titleLarge.copy(
                    color = Secondary,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Normal
                ),
                modifier = modifier.weight(1f)
            )

            Icon(
                painter = painterResource(id = if (isExpanded) R.drawable.ic_cross else R.drawable.ic_add),
                contentDescription = "",
                modifier = modifier.size(30.dp)
            )
        }

        if (isExpanded) {
            Row(modifier = modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                Icon(
                    painter = painterResource(id = R.drawable.twotone_edit_24),
                    contentDescription = "",
                    modifier = modifier
                        .size(30.dp)
                        .clickable {
                            showEdit()
                        }
                )
            }
            Spacer(modifier = modifier.height(8.dp))
            content()
        }

        Spacer(modifier = modifier.height(16.dp))

        Divider(
            thickness = 1.dp,
            color = PrimaryCard
        )
        Spacer(modifier = modifier.height(8.dp))

    }
}