package com.widsons.validator.utils

import com.widsons.validator.annotation.Email
import com.widsons.validator.annotation.ErrorTargetView
import com.widsons.validator.annotation.TargetView
import com.widsons.validator.sampleObject.SignUpForm
import org.junit.Test

import org.junit.Assert.*
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import kotlin.math.sign

/**
 * Created on : June/21/2021
 * Author     : Muhammad Fahmi Hidayah
 * Company    : PiXilApps
 * Project    : TodoApps
 */
@RunWith(MockitoJUnitRunner::class)
class ValidationXKtTest {

    var signUpForm : SignUpForm = SignUpForm(
        email = "",
        name = "",
        password = "",
        confirmPassword = ""
    )

    @Test
    fun getTargetView() {
        assertNotNull(signUpForm.getFields()[0].getTargetView())
    }

    @Test
    fun `get target view id`() {
        assertEquals(1, signUpForm.getFields()[0].getTargetView().getId())
    }

    @Test
    fun `get error target view id`() {
        assertEquals(1, signUpForm.getFields()[0].getErrorTargetView().getId())
    }

    @Test
    fun `findAnnotation email found`() {
        assertNotNull(signUpForm.findAnnotation {annotation -> annotation is Email })
    }

    @Test
    fun `validateMethod validateObject size5`() {
        signUpForm.email = "fahmi@gmail.com"
        signUpForm.name = "fahmi"
        signUpForm.password = "123456"
        signUpForm.confirmPassword = "123456"
        signUpForm.isAgree = true
        assertEquals(5, signUpForm.validate().size)
    }

      @Test
    fun `isValidMethod validateObject true`() {
        signUpForm.email = "fahmi@gmail.com"
        signUpForm.name = "fahmi"
        signUpForm.password = "123456"
        signUpForm.confirmPassword = "123456"
        signUpForm.isAgree = true
        assertTrue( signUpForm.validate().isValid())
    }

    @Test
    fun `isValidMethod validateObject false`() {
        signUpForm.email = "fahminotemail"
        signUpForm.name = "fahmi"
        signUpForm.password = "123456"
        signUpForm.confirmPassword = "123456"
        signUpForm.isAgree = true
        assertFalse( signUpForm.validate().isValid())
    }

    @Test
    fun `findField findVField true`(){
        assertNotNull(signUpForm.getField("email"))
    }

}