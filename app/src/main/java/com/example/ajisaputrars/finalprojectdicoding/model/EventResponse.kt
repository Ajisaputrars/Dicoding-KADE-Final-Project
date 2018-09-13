package com.example.ajisaputrars.submission4.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class EventResponse(
        @SerializedName("events")
        val events: List<Event>
) : Parcelable