package com.homalab.android.compose.sample.searchfield

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import com.homalab.android.compose.sample.searchfield.ui.theme.SampleSearchFieldTheme
import com.homalab.android.compose.searchbar.SearchBar
import com.homalab.android.compose.searchbar.rememberSearchState

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SampleSearchFieldTheme {
                val searchState = rememberSearchState<String>()
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
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
                        focused = searchState.focused
                    )
                }
            }
        }
    }
}