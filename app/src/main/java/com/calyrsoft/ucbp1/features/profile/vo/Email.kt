package com.calyrsoft.ucbp1.features.profile.domain.model.vo

@JvmInline
value class Email private constructor(val value: String) {
    companion object {
        private val REGEX = Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")
        fun of(raw: String?): Result<Email> {
            val v = raw?.trim().orEmpty()
            return if (REGEX.matches(v)) Result.success(Email(v))
            else Result.failure(IllegalArgumentException("Invalid email"))
        }
    }
}
