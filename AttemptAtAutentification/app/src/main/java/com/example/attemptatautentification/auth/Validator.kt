package com.example.attemptatautentification.auth

import java.util.regex.Pattern

/**
 * Класс валидации данных.
 */
object Validator {
    /**
     * Проверить что это адрес электронной почты по формату.
     *
     * @param email предполагаемый адрес электронной почты.
     * @return является ли входная строка адресом электронной почты.
     */
    fun isEmail(email: String?): Boolean {
        return Pattern.matches(
                "([a-zA-Z0-9]+(?:[._+-][a-zA-Z0-9]+)*)@([a-zA-Z0-9]+(?:[.-][a-zA-Z0-9]+)*[.][a-zA-Z]{2,})",
                email)
    }

    /**
     * проверить достаточно ли надёжен пароль.
     * @param password пароль.
     * @return достаточно ли он нам надёжен.
     */
    fun strongEnoughPassword(password: String): Boolean {
        return password.length > 5
    }
}