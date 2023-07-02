package com.retheviper.choseikun.domain.model

enum class Availability(val value: String) {
    AVAILABLE("available"),
    MAYBE("maybe"),
    UNAVAILABLE("unavailable"),
    UNKNOWN("unknown");

    companion object {
        fun from(value: String): Availability {
            return values().firstOrNull { it.value == value } ?: UNKNOWN
        }
    }
}