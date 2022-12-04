package net.hafiznaufalr.movie.utils

import java.text.SimpleDateFormat
import java.util.*

fun String.generalDateHelper(fromPattern: String, targetPattern: String): String {
    val dateFormat = SimpleDateFormat(fromPattern, Locale.getDefault())
    dateFormat.timeZone = TimeZone.getTimeZone("GMT")

//    val localeID = Locale("in", "ID")
    val readableFormat = SimpleDateFormat(targetPattern)
    val date = dateFormat.parse(this)!!

    return readableFormat.format(date)
}
