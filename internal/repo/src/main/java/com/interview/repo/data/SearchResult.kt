package com.interview.repo.data


data class Result<T>(
    val data: T
)

data class SearchResult(
    val id: String,
    val title: String?,
    val description: String?,
    val cover: String,
    val favorite: Boolean,
    val images: List<ImageResult> ?= emptyList()
)

data class ImageResult(
    val id: String,
    val title: String?,
    val description: String?,
    val link: String,
    val type: String,
    val animated: Boolean,
    val favorite: Boolean
)


