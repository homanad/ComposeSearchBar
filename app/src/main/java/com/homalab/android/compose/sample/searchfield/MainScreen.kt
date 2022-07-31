package com.homalab.android.compose.sample.searchfield

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.homalab.android.compose.searchbar.SearchBar
import com.homalab.android.compose.searchbar.SearchState

@Composable
fun MainScreen(modifier: Modifier = Modifier, searchState: SearchState<String>) {
    Column(modifier = modifier) {
        SearchBar(
            modifier = Modifier,
            query = searchState.query,
            onQueryChange = { searchState.query = it },
            onSearchFocusChange = {
                if (!searchState.focused) searchState.query = TextFieldValue("")
                searchState.focused = it
            },
            onClearQuery = { searchState.query = TextFieldValue("") },
            onBack = { searchState.focused = false },
            searching = searchState.searching,
            focused = searchState.focused,
            backIcon = { Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null) },
            progressIndicator = {
                CircularProgressIndicator(
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .size(36.dp)
                )
            },
            cancelIcon = {
                Icon(imageVector = Icons.Filled.Close, contentDescription = null)
            }
        )
    }
}