package com.narvatov.planthelper

import com.narvatov.planthelper.utils.isInvalidEmail
import com.narvatov.planthelper.utils.isInvalidPassword
import com.narvatov.planthelper.utils.isInvalidPhone
import org.junit.Test

class ValidationExtensionsUnitTest {

    @Test
    fun verifyEmptyEmailIsInvalidEmailReturnsTrue() {
        val emptyEmail = ""

        val result = emptyEmail.isInvalidEmail()

        assert(result)
    }

    @Test
    fun verifyInvalidEmailIsInvalidEmailReturnsTrue() {
        val invalidEmail = "invalidemailaslaskasld"

        val result = invalidEmail.isInvalidEmail()

        assert(result)
    }

    @Test
    fun verifyEmailWithErrorInTheEndIsInvalidEmailReturnsTrue() {
        val invalidEmail = "validpart@invalidpart"

        val result = invalidEmail.isInvalidEmail()

        assert(result)
    }

    @Test
    fun verifyValidEmailIsInvalidEmailReturnsFalse() {
        val validEmail = "hipi96222@gmail.com"

        val result = validEmail.isInvalidEmail()

        assert(!result)
    }

    @Test
    fun verifyEmptyPasswordIsInvalidPasswordReturnsTrue() {
        val emptyPassword = ""

        val result = emptyPassword.isInvalidPassword()

        assert(result)
    }

    @Test
    fun verifyPasswordWith7SymbolsIsInvalidPasswordReturnsTrue() {
        val invalidEmail = "1234567"

        val result = invalidEmail.isInvalidPassword()

        assert(result)
    }

    @Test
    fun verifyValidInput_InvalidPassword_ReturnsFalse() {
        val validPassoword = "128189sjasid"

        val result = validPassoword.isInvalidPassword()

        assert(!result)
    }

    @Test
    fun verifyCharSymbolsIsInvalidPhoneReturnsTrue() {
        val invalidPhone = "aojaskllkasd"

        val result = invalidPhone.isInvalidPhone()

        assert(result)
    }

    @Test
    fun verifyEmptyPhoneIsInvalidPhoneReturnsTrue() {
        val invalidPhone = ""

        val result = invalidPhone.isInvalidPhone()

        assert(result)
    }

    @Test
    fun verifyTooMuchNumbersIsInvalidPhoneReturnsTrue() {
        val invalidPhone = "19278719387931927312813421"

        val result = invalidPhone.isInvalidPhone()

        assert(result)
    }

    @Test
    fun verifyMixedNumbersAndCharsIsInvalidPhoneReturnsTrue() {
        val invalidPhone = "192a81s3421"

        val result = invalidPhone.isInvalidPhone()

        assert(result)
    }

    @Test
    fun verifyValidInputInvalidPhoneReturnsFalse() {
        val invalidPhone = "+380506789145"

        val result = invalidPhone.isInvalidPhone()

        assert(!result)
    }

}