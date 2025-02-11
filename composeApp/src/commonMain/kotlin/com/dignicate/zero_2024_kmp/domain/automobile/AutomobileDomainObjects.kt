package com.dignicate.zero_2024_kmp.domain.automobile

data class Company(
    val id: Id,
    val name: String,
    val country: String,
    val foundedYear: Int
) {
    data class Id(val value: Int)
}
