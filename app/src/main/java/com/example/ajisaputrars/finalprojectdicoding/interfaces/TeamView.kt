package com.example.ajisaputrars.finalprojectdicoding.interfaces

import com.example.ajisaputrars.submission4.model.Team

interface TeamView {
    fun showLoading()
    fun hideLoading()
    fun showTeamList(data: List<Team>)
}