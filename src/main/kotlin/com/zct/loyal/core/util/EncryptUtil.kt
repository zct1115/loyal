package com.zct.loyal.core.util

import org.springframework.security.crypto.password.StandardPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder



object EncryptUtil {
    init {

    }

    private val SITE_WIDE_SECRET = "loyal"
    private val encoder = StandardPasswordEncoder(SITE_WIDE_SECRET)

    fun encrypt(rawPassword: String): String {
        return encoder.encode(rawPassword)
    }

    fun match(rawPassword: String, password: String): Boolean {
        return encoder.matches(rawPassword, password)
    }
}