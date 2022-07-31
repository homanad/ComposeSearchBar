package com.homalab.android.compose.searchbar

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun DefaultBackIcon() {
    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
}

@Composable
fun DefaultProgressIndicator() {
    CircularProgressIndicator(
        modifier = Modifier
            .padding(horizontal = 8.dp)
            .size(28.dp)
    )
}

@Composable
fun DefaultCancelIcon() {
    Icon(imageVector = Icons.Filled.Close, contentDescription = null)
}

object SearchBarDefaults {

    fun defaultSearchBarConfigs() = SearchBarConfigs(
        backgroundColor = Color(0xffF5F5F5),
        shape = RoundedCornerShape(percent = 50)
    )

    @Immutable
    class SearchBarConfigs(
        val backgroundColor: Color,
        val shape: Shape
    ) {

        @Stable
        fun copy(
            backgroundColor: Color = this.backgroundColor,
            shape: Shape = this.shape
        ) = SearchBarConfigs(backgroundColor, shape)
    }

    fun defaultTextConfigs() = TextConfigs(
        hintText = "Type to search",
        hintTextStyle = TextStyle(color = Color(0xff757575)),
        searchTextStyle = TextStyle(color = Color.Black)
    )

    @Immutable
    class TextConfigs(
        val hintText: String,
        val hintTextStyle: TextStyle,
        val searchTextStyle: TextStyle
    ) {

        @Stable
        fun copy(
            hintText: String = this.hintText,
            hintTextStyle: TextStyle = this.hintTextStyle,
            searchTextStyle: TextStyle = this.searchTextStyle
        ) = TextConfigs(hintText, hintTextStyle, searchTextStyle)
    }

    fun defaultDimensionValues() =
        DimensionValues(
            searchBarHeight = 52.dp,
            contentPadding = 8.dp,
            iconPadding = 4.dp,
            startTextPadding = 24.dp
        )

    @Immutable
    class DimensionValues(
        val searchBarHeight: Dp,
        val contentPadding: Dp,
        val iconPadding: Dp,
        val startTextPadding: Dp
    ) {

        @Stable
        fun copy(
            searchBarHeight: Dp = this.searchBarHeight,
            contentPadding: Dp = this.contentPadding,
            iconPadding: Dp = this.iconPadding,
            startTextPadding: Dp = this.startTextPadding
        ) = DimensionValues(searchBarHeight, contentPadding, iconPadding, startTextPadding)
    }
}