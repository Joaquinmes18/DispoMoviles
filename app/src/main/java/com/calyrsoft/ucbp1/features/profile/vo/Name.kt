package com.calyrsoft.ucbp1.features.profile.domain.model.vo

@JvmInline
value class Name private constructor(val value: String) {
    companion object {
        fun of(raw: String?): Result<Name> {
            val v = raw?.trim().orEmpty()
            return if (v.length in 2..60) Result.success(Name(v))
            else Result.failure(IllegalArgumentException("Invalid name"))
        }
    }
}
