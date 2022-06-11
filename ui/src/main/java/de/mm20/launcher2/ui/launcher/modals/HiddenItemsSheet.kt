package de.mm20.launcher2.ui.launcher.modals

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import de.mm20.launcher2.ui.R
import de.mm20.launcher2.ui.component.BottomSheetDialog
import de.mm20.launcher2.ui.launcher.search.common.grid.SearchResultGrid

@Composable
fun HiddenItemsSheet(
    onDismiss: () -> Unit
) {
    val viewModel: HiddenItemsSheetVM = viewModel()

    val context = LocalContext.current

    BottomSheetDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                stringResource(R.string.preference_hidden_items),
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(end = 16.dp),
                maxLines = 1
            )
        },
        actions = {
            IconButton(onClick = { viewModel.showHiddenItems(context) }) {
                Icon(imageVector = Icons.Rounded.Edit, contentDescription = null)
            }
        },
        confirmButton = {
            OutlinedButton(onClick = onDismiss) {
                Text(stringResource(id = R.string.close))
            }
        }
    ) {


        val items by remember { viewModel.hiddenItems }.collectAsState(emptyList())
        SearchResultGrid(
            items,
            modifier = Modifier
                .verticalScroll(rememberScrollState())
        )
    }
}

private enum class SwipeState {
    Default, Dismiss
}