package com.example.ajisaputrars.finalprojectdicoding.model

import android.os.Parcelable
import com.example.ajisaputrars.submission4.model.Event
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class EventSearchResponse(
        @SerializedName("event")
        val event: List<Event>
) : Parcelable