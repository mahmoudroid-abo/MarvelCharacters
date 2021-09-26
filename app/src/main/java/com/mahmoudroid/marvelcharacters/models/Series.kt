package com.mahmoudroid.marvelcharacters.models

data class Series(
    val available: Int,
    val collectionURI: String,
    val items: List<ItemXX>,
    val returned: Int
)