package com.calyrsoft.ucbp1.features.profile.domain.usecase

import com.calyrsoft.ucbp1.features.profile.domain.model.ProfileModel
import com.calyrsoft.ucbp1.features.profile.domain.repository.IProfileRepository
import kotlinx.coroutines.delay

class GetProfileUseCase(
    private val repository: IProfileRepository
) {
    suspend operator fun invoke(): Result<ProfileModel> {
        // Simula tiempo de carga (ejemplo examen: 3 segundos)
        delay(3000)
        return repository.fetchData()
    }
}
