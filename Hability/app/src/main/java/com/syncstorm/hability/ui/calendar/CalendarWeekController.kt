package com.syncstorm.hability.ui.calendar

import org.joda.time.DateTime
import org.joda.time.LocalDate
import org.joda.time.format.DateTimeFormat
import org.joda.time.format.DateTimeFormatter
import java.util.*
import java.util.stream.Collectors
import java.util.stream.Stream

class CalendarWeekController  {
    fun datesBetweeen(startDate: DateTime, endDate: DateTime): MutableList<String> {
        val dates: MutableList<String> = ArrayList()

        while(startDate.isBefore(endDate)) {
            dates.add(startDate.toString(DateTimeFormat.forPattern("dd/MM/yyyy")))
            startDate.plusDays(+1)
        }
        return dates
    }
}