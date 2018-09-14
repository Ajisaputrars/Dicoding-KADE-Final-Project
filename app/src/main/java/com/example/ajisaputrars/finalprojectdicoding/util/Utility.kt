package com.example.ajisaputrars.finalprojectdicoding.util

import android.annotation.SuppressLint
import android.view.View
import java.text.SimpleDateFormat
import java.util.*

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun String?.nullToEmpty(): String = this ?: ""

@SuppressLint("SimpleDateFormat")
fun toSimpleString(date: Date?): String? = with(date ?: Date()) {
    SimpleDateFormat("EEE, dd MMM yyy").format(this)
}

fun String.dateTimeToFormat(format: String = "yyyy-MM-dd HH:mm:ss"): Long {

    val formatter = SimpleDateFormat(format, Locale.ENGLISH)
    val date = formatter.parse(this)

    return date.time
}