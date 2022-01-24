package com.hrins.hrinsx

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class Utils(val app: App) {

    fun getTimeAgo(time: Long?): String {
        var time = time
        val sdfTime: DateFormat = SimpleDateFormat("h:mm aa", Locale.ENGLISH)
        val sdf: DateFormat = SimpleDateFormat("d MMM 'at' h:mm aa", Locale.ENGLISH)
        val sdfY: DateFormat = SimpleDateFormat("d MMM yyyy 'at' h:mm aa", Locale.ENGLISH)
        val SECOND_MILLIS = 1000
        val MINUTE_MILLIS = 60 * SECOND_MILLIS
        val HOUR_MILLIS = 60 * MINUTE_MILLIS
        val DAY_MILLIS = 24 * HOUR_MILLIS
        if (time != null) {
            if (time < 1000000000000L) {
                time *= 1000
            }
            val today = Calendar.getInstance()
            val timeCal = Calendar.getInstance()
            timeCal.timeInMillis = time
            val now = System.currentTimeMillis()
            if (time > now || time <= 0) {
                return ""
            }
            val diff = now - time
            return if (diff < MINUTE_MILLIS) {
                app.getString(R.string.just_now)
            } else if (diff < 2 * MINUTE_MILLIS) {
                app.getString(R.string._1_min)
            } else if (diff < 59 * MINUTE_MILLIS) {
                (diff / MINUTE_MILLIS).toString() + app.getString(R.string.mins)
            } else if (diff < 2 * HOUR_MILLIS) {
                app.getString(R.string._1_hr)
            } else if (diff < 24 * HOUR_MILLIS) {
                (diff / HOUR_MILLIS).toString() + app.getString(R.string.hours)
            } else if (diff < 48 * HOUR_MILLIS) {
                app.getString(R.string.Yesterday_at).toString() + capsAMtoSmall(
                    sdfTime.format(
                        timeCal.time
                    )
                )
            } else if (today[Calendar.YEAR] == timeCal[Calendar.YEAR]) {
                capsAMtoSmall(sdf.format(timeCal.time))
            } else {
                capsAMtoSmall(sdfY.format(timeCal.time))
            }
        }
        return ""
    }

    fun getDaysOf(time: Long?): String {

        var time = time

        if (time != null) {
            if (time < 1000000000000L) {
                time *= 1000
            }
            val now = System.currentTimeMillis()
            val diff = now - time
            val seconds = diff / 1000
            val minutes = seconds / 60
            val hours = minutes / 60
            val days = hours / 24
            return if (diff > 0)
                days.toString() + app.getString(R.string.daysAgo)
            else (days*-1).toString() + app.getString(R.string.daysLeft)

        }
        return ""
    }

    private fun capsAMtoSmall(time: String): String {
        return time.replace("AM", app.getString(R.string.am))
            .replace("PM", app.getString(R.string.pm))
    }


}