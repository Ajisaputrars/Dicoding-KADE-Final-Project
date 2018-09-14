package com.example.ajisaputrars.finalprojectdicoding.util

import org.junit.Test

import org.junit.Assert.*
import java.text.SimpleDateFormat

class UtilityKtTest {

    @Test
    fun nullToEmpty() {
        val nullString: String? = null
        assertEquals("", nullString.nullToEmpty())
    }

    @Test
    fun toSimpleString() {
        val dateFormat = SimpleDateFormat("MM/dd/yyyy")
        val date = dateFormat.parse("02/28/2018")
        assertEquals("Wed, 28 Feb 2018", toSimpleString(date))
    }

    @Test
    fun dateTimeToFormat() {
        val dateTime = "2018-09-15 14:00:00+00:00"
        val dateFormat = 1536994800000
        assertEquals(dateFormat, dateTime.dateTimeToFormat())
    }
}