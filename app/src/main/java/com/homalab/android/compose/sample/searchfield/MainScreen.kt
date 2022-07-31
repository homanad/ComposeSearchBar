package com.homalab.android.compose.sample.searchfield

import androidx.activity.compose.BackHandler
import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import com.homalab.android.compose.sample.searchfield.model.Fruit
import com.homalab.android.compose.sample.searchfield.model.dummyFruit
import com.homalab.android.compose.sample.searchfield.search.MessageText
import com.homalab.android.compose.sample.searchfield.search.SearchDisplay
import com.homalab.android.compose.searchbar.SearchBar
import com.homalab.android.compose.searchbar.SearchState
import kotlinx.coroutines.delay

@ExperimentalAnimationApi
@Composable
fun MainScreen(modifier: Modifier = Modifier, searchState: SearchState<Fruit>) {
    BackHandler(searchState.focused) {
        searchState.focused = false
    }

    LaunchedEffect(key1 = searchState.query, block = {
        if (searchState.query.text.isEmpty()) {
            searchState.searching = false
            return@LaunchedEffect
        }

        searchState.searching = true
        delay(1000)
        searchState.searchResults =
            dummyFruit.filter { it.name.lowercase().contains(searchState.query.text) }
        searchState.searching = false
    })

    Column(modifier = modifier) {
        SearchBar(
            modifier = Modifier,
            query = searchState.query,
            onQueryChange = { searchState.query = it },
            onSearchFocusChange = {
                if (!searchState.focused) searchState.query = TextFieldValue("")
                searchState.focused = it
            },
            onClearQuery = {
                searchState.query = TextFieldValue("")
//                searchState.focused = false
            },
            onBack = { searchState.focused = false },
            searching = searchState.searching,
            focused = searchState.focused
        )

        AnimatedContent(
            targetState = searchState.focused,
            transitionSpec = {
                scaleIn() + fadeIn() + slideInVertically(
                    animationSpec = tween(300),
                    initialOffsetY = { fullHeight -> fullHeight }) with fadeOut(
                    animationSpec = tween(100)
                )
            }
        ) { isFocused ->
            if (isFocused) {
                SearchDisplay(searchState = searchState)
            } else {
                searchState.selectedItem?.let {
                    MessageText(text = "${it.name} is selected")
                }
            }
        }
    }
}

