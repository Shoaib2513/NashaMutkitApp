package com.example.project_nm

data class RehabCenter(
    val name: String,
    val address: String,
    val phone: String,
    var isBookmarked: Boolean = false
)
