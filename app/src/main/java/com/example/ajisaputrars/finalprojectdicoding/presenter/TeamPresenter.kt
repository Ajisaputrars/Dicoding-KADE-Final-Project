package com.example.ajisaputrars.finalprojectdicoding.presenter

import com.example.ajisaputrars.finalprojectdicoding.interfaces.TeamView
import com.example.ajisaputrars.submission4.api.ApiRepository
import com.example.ajisaputrars.submission4.api.TheSportDbApi
import com.example.ajisaputrars.submission4.model.TeamResponse
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class TeamPresenter(private val view: TeamView,
                    private val apiRepository: ApiRepository,
                    private val gson: Gson) {

    fun getTeamList(league: String?) {
        view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepository
                    .doRequest(TheSportDbApi.getTeams(league)),
                    TeamResponse::class.java
            )

            uiThread {
                view.hideLoading()
                view.showTeamList(data.teams)
            }
        }
    }
}