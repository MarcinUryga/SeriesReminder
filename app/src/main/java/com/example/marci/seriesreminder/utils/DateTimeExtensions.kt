package com.example.marci.seriesreminder.utils

import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat
import java.util.regex.Pattern

/**
 * Created by marci on 2018-03-22.
 */

fun Any.stringToJodaTime(pattern: String = "yyyy-MM-dd"): DateTime {
  val dateTimeFormatter = DateTimeFormat.forPattern(pattern)
  return dateTimeFormatter.parseDateTime(this.toString())
}

fun DateTime.dateTimeToShortDate(): DateTime {
  return this.toString(DateTimeFormat.shortDate()).stringToJodaTime("dd.MM.yyyy")
}

fun DateTime.dateTimeToString(): String {
  val dateTimeFormatter = DateTimeFormat.forPattern("dd/MM/YYYY")
  return dateTimeFormatter.print(this)
}

fun String.stringFormatToTwoDigitsTime(): String {
  val time = DateTimeFormat.forPattern("H:m").parseDateTime(this)
  return DateTimeFormat.forPattern("HH:mm").print(time)
}