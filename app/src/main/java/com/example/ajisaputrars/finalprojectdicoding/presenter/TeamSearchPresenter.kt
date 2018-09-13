package com.example.ajisaputrars.finalprojectdicoding.presenter

import com.example.ajisaputrars.finalprojectdicoding.interfaces.TeamSearchView
import com.example.ajisaputrars.finalprojectdicoding.interfaces.TeamView
import com.example.ajisaputrars.submission4.api.ApiRepository
import com.example.ajisaputrars.submission4.api.TheSportDbApi
import com.example.ajisaputrars.submission4.model.TeamResponse
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class TeamSearchPresenter(private val view: TeamSearchView,
                          private val apiRepository: ApiRepository,
                          private val gson: Gson) {

    fun getTeamsSearch(name: String?) {
        doAsync {
            val data = gson.fromJson(apiRepository
                    .doRequest(TheSportDbApi.getSearchTeams(name)),
                    TeamResponse::class.java
            )

            uiThread {
                view.showTeamSearchList(data.teams)
            }
        }
    }
}