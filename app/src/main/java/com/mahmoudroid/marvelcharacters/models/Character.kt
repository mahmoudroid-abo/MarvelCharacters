package com.mahmoudroid.marvelcharacters.models

import kotlinx.android.parcel.Parcelize
import java.io.Serializable


data class Character(
    val attributionHTML: String,
    val attributionText: String,
    val code: Int,
    val copyright: String,
    val data: Data,
    val etag: String,
    val status: String
)