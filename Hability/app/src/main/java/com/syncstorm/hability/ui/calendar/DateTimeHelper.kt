package com.syncstorm.hability.ui.calendar
import org.joda.time.DateTime
import org.joda.time.DateTimeConstants
import org.joda.time.LocalDate
import org.joda.time.format.DateTimeFormat

object DateTimeHelper {

        fun getCurrentWeekStartEndDate(): StartEndDate {
            return StartEndDate(
                LocalDate.now().withDayOfWeek(DateTimeConstants.MONDAY),
                LocalDate.now().withDayOfWeek(DateTimeConstants.SUNDAY)
            )
        }

        fun getNextWeekStartEndDateTime(currentDate: LocalDate): StartEndDate {
            return StartEndDate(
                currentDate.withDayOfWeek(DateTimeConstants.SUNDAY),
                currentDate.withDayOfWeek(DateTimeConstants.SATURDAY).plusWeeks(1)
            )
        }

        fun getPreviousWeekStartEndDateTime(currentDate: LocalDate): StartEndDate {
            return StartEndDate(
                currentDate.withDayOfWeek(DateTimeConstants.SUNDAY).minusWeeks(1),
                currentDate.withDayOfWeek(DateTimeConstants.SATURDAY)
            )
        }

        fun getCurrentMonthStartEndDate(): StartEndDate {
            return StartEndDate(
                LocalDate.now().dayOfMonth().withMinimumValue(),
                LocalDate.now().dayOfMonth().withMaximumValue()
            )
        }

        fun getNextMonthStartEndDateTime(currentDate: LocalDate): StartEndDate {
            return StartEndDate(
                currentDate.plusMonths(1).dayOfMonth().withMinimumValue(),
                currentDate.plusMonths(1).dayOfMonth().withMaximumValue()
            )
        }

        fun getPreviousMonthStartEndDateTime(currentDate: LocalDate): StartEndDate {
            return StartEndDate(
                currentDate.minusMonths(1).dayOfMonth().withMinimumValue(),
                currentDate.minusMonths(1).dayOfMonth().withMaximumValue()
            )
        }

        data class StartEndDate(val startDate: LocalDate, val endDate: LocalDate)

        fun DateTime.toDefaultDateTimeString(dateTimeFormat: String) : String {
            return DateTimeFormat.forPattern(dateTimeFormat).print(this)
        }
}
