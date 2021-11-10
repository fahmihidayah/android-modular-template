package com.widsons.core.data

/**
 * Created on : October/22/2021
 * Author     : Muhammad Fahmi Hidayah
 * Company    : PiXilApps
 * Project    : ModularApp
 */
data class PagingResponse<D>(
    val code : Int = 0,
    val message : String = "",
    val count : Int = 0,
    val next : String ?= null,
    val previous : String ?= null,
    val results : D
)