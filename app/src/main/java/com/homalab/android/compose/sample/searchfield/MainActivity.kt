package com.homalab.android.compose.sample.searchfield

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.ExperimentalTextApi
import com.homalab.android.compose.sample.searchfield.model.Fruit
import com.homalab.android.compose.sample.searchfield.model.dummyFruit
import com.homalab.android.compose.sample.searchfield.ui.theme.SampleSearchFieldTheme
import com.homalab.android.compose.searchbar.rememberSearchState

@ExperimentalTextApi
@ExperimentalAnimationApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SampleSearchFieldTheme {
                val searchState = rememberSearchState<Fruit>()
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen(searchState = searchState)
                }
            }
        }
    }
}