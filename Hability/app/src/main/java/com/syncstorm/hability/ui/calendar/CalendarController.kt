package com.syncstorm.hability.ui.calendar

import android.content.Context
import org.joda.time.DateTime
import org.joda.time.LocalDate
import org.joda.time.format.DateTimeFormat
import org.joda.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.time.temporal.Temporal
import java.util.*
import java.util.stream.Collectors
import java.util.stream.Stream
import org.joda.time.DurationFieldType

import org.joda.time.Days

class CalendarController(val context: Context)  {

    fun DatesBetweeen(startDate: LocalDate, endDate: LocalDate): MutableList<String> {
        val dates: MutableList<String> = ArrayList()
        val days = Days.daysBetween(startDate, endDate).days + 1
        for (i in 0 until days) {
            val d = startDate.withFieldAdded(DurationFieldType.days(), i)
            dates.add(d.toString(DateTimeFormat.forPattern("dd/MM/yyyy")))
        }

        return dates
    }

}