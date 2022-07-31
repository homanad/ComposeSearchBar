package com.homalab.android.compose.searchbar

import androidx.compose.runtime.*
import androidx.compose.ui.text.input.TextFieldValue

@Stable
class SearchState<T>(
    query: TextFieldValue,
    focused: Boolean,
    searching: Boolean,
    suggestions: List<T>,
    searchResults: List<T>?
) {
    var query by mutableStateOf(query)
    var focused by mutableStateOf(focused)
    var searching by mutableStateOf(searching)
    var suggestions by mutableStateOf(suggestions)
    var searchResults by mutableStateOf(searchResults)

    val searchDisplayType: SearchDisplayType
        get() = when {
            !focused && query.text.isEmpty() -> SearchDisplayType.InitialResults
            focused && query.text.isEmpty() -> SearchDisplayType.Suggestions
            searchResults?.isEmpty() == true -> SearchDisplayType.NoResults
            searchResults == null -> SearchDisplayType.NetworkUnavailable
            else -> SearchDisplayType.Results
        }
}

enum class SearchDisplayType {
    InitialResults, Suggestions, Results, NoResults, NetworkUnavailable
}

@Composable
fun <T> rememberSearchState(
    query: TextFieldValue = TextFieldValue(""),
    focused: Boolean = false,
    searching: Boolean = false,
    suggestions: List<T> = emptyList(),
    searchResults: List<T>? = null
): SearchState<T> {
    return remember {
        SearchState(
            query = query,
            focused = focused,
            searching = searching,
            suggestions = suggestions,
            searchResults = searchResults,
        )
    }
}