package com.example.marci.seriesreminder.utils

import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat

/**
 * Created by marci on 2018-03-22.
 */

fun String.stringToJodaTime(): DateTime {
  val dateFields = this.split("-")
  return DateTime(
      dateFields[0].toInt(),
      dateFields[1].toInt(),
      dateFields[2].toInt(),
      23,
      59)
}

fun DateTime.dateTimeToString(): String {
  val dateTimeFormatter = DateTimeFormat.forPattern("dd/MM/YYYY")
  return dateTimeFormatter.print(this)
}