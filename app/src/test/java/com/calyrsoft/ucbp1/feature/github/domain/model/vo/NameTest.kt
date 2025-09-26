package com.calyrsoft.ucbp1.feature.github.domain.model.vo

import com.calyrsoft.ucbp1.features.profile.domain.model.vo.Name
import org.junit.Assert.*
import org.junit.Test

class NameTest {

    @Test fun `valid name`() {
        val r = Name.of("Marge Simpson")
        assertTrue(r.isSuccess)
        assertEquals("Marge Simpson", r.getOrThrow().value)
    }

    @Test fun `invalid name - too short`() {
        val r = Name.of("A")
        assertTrue(r.isFailure)
    }

    @Test fun `invalid name - blank`() {
        val r = Name.of("   ")
        assertTrue(r.isFailure)
    }
}
