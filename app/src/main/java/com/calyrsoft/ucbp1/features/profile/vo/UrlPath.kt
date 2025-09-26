package com.calyrsoft.ucbp1.features.profile.domain.model.vo

@JvmInline
value class UrlPath private constructor(val value: String) {
    companion object {
        private val REGEX = Regex("^https?://.+", RegexOption.IGNORE_CASE)
        fun of(raw: String?): Result<UrlPath> {
            val v = raw?.trim().orEmpty()
            return if (REGEX.matches(v)) Result.success(UrlPath(v))
            else Result.failure(IllegalArgumentException("Invalid url"))
        }
    }
}
