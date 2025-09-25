package com.calyrsoft.ucbp1.feature.github.domain.model.vo

import com.calyrsoft.ucbp1.features.github.domain.model.vo.UrlPath
import org.junit.Test

class UrlPathTest {
    @Test(expected = Exception::class)
    fun `test UrlPath`(){
        UrlPath("myurl")
        UrlPath("")
    }
}