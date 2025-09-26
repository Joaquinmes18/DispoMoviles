package com.calyrsoft.ucbp1.feature.github.domain.model.vo

import com.calyrsoft.ucbp1.features.profile.domain.model.vo.UrlPath
import org.junit.Assert.*
import org.junit.Test

class UrlPathValueObjectTest {

    @Test fun `valid https url`() {
        val r = UrlPath.of("https://example.com/image.png")
        assertTrue(r.isSuccess)
        assertEquals("https://example.com/image.png", r.getOrThrow().value)
    }

    @Test fun `valid http url`() {
        val r = UrlPath.of("http://host/path")
        assertTrue(r.isSuccess)
    }

    @Test fun `invalid url - no scheme`() {
        val r = UrlPath.of("www.example.com")
        assertTrue(r.isFailure)
    }

    @Test fun `invalid url - blank`() {
        val r = UrlPath.of("   ")
        assertTrue(r.isFailure)
    }
}
