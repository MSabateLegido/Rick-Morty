package com.marc.rickmorty.features.characters.domain.model


data class Character(
    val id: Int,
    val name: String,
    val status: Status,
    val species: String,
    val type: String,
    val gender: Gender,
    val origin: CharacterLocation,
    val location: CharacterLocation,
    val image: String,
    val episode: List<String>
)

enum class Gender(val value: String) {
    MALE("Male"),
    FEMALE("Female"),
    UNKNOWN("Unknown")
}

enum class Status(val value: String) {
    ALIVE("Alive"),
    DEAD("Dead"),
    UNKNOWN("Unknown")
}