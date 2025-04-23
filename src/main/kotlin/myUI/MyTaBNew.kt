package myUI

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.UiComposable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import theme.lightGrey

@Composable
@UiComposable
fun MyTabNew(
    selected: Boolean,
    closable: Boolean = true,
    onClick: () -> Unit,
    onCloseClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = false,
    text: String,
): Unit {
    Row(modifier = Modifier.padding(end = 16.dp),
        verticalAlignment = Alignment.CenterVertically) {
        Tab(
            selected = selected,
            text = { Text(text) },
            onClick = onClick,
        )
//        Button(onClick = onClick) {
//            Text(text = text)
//        }
        if (closable)
            OutlinedButton(
                onClick = onCloseClick,
                shape = CircleShape,
                modifier = Modifier.size(30.dp),
                border = BorderStroke(0.dp, Color(0x00000000)),
                contentPadding = PaddingValues(7.dp),
                colors = ButtonDefaults.outlinedButtonColors(
                    backgroundColor = Color(0x00000000),
                    contentColor = lightGrey
                )
            ) {
                Icon(Icons.Default.Close, contentDescription = "content")
//                Text(text = "x", maxLines = 1, modifier = Modifier.padding(0.dp))
            }
    }
}