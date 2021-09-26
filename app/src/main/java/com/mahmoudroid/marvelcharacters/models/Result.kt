package com.mahmoudroid.marvelcharacters.models

import java.io.Serializable

data class Result(
    var comics: Comics,
    val description: String,
    var events: Events,
    var id: Int,
    var modified: String,
    var name: String,
    var resourceURI: String,
    var series: Series,
    var stories: Stories,
    var thumbnail: Thumbnail,
    var urls: List<Url>
) : Serializable