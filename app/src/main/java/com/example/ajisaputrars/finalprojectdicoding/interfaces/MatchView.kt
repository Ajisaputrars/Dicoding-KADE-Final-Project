package com.example.ajisaputrars.finalprojectdicoding.interfaces

import com.example.ajisaputrars.submission4.model.Event

public interface MatchView {
    fun showLoading()
    fun hideLoading()
    fun showTeamList(data: List<Event>)
}