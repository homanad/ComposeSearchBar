package com.homalab.android.compose.searchbar

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.TextFieldValue

@Composable
fun SearchBar(
    query: TextFieldValue,
    onQueryChange: (TextFieldValue) -> Unit,
    onSearchFocusChange: (Boolean) -> Unit,
    onClearQuery: () -> Unit,
    onBack: () -> Unit,
    searching: Boolean,
    focused: Boolean,
    modifier: Modifier = Modifier,
    backIcon: @Composable () -> Unit = { DefaultBackIcon() },
    progressIndicator: @Composable () -> Unit = { DefaultProgressIndicator() },
    cancelIcon: @Composable () -> Unit = { DefaultCancelIcon() },
    textConfigs: SearchBarDefaults.TextConfigs = SearchBarDefaults.defaultTextConfigs(),
    searchBarConfigs: SearchBarDefaults.SearchBarConfigs = SearchBarDefaults.defaultSearchBarConfigs(),
    dimensionValues: SearchBarDefaults.DimensionValues = SearchBarDefaults.defaultDimensionValues()
) {

    val focusManager = LocalFocusManager.current
    if (!focused) focusManager.clearFocus()

    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {

        AnimatedVisibility(visible = focused) {
            IconButton(
                modifier = Modifier.padding(start = dimensionValues.iconPadding),
                onClick = {
                    onBack()
                }
            ) {
                backIcon()
            }
        }

        SearchTextField(
            query,
            onQueryChange,
            onSearchFocusChange,
            onClearQuery,
            searching,
            progressIndicator,
            cancelIcon,
            textConfigs,
            searchBarConfigs,
            dimensionValues
        )
    }
}

@Composable
private fun SearchTextField(
    query: TextFieldValue,
    onQueryChange: (TextFieldValue) -> Unit,
    onSearchFocusChange: (Boolean) -> Unit,
    onClearQuery: () -> Unit,
    searching: Boolean,
    progressIndicator: @Composable () -> Unit,
    cancelIcon: @Composable () -> Unit,
    textConfigs: SearchBarDefaults.TextConfigs = SearchBarDefaults.defaultTextConfigs(),
    searchBarConfigs: SearchBarDefaults.SearchBarConfigs,
    dimensionValues: SearchBarDefaults.DimensionValues
) {

    val focusRequester = remember { FocusRequester() }

    Surface(
        modifier = Modifier
            .height(dimensionValues.searchBarHeight)
            .padding(dimensionValues.contentPadding),
        color = searchBarConfigs.backgroundColor,
        shape = searchBarConfigs.shape,
    ) {
        Box(
            contentAlignment = Alignment.CenterStart,
        ) {

            if (query.text.isEmpty()) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(
                            start = dimensionValues.startTextPadding,
                            end = dimensionValues.contentPadding
                        )
                ) {
                    Text(
                        text = textConfigs.hintText,
                        style = textConfigs.hintTextStyle
                    )
                }
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                BasicTextField(
                    value = query,
                    onValueChange = onQueryChange,
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(1f)
                        .onFocusChanged {
                            onSearchFocusChange(it.isFocused)
                        }
                        .focusRequester(focusRequester)
                        .padding(
                            top = dimensionValues.contentPadding,
                            bottom = dimensionValues.contentPadding,
                            start = dimensionValues.startTextPadding,
                            end = dimensionValues.contentPadding
                        ),
                    singleLine = true,
                    textStyle = textConfigs.searchTextStyle
                )

                when {
                    searching -> {
                        progressIndicator()
                    }
                    query.text.isNotEmpty() -> {
                        IconButton(onClick = onClearQuery) {
                            cancelIcon()
                        }
                    }
                }
            }
        }
    }
}