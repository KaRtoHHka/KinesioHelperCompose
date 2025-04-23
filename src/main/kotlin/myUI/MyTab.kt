package myUI

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.UiComposable
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp

@Composable
@UiComposable
fun MyTab(
    selected: Boolean,
    closable: Boolean = true,
    onClick: () -> Unit,
    onCloseClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = false,
    text: String,
): Unit {
    Row(modifier = Modifier.padding(end = 16.dp)) {
        Button(onClick = onClick) {
            Text(text = text)
        }
        if (closable)
            Button(onClick = onCloseClick) {
                Text(text = "x")
            }
    }
}