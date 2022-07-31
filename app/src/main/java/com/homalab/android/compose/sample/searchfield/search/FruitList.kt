package com.homalab.android.compose.sample.searchfield.search

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.homalab.android.compose.sample.searchfield.model.Fruit

@Composable
fun FruitList(
    modifier: Modifier = Modifier,
    itemList: List<Fruit>,
    onItemClick: (Fruit) -> Unit
) {
    val scrollState = rememberLazyListState()

    LazyColumn(state = scrollState) {
        items(items = itemList, key = { it.id }) {
            FruitRow(it, modifier = modifier.clickable { onItemClick(it) })
        }
    }
}

@Composable
private fun FruitRow(fruit: Fruit, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(text = fruit.name, modifier = Modifier.padding(12.dp))
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(Color.Gray.copy(alpha = 0.5f))
        )
    }
}