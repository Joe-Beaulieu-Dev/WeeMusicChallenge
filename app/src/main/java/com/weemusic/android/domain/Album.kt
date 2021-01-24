package com.weemusic.android.domain

import java.time.LocalDate

data class Album(
    val id: Int,
    val name: String,
    val images: List<String>,
    val rights: String,
    val title: String,
    val artist: String,
    val category: String,
    val releaseDate: LocalDate,
    val price: String
)
