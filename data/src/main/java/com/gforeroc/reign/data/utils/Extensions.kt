package com.gforeroc.reign.data.utils

import java.text.ParseException
import java.time.Instant
import java.time.temporal.ChronoUnit

fun String.convertDateFromApiToAppFormat(): String {

    return try {
        val instant = Instant.parse(this)
        val now = Instant.now()
        when (val minutes = instant.until(now, ChronoUnit.MINUTES)) {
            in 0..60 -> return "$minutes minutes ago"
        }
        when (val hours = instant.until(now, ChronoUnit.HOURS)) {
            in 0..24 -> return "$hours hours ago"
        }
        when (val days = instant.until(now, ChronoUnit.DAYS)) {
            in 0..Int.MAX_VALUE -> return "$days days ago"
        }
        return "Date Not Found"
    } catch (e: ParseException) {
        "Date not found"
    }

}

fun String.findMinutes(): Long {
    val instant = Instant.parse(this)
    val now = Instant.now()
    return instant.until(now, ChronoUnit.MINUTES)
}

