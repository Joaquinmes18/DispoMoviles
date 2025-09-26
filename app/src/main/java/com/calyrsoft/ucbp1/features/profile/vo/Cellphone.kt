package com.calyrsoft.ucbp1.features.profile.domain.model.vo

@JvmInline
value class Cellphone private constructor(val value: String) {
    companion object {
        // acepta dígitos, espacios, +, -, paréntesis
        private val REGEX = Regex("^[+()\\-\\d\\s]{7,20}$")
        fun of(raw: String?): Result<Cellphone> {
            val v = raw?.trim().orEmpty()
            return if (REGEX.matches(v)) Result.success(Cellphone(v))
            else Result.failure(IllegalArgumentException("Invalid cellphone"))
        }
    }
}
