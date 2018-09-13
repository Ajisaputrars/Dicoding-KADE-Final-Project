package com.example.ajisaputrars.finalprojectdicoding.interfaces

import com.example.ajisaputrars.submission4.model.Event
import com.example.ajisaputrars.submission4.model.Team

interface MatchSearchView {
    fun showMatchSearchList(data: List<Event>?)
}