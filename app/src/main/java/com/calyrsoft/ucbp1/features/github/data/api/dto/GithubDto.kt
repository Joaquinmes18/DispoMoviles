package com.calyrsoft.ucbp1.features.github.data.api.dto

import com.calyrsoft.ucbp1.features.github.domain.model.vo.Nickname
import com.calyrsoft.ucbp1.features.github.domain.model.vo.UrlPath
import com.google.gson.annotations.SerializedName

data class GithubDto (val login: Nickname,
                      @SerializedName("avatar_url") val url: UrlPath)