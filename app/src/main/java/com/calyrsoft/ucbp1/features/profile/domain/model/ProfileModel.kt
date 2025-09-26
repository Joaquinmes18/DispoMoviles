package com.calyrsoft.ucbp1.features.profile.domain.model

import com.calyrsoft.ucbp1.features.profile.domain.model.vo.Cellphone
import com.calyrsoft.ucbp1.features.profile.domain.model.vo.Email
import com.calyrsoft.ucbp1.features.profile.domain.model.vo.Name
import com.calyrsoft.ucbp1.features.profile.domain.model.vo.UrlPath

data class ProfileModel(
    val pathUrl: UrlPath,
    val name: Name,
    val email: Email,
    val cellphone: Cellphone,
    val summary: String
)
