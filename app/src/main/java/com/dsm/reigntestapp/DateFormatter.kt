package com.dsm.reigntestapp

import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit.MILLISECONDS


class DateFormatter {


    private val format: SimpleDateFormat

    private val formatType = "yyyy-MM-dd'T'HH:mm:ss"

    init {
        format = SimpleDateFormat(formatType)
    }

    fun formatDate(date: String): String {


        val createdTime = format.parse(date)
        val currentTime = getCurrentUtcTime()

        val dateDiff = currentTime.time - createdTime.time

        val months = MILLISECONDS.toDays(dateDiff) / 30
        val weeks = MILLISECONDS.toDays(dateDiff) / 7
        val days = MILLISECONDS.toDays(dateDiff)
        val hours = MILLISECONDS.toHours(dateDiff)
        val minutes = MILLISECONDS.toMinutes(dateDiff)
        val seconds = MILLISECONDS.toSeconds(dateDiff)

        return when {
            months > 0L -> " - " + months.toString() + "m"
            weeks > 0L -> " - " + weeks.toString() + "w"
            days == 1L -> " - " + "Yesterday"
            days > 0L -> " - " + days.toString() + "d"
            hours > 0L -> " - " + hours.toString() + "h"
            minutes > 0L -> " - " + minutes.toString() + "m"
            else -> " - " + seconds.toString() + "s"
        }

    }

    private fun getCurrentUtcTime(): Date {
        val sdf = SimpleDateFormat(formatType)
        sdf.timeZone = TimeZone.getTimeZone("UTC")
        var newDate = Date()
        return format.parse(sdf.format(newDate))
    }

}