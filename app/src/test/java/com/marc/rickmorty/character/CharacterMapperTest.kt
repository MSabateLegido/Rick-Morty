package com.marc.rickmorty.character

import com.marc.rickmorty.features.characters.data.mapper.toDomain
import com.marc.rickmorty.features.characters.data.model.CharacterDto
import com.marc.rickmorty.features.characters.data.model.CharacterLocationDto
import com.marc.rickmorty.features.characters.domain.model.Character
import com.marc.rickmorty.features.characters.domain.model.Gender
import com.marc.rickmorty.features.characters.domain.model.Status
import junit.framework.TestCase.assertEquals
import org.junit.Test

class CharacterMapperTest {

    @Test
    fun `maps character dto to domain`() {
        val dto = CharacterDto(
            id = 1,
            name = "Rick Sanchez",
            status = "Alive",
            species = "Human",
            type = "",
            gender = "Male",
            origin = CharacterLocationDto(
                name = "Earth",
                url = ""
            ),
            location = CharacterLocationDto(
                name = "Earth",
                url = ""
            ),
            image = "rick.jpg",
            episode = listOf(
                "https://rickandmortyapi.com/api/episode/1"
            )
        )

        val result = dto.toDomain()

        assertEquals(
            Character(
                id = 1,
                name = "Rick Sanchez",
                status = Status.ALIVE,
                species = "Human",
                type = "",
                gender = Gender.MALE,
                image = "rick.jpg",
                episodes = listOf(
                    "https://rickandmortyapi.com/api/episode/1"
                )
            ),
            result
        )
    }

    @Test
    fun `maps unknown status and gender`() {
        val dto = CharacterDto(
            id = 2,
            name = "Unknown Character",
            status = "unknown",
            species = "Unknown",
            type = "",
            gender = "unknown",
            origin = CharacterLocationDto(
                name = "unknown",
                url = ""
            ),
            location = CharacterLocationDto(
                name = "unknown",
                url = ""
            ),
            image = "",
            episode = emptyList()
        )

        val result = dto.toDomain()

        assertEquals(Status.UNKNOWN, result.status)
        assertEquals(Gender.UNKNOWN, result.gender)
    }
}