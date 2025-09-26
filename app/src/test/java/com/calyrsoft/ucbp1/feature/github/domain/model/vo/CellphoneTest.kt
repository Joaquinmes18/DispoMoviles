package com.calyrsoft.ucbp1.feature.github.domain.model.vo

import com.calyrsoft.ucbp1.features.profile.domain.model.vo.Cellphone
import org.junit.Assert.*
import org.junit.Test

class CellphoneTest {

    @Test fun `valid cellphone`() {
        val r = Cellphone.of("+1 (555) 123-456")
        assertTrue(r.isSuccess)
        assertEquals("+1 (555) 123-456", r.getOrThrow().value)
    }

    @Test fun `invalid cellphone - letters`() {
        val r = Cellphone.of("abc123")
        assertTrue(r.isFailure)
    }

    @Test fun `invalid cellphone - too short`() {
        val r = Cellphone.of("12345")
        assertTrue(r.isFailure)
    }
}
