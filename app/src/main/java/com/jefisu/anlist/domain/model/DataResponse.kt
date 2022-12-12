package com.jefisu.anlist.domain.model

data class DataResponse<T>(
    val items: List<T>,
    val totalItems: Int
)
