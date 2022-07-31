package com.homalab.android.compose.sample.searchfield.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.homalab.android.compose.sample.searchfield.R

@Composable
fun LottieAirplaneLoading(modifier: Modifier = Modifier) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.airplane_loading))

    LottieAnimation(
        composition = composition,
        modifier = modifier
    )
}