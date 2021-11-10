package com.widsons.validator.annotation

/**
 * Created on : December/23/2020
 * Author     : Muhammad Fahmi Hidayah
 * Company    : PiXilApps
 * Project    : Yemnak
 */

@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
annotation class ErrorTargetView (
    val errorTargetId : Int = -1
)