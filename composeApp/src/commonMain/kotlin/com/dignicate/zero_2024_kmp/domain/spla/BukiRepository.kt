package com.dignicate.zero_2024_kmp.domain.spla

import com.dignicate.zero_2024_kmp.data.sample.TodoDto

interface BukiRepository {
    suspend fun getBukiData(): TodoDto
}
