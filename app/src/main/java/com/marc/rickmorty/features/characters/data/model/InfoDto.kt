package com.marc.rickmorty.features.characters.data.model

import kotlinx.serialization.Serializable


@Serializable
data class InfoDto(
    val count: Int,
    val pages: Int,
    val next: String?,
    val prev: String?
)