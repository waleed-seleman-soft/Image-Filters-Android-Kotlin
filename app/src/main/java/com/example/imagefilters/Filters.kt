package com.example.imagefilters

import com.mukesh.imageproccessing.filters.Filter

data class Filters(
    // name of the filter
    var title: String,
    // type of the filter
    var filter: Filter
)