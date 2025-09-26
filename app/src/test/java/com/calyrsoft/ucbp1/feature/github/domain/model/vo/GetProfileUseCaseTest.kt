package com.calyrsoft.ucbp1.features.profile.domain.usecase

import com.calyrsoft.ucbp1.features.profile.domain.model.ProfileModel
import com.calyrsoft.ucbp1.features.profile.domain.repository.IProfileRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

// Fake repo OK (devuelve un ProfileModel válido)
private class FakeSuccessRepo : IProfileRepository {
    override fun fetchData(): Result<ProfileModel> =
        Result.success(
            ProfileModel(
                pathUrl   = "https://a.com/photo.png",
                name      = "Lisa Simpson",
                email     = "lisa@springfield.com",
                cellphone = "+1 555 000 111",
                summary   = "Estudiante aplicada"
            )
        )
}

// Fake repo con error
private class FakeErrorRepo : IProfileRepository {
    override fun fetchData(): Result<ProfileModel> =
        Result.failure(IllegalStateException("Network error"))
}

@OptIn(ExperimentalCoroutinesApi::class)
class GetProfileUseCaseTest {

    @Test
    fun `usecase returns success`() = runTest {
        val uc = GetProfileUseCase(FakeSuccessRepo())
        val result = uc() // invoke() es suspend, por eso runTest
        assertTrue(result.isSuccess)
        assertEquals("Lisa Simpson", result.getOrThrow().name)
    }

    @Test
    fun `usecase returns failure`() = runTest {
        val uc = GetProfileUseCase(FakeErrorRepo())
        val result = uc()
        assertTrue(result.isFailure)
    }
}
