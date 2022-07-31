package com.homalab.android.compose.sample.searchbar.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.homalab.android.compose.sample.searchbar.R

@Composable
fun LottieCircularLoading(modifier: Modifier = Modifier) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.circular_loading))

    LottieAnimation(
        composition = composition,
        modifier = modifier
    )
}