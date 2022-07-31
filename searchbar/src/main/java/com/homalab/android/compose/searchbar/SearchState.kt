package com.homalab.android.compose.searchbar

import androidx.compose.runtime.*
import androidx.compose.ui.text.input.TextFieldValue

@Stable
class SearchState<T>(
    query: TextFieldValue,
    focused: Boolean,
    searching: Boolean,
    searchResults: List<T>?,
    selectedItem: T?
) {
    var query by mutableStateOf(query)
    var focused by mutableStateOf(focused)
    var searching by mutableStateOf(searching)
    var searchResults by mutableStateOf(searchResults)
    var selectedItem by mutableStateOf(selectedItem)

    val searchDisplayType: SearchDisplayType
        get() = when {
            !focused && query.text.isEmpty() -> SearchDisplayType.InitialResults
            focused && query.text.isEmpty() -> SearchDisplayType.Suggestions
            searchResults?.isEmpty() == true -> SearchDisplayType.NoResults
            searching -> SearchDisplayType.Searching
            searchResults == null -> SearchDisplayType.Error
            else -> SearchDisplayType.Results
        }
}

enum class SearchDisplayType {
    InitialResults, Suggestions, Results, NoResults, Searching, Error
}

@Composable
fun <T> rememberSearchState(
    query: TextFieldValue = TextFieldValue(""),
    focused: Boolean = false,
    searching: Boolean = false,
    searchResults: List<T>? = null,
    selectedItem: T? = null
): SearchState<T> {
    return remember {
        SearchState(
            query = query,
            focused = focused,
            searching = searching,
            searchResults = searchResults,
            selectedItem = selectedItem
        )
    }
}