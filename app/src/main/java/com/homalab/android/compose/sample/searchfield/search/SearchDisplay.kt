package com.homalab.android.compose.sample.searchfield.search

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.homalab.android.compose.sample.searchfield.model.Fruit
import com.homalab.android.compose.sample.searchfield.model.dummyFruit
import com.homalab.android.compose.searchbar.SearchDisplayType
import com.homalab.android.compose.searchbar.SearchState

@Composable
fun SearchDisplay(searchState: SearchState<Fruit>) {
    when (searchState.searchDisplayType) {
        SearchDisplayType.InitialResults -> {
            FruitList(
                itemList = dummyFruit.subList(5, 20)
            ) { fruit ->
                searchState.selectedItem = fruit
                searchState.focused = false
            }
        }
        SearchDisplayType.NoResults -> {
            MessageText(text = "No result")
        }
        SearchDisplayType.Searching -> {
            MessageText(text = "Searching")
        }
        SearchDisplayType.Error -> {
            MessageText(text = "Error")
        }
        SearchDisplayType.Suggestions -> {
            FruitList(
                itemList = dummyFruit.subList(0, 5)
            ) { fruit ->
                searchState.selectedItem = fruit
                searchState.focused = false
            }
        }
        SearchDisplayType.Results -> {
            searchState.searchResults?.let {
                FruitList(
                    itemList = it
                ) { fruit ->
                    searchState.selectedItem = fruit
                    searchState.focused = false
                }
            }
        }
    }
}

@Composable
fun MessageText(
    modifier: Modifier = Modifier,
    text: String,
    textAlign: TextAlign = TextAlign.Center
) {
    Text(
        text = text,
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        textAlign = textAlign
    )
}