package com.multimedialab.mfunzi

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import java.io.Serializable

data class Feature(
    val title: String,
    @DrawableRes val iconId: Int,
):Serializable
