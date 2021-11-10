package com.widsons.validator.sampleObject

import com.widsons.validator.annotation.*

/**
 * Created on : June/21/2021
 * Author     : Muhammad Fahmi Hidayah
 * Company    : PiXilApps
 * Project    : TodoApps
 */
class SignUpForm(
    @Email(error = "Fill valid email")
    @TargetView(targetId = 1)
    @ErrorTargetView(errorTargetId = 1)
    var email : String,

    @NotEmpty(error = "Name should not be empty")
    var name : String,

    @Min(error = "Password should not be empty", min = 5)
    var password : String,

    @Equal(error = "Confirm password should not be empty", referenceField = "password")
    var confirmPassword : String,

    @RequiredChecked(error = "Require check")
    var isAgree : Boolean = false
)