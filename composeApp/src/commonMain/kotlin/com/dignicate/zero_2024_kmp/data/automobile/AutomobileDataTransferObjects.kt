package com.dignicate.zero_2024_kmp.data.automobile

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class CompanyDto(
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: List<LocalizedStringDto>,
    @SerialName("country")
    val country: List<LocalizedStringDto>,
    @SerialName("founded_year")
    val foundedYear: Int
)

@Serializable
data class LocalizedStringDto(
    @SerialName("language")
    val language: String,
    @SerialName("value")
    val value: String
)
