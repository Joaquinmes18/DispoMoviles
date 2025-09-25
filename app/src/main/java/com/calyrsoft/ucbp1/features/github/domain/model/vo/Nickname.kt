package com.calyrsoft.ucbp1.features.github.domain.model.vo

@JvmInline
value class Nickname (val value:String){
    init{
        require(value.isNotEmpty()) {
            "You must insert the nickname"
        }
    }
}