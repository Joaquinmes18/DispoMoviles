package com.calyrsoft.ucbp1.features.profile.data.repository

import com.calyrsoft.ucbp1.features.profile.domain.model.ProfileModel
import com.calyrsoft.ucbp1.features.profile.domain.model.vo.Cellphone
import com.calyrsoft.ucbp1.features.profile.domain.model.vo.Email
import com.calyrsoft.ucbp1.features.profile.domain.model.vo.Name
import com.calyrsoft.ucbp1.features.profile.domain.model.vo.UrlPath
import com.calyrsoft.ucbp1.features.profile.domain.repository.IProfileRepository

class ProfileRepository : IProfileRepository {
    override fun fetchData(): Result<ProfileModel> {
        val name      = Name.of("Homero J. Simpson").getOrElse { return Result.failure(it) }
        val email     = Email.of("homero.simpson@springfieldmail.com").getOrElse { return Result.failure(it) }
        val cellphone = Cellphone.of("+1 (939) 555-7422").getOrElse { return Result.failure(it) }
        val url       = UrlPath.of("https://www.viaempresa.cat/uploads/s1/43/99/69/homer.jpg").getOrElse { return Result.failure(it) }

        return Result.success(
            ProfileModel(
                name = name,
                email = email,
                cellphone = cellphone,
                pathUrl = url,
                summary = "Ciudadano de Springfield y dedicado inspector de seguridad en la Planta Nuclear."
            )
        )
    }
}
