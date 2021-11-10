package com.widsons.validator.data

import android.content.Context

data class ValidationResult(
    var errorRes : Int = -1,
    var error : String = "",
    var targetId : Int = -1,
    var errorViewId : Int = -1,
    var isValid : Boolean = false
) {
    fun getMessage(context : Context?) : String{
        if(errorRes == -1) {
            return error
        }
        else {
            return context?.getString(errorRes)?:"Error message"
        }
    }
}