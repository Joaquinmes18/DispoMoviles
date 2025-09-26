package com.calyrsoft.ucbp1.feature.github.domain.model.vo

import com.calyrsoft.ucbp1.features.profile.domain.model.vo.Email
import org.junit.Assert.*
import org.junit.Test

class EmailTest {

    @Test fun `valid email`() {
        val r = Email.of("lisa@springfield.com")
        assertTrue(r.isSuccess)
        assertEquals("lisa@springfield.com", r.getOrThrow().value)
    }

    @Test fun `invalid email - missing at`() {
        val r = Email.of("lisa.springfield.com")
        assertTrue(r.isFailure)
    }

    @Test fun `invalid email - empty`() {
        val r = Email.of("")
        assertTrue(r.isFailure)
    }
}
